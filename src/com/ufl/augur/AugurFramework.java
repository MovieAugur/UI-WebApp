package com.ufl.augur;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.elasticmapreduce.model.DescribeJobFlowsRequest;
import com.amazonaws.services.elasticmapreduce.model.DescribeJobFlowsResult;
import com.amazonaws.services.elasticmapreduce.model.HadoopJarStepConfig;
import com.amazonaws.services.elasticmapreduce.model.JobFlowDetail;
import com.amazonaws.services.elasticmapreduce.model.JobFlowExecutionState;
import com.amazonaws.services.elasticmapreduce.model.JobFlowInstancesConfig;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowRequest;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowResult;
import com.amazonaws.services.elasticmapreduce.model.StepConfig;
import com.amazonaws.services.elasticmapreduce.util.StepFactory;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class AugurFramework {

	private static final List<JobFlowExecutionState> DONE_STATES = Arrays
			.asList(new JobFlowExecutionState[] {
					JobFlowExecutionState.COMPLETED,
					JobFlowExecutionState.FAILED,
					JobFlowExecutionState.TERMINATED });

	/**
	 * 
	 * 
	 * @param args
	 *            args[0] mode - 't'/'p' args[1] movie - if 'p' args[2] new
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out
					.println("Usage: \"<exec> <t> [<year><skip>]\" or \"<exec> p <moviename> [new]\"");
			return;
		}
		if (args[0].equals("t")) {
			// Training mode
			/*
			 * Get a list of movies, and compute metrics for movies not in the
			 * DB, and store in the DB.
			 * 
			 * 1. Get a list of movies (from wiki or recents). 2. Check in DB
			 * for each Moviename in list. If movieName in DB, remove it from
			 * the list. 3. Load data for the movies from various sources and
			 * write to S3. 4. Run EMR on the data from S3. 5. Aggregate the
			 * data and load it in Hive DB.
			 */
			// 1, 2
			int year = 2013;
			int skip = 0;
			if (args.length > 1) {
				year = Integer.parseInt(args[1]);
				skip = Integer.parseInt(args[2]);
			}
			List<String> movieList = new ArrayList<String>();

			AWSCredentials credentials = null;
			try {
				credentials = new ProfileCredentialsProvider().getCredentials();
			} catch (Exception e) {
				throw new AmazonClientException(
						"Cannot load credentials from the file", e);
			}

			AmazonS3Client s3 = new AmazonS3Client(credentials);

			System.out.println("Loading RT JAR...");
			File rtJarFile = new File("RottenTomatoes.jar");
			s3.getObject(new GetObjectRequest("augurframeworknew",
					"bin/RottenTomatoes.jar"), rtJarFile);

			System.out.println("Loading Twitter JAR...");
			File twitJarFile = new File("Twitter.jar");
			s3.getObject(new GetObjectRequest("augurframeworknew",
					"bin/Twitter.jar"), twitJarFile);

			System.out.println("Loading YouTube JAR...");
			File ytJarFile = new File("YouTube.jar");
			s3.getObject(new GetObjectRequest("augurframeworknew",
					"bin/YouTube.jar"), ytJarFile);

			File file = new File("movieList.txt");

			String s3filename = "trainingmovielist/movies" + year + ".txt";
			System.out.println("Retrieving: " + s3filename);
			GetObjectRequest objReq;
			try {
				objReq = new GetObjectRequest("augurframeworknew", s3filename);
			} catch (Exception e) {
				System.out.println("No year data. Aborting!");
				return;
			}

			ObjectMetadata meta = s3.getObject(objReq, file);
			movieList.clear();
			try {
				List<String> fullMovieList = Files.readAllLines(file.toPath(),
						Charset.defaultCharset());
				movieList = fullMovieList.subList(skip, fullMovieList.size());
				// System.out.println(movieList);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
			if (meta != null) {
				// List<String> rtList = new ArrayList<String>(movieList);
				// List<String> twitList = new ArrayList<String>(movieList);
				Iterator<String> rtIter = movieList.iterator();
				Iterator<String> twIter = movieList.iterator();
				Iterator<String> ytIter = movieList.iterator();
				while (twIter.hasNext()) {
					// Call Twitter with list of movies
					/*String twitCmd = "java -jar Twitter.jar augurframeworknew mapreduceInput/train";
					for (int i = 0; i < 5 && twIter.hasNext(); i++) {
						StringTokenizer tokenizer = new StringTokenizer(
								twIter.next(), "\t");
						String movie = tokenizer.nextToken();
						twitCmd = twitCmd + " " + movie.replaceAll("\\s", "_");
						System.out.println(movie);
					}
					try {
						boolean fault = false;
						String line;
						System.out.println(twitCmd);
						Process p = Runtime.getRuntime().exec(twitCmd);
						BufferedReader bri = new BufferedReader(
								new InputStreamReader(p.getInputStream()));
						BufferedReader bre = new BufferedReader(
								new InputStreamReader(p.getErrorStream()));
						while ((line = bri.readLine()) != null) {
							System.out.println(line);
							String error;
							if ((error = bre.readLine()) != null) {
								System.out.println(error);
								fault = true;
								break;
							}
						}
						if (fault) {
							return;
						}
						bre.close();
						bri.close();
						p.waitFor();
					} catch (IOException e) {
						System.out.println("Error in Twitter: "
								+ e.getMessage());
						return;
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
					// Sleep 600,000 ms
					try {
						System.out.println("Done 1.");
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}*/

					// Call Rotten Tomatoes with list of movies

					/*String rtCmd = "java -jar RottenTomatoes.jar -c augurframeworknew mapreduceInput/train";
					for (int i = 0; i < 5 && rtIter.hasNext(); i++) {
						StringTokenizer tokenizer = new StringTokenizer(
								twIter.next(), "\t");
						String movie = tokenizer.nextToken();
						rtCmd = rtCmd + " " + movie.replaceAll("\\s", "_");
						System.out.println(movie);
					}
					try {
						boolean fault = false;
						String line;
						System.out.println(rtCmd);
						Process p = Runtime.getRuntime().exec(rtCmd);
						BufferedReader bri = new BufferedReader(
								new InputStreamReader(p.getInputStream()));
						BufferedReader bre = new BufferedReader(
								new InputStreamReader(p.getErrorStream()));
						while ((line = bri.readLine()) != null) {
							System.out.println(line);
							String error;
							if ((error = bre.readLine()) != null) {
								System.out.println(error);
								fault = true;
								break;
							}
						}
						if (fault) {
							return;
						}
						bre.close();
						bri.close();
						p.waitFor();
					} catch (IOException e) {
						System.out.println("Error in RT: " + e.getMessage());
						return;
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
					// Sleep 600,000 ms
					try {
						System.out.println("Done 2.");
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}*/

					// Call YouTube with list of movies

					String ytCmd = "java -jar YouTube.jar augurbucket movies";
					for (int i = 0; i < 5 && ytIter.hasNext(); i++) {
						StringTokenizer tokenizer = new StringTokenizer(
								twIter.next(), "\t");
						String movie = tokenizer.nextToken();
						ytCmd = ytCmd + " " + movie.replaceAll("\\s", "_");
						System.out.println(movie);
					}
					try {
						boolean fault = false;
						String line;
						System.out.println(ytCmd);
						Process p = Runtime.getRuntime().exec(ytCmd);
						BufferedReader bri = new BufferedReader(
								new InputStreamReader(p.getInputStream()));
						BufferedReader bre = new BufferedReader(
								new InputStreamReader(p.getErrorStream()));
						while ((line = bri.readLine()) != null) {
							System.out.println(line);
							String error;
							if ((error = bre.readLine()) != null) {
								System.out.println(error);
								fault = true;
								break;
							}
						}
						if (fault) {
							return;
						}
						bre.close();
						bri.close();
						p.waitFor();
					} catch (IOException e) {
						System.out.println("Error in YouTube: "
								+ e.getMessage());
						return;
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					} // Sleep 600,000 ms
					try {
						System.out.println("Done 3.");
						Thread.sleep(300000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}
				}
			}
			System.exit(0);
		}

		// 4
		if (args[0].equals("m")) {
			AWSCredentials credentials = null;
			try {
				credentials = new ProfileCredentialsProvider().getCredentials();
			} catch (Exception e) {
				throw new AmazonClientException(
						"Cannot load credentials from the file", e);
			}
			AmazonElasticMapReduce client = new AmazonElasticMapReduceClient(
					credentials);

			// predefined steps. See StepFactory for list of predefined steps
			StepConfig hive = new StepConfig("Hive",
					new StepFactory().newInstallHiveStep());

			StepConfig debug = new StepConfig("Debug",
					new StepFactory().newEnableDebuggingStep());

			// A custom step
			HadoopJarStepConfig hadoopConfig1 = new HadoopJarStepConfig()
					.withJar("s3://augurframeworknew/bin/AugurMapreduce.jar")
					.withArgs("s3://augurframeworknew/include/",
							"s3://augurframeworknew/" + args[1],
							"s3://augurframeworknew/outputs/" + args[2]); // optional
			// list of
			// arguments
			StepConfig customStep = new StepConfig("augurTrain", hadoopConfig1);

			RunJobFlowResult result = client.runJobFlow(new RunJobFlowRequest()
					.withSteps(debug, hive, customStep)
					.withAmiVersion("3.3.0")
					.withInstances(
							new JobFlowInstancesConfig().withInstanceCount(3)
									.withMasterInstanceType("m1.medium")
									.withSlaveInstanceType("m1.medium")
									.withKeepJobFlowAliveWhenNoSteps(false)
									.withEc2KeyName("augur")
									.withHadoopVersion("2.4.0"))
					.withName("augurmr")
					.withLogUri("s3://augurframeworknew/log/")
					.withServiceRole("Role2").withJobFlowRole("SailiRole"));
			// AddJobFlowStepsResult result = client.addJobFlowSteps(new
			// AddJobFlowStepsRequest()
			// .withJobFlowId()
			// .withSteps(hive, customStep));

			System.out.println(result.getJobFlowId());

			String lastState = "";
			STATUS_LOOP:

			while (true) {
				DescribeJobFlowsRequest desc = new DescribeJobFlowsRequest(
						Arrays.asList(new String[] { result.getJobFlowId() }));

				DescribeJobFlowsResult descResult = client
						.describeJobFlows(desc);

				for (JobFlowDetail detail : descResult.getJobFlows()) {
					String state = detail.getExecutionStatusDetail().getState();
					if (isDone(state)) {
						System.out.println("Job " + state + ": "
								+ detail.toString());
						break STATUS_LOOP;
					} else if (!lastState.equals(state)) {
						lastState = state;
						System.out.println("Job " + state + " at "
								+ new Date().toString());
					}

				}
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else if (args[0].equals("p")) {
			if (args.length > 3) {
				System.out
						.println("Usage: \"<exec> t\" or \"<exec> p <moviename> [new]\"");
				return;
			}
			// String movieName = args[1];
			// boolean rePredict = args.length == 3;
			// Predict mode
			/*
			 * Predict the movie box office collections
			 * 
			 * 1. If movie is in DB, load the already calculated collection and
			 * display the result if rePrediction is not requested. ELse, go to
			 * step 2. 2. Load data for the movie from various sources and write
			 * to S3. 3. Run EMR on the data from S3. 4. Aggregate the data, and
			 * load it in Hive DB, along with the prediction info. Display the
			 * prediction.
			 */
		} else {
			System.out
					.println("Usage: \"<exec> t\" or \"<exec> p <moviename> [new]\"");
		}
	}

	public static boolean isDone(String value) {

		JobFlowExecutionState state = JobFlowExecutionState.fromValue(value);

		return DONE_STATES.contains(state);

	}

}