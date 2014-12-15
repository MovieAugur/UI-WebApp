package com.ufl.augur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PredictionEngine
 */
@WebServlet("/PredictionEngine")
public class PredictionEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String searchQuery;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PredictionEngine() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<MovieMetrics> topMovies = new ArrayList<MovieMetrics>();
		DecimalFormat df = new DecimalFormat("#,##0");
		Connection conn = (Connection) getServletContext().getAttribute(
				"DBconnection");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn
					.prepareStatement("select * from augur_test2 order by pred_collection desc LIMIT 10");
			rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					MovieMetrics movie = new MovieMetrics();
					String[] movieName = rs.getString("movie_name").split(":");
					String imageName = movieName[0].toLowerCase().replace(" ",
							"_");
					//System.out.println(imageName);
					movie.setMovieName(rs.getString("movie_name"));
					movie.setBoxOfficeEarnings(df.format(rs.getFloat("pred_collection")));
					movie.setSentimentFactor(rs.getString("var_coll"));
					movie.setRtAudienceSentiment(rs
							.getFloat("RT_audience_cmt"));
					movie.setRtCriticSentiment(rs.getFloat("RT_critic_cmt"));
					movie.setRtAudienceScore(rs
							.getFloat("RT_audience_score"));
					movie.setRtCriticScore(rs.getFloat("RT_critic_score"));
					movie.setYtDisLikes(rs.getFloat("YT_dislikes"));
					movie.setYtLikes(rs.getFloat("YT_likes"));
					movie.setYtCommentSentiment(rs.getFloat("YT_cmt_score"));
					movie.setYtTrailerViews(rs.getFloat("YT_views"));
					movie.settTweetSentiment(rs.getFloat("twitter_score"));
					movie.setHypeFactor(rs.getFloat("HF"));
					movie.setImageName(imageName);
					topMovies.add(movie);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("topMovies", topMovies);
		response.sendRedirect("home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute(
				"DBconnection");
		PreparedStatement ps = null;
		ResultSet rs = null;

		searchQuery = request.getParameter("movieName");
		DecimalFormat df = new DecimalFormat("#,##0");

		try {
			ps = conn
					.prepareStatement("select * from augur_test2 where movie_name like ? limit 1");
			ps.setString(1, "%" + searchQuery + "%");
			rs = ps.executeQuery();
			if (rs != null && rs.next()) {
				System.out.println(rs.getString(1));
				MovieMetrics movie = new MovieMetrics();
				String[] movieName = rs.getString("movie_name").split(":");
				String imageName = movieName[0].toLowerCase().replace(" ",
						"_");
				System.out.println(imageName);
				movie.setMovieName(rs.getString("movie_name"));
				movie.setBoxOfficeEarnings(df.format(rs.getFloat("pred_collection")));
				movie.setSentimentFactor(rs.getString("var_coll"));
				movie.setRtAudienceSentiment(rs.getFloat("RT_audience_cmt"));
				movie.setRtCriticSentiment(rs.getFloat("RT_critic_cmt"));
				movie.setRtAudienceScore(rs.getFloat("RT_audience_score"));
				movie.setRtCriticScore(rs.getFloat("RT_critic_score"));
				movie.setYtDisLikes(rs.getFloat("YT_dislikes"));
				movie.setYtLikes(rs.getFloat("YT_likes"));
				movie.setYtCommentSentiment(rs.getFloat("YT_cmt_score"));
				movie.setYtTrailerViews(rs.getFloat("YT_views"));
				movie.settTweetSentiment(rs.getFloat("twitter_score"));
				movie.setStarPower(rs.getFloat("starpower"));
				movie.setHypeFactor(rs.getFloat("HF"));
				movie.setImageName(imageName);
				HttpSession session = request.getSession();
				session.setAttribute("movie", movie);
				response.sendRedirect("portfolio.jsp");
			} else {
				/*System.out.println("In else..");
				ps = conn
						.prepareStatement("select * from augur_test2 where movie_name like ? limit 1");
				ps.setString(1, "%" + searchQuery + "%");
				ResultSet rs1 = null;
				System.out.println("before query execution" + ps);
				while(true){
					//System.out.println("in while");
					rs1 = null;
					rs1 = ps.executeQuery();
					if(rs1 !=null && rs1.next())
						break;
				}
				MovieMetrics movie = new MovieMetrics();
				String[] movieName = rs1.getString("movie_name").split(":");
				String imageName = movieName[0].toLowerCase().replace(" ",
						"_");
				System.out.println(imageName);
				movie.setMovieName(rs1.getString("movie_name"));
				movie.setBoxOfficeEarnings(df.format(rs1.getFloat("collection")));
				movie.setSentimentFactor(rs1.getString("var_coll"));
				movie.setRtAudienceSentiment(rs1.getFloat("RT_audience_cmt"));
				movie.setRtCriticSentiment(rs1.getFloat("RT_critic_cmt"));
				movie.setRtAudienceScore(rs1.getFloat("RT_audience_score"));
				movie.setRtCriticScore(rs1.getFloat("RT_critic_score"));
				movie.setYtDisLikes(rs1.getFloat("YT_dislikes"));
				movie.setYtLikes(rs1.getFloat("YT_likes"));
				movie.setYtCommentSentiment(rs1.getFloat("YT_cmt_score"));
				movie.setYtTrailerViews(rs1.getFloat("YT_views"));
				movie.settTweetSentiment(rs1.getFloat("twitter_score"));
				movie.setStarPower(rs1.getFloat("starpower"));
				movie.setHypeFactor(rs1.getFloat("HF"));
				movie.setImageName(imageName);
				HttpSession session = request.getSession();
				session.setAttribute("movie", movie);
				response.sendRedirect("portfolio.jsp");*/				
				response.sendRedirect("error.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(searchQuery);
	}
}
