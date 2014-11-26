package com.ufl.augur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

		try {
			ps = conn.prepareStatement("select id, name from testtable");
			
			//ps.setString(1, searchQuery);
			rs = ps.executeQuery();
			if (rs != null && rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name"));
				/*MovieMetrics movie = new MovieMetrics("Gone Girl",
						"$319.9 million", "88", "20421", "8.4");
				HttpSession session = request.getSession();
				session.setAttribute("movie", movie);
				response.sendRedirect("portfolio.jsp");*/
			} else {
				//perform augury for the movie and fetch data
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(searchQuery);
		MovieMetrics movie = new MovieMetrics("Gone Girl", "$319.9 million",
				"88", "20421", "8.4");
		movie.setImageName("gone_girl");
		HttpSession session = request.getSession();
		session.setAttribute("movie", movie);
		response.sendRedirect("portfolio.jsp");
	}

}
