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
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "My First Servlet", urlPatterns = { "/Main",
		"/Main.do" })
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("Train") != null) {
			// Training Phase
		} else if (request.getParameter("Predict") != null) {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
