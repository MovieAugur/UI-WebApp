<%@page import="com.ufl.augur.MovieMetrics"%>
<%@page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Augur</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>
<body id="top">
<div class="wrapper col1">
  <div id="header" class="clear">
    <div class="fl_left">
      <h1><a href="http://localhost:8080/augur/PredictionEngine">Augur</a></h1>
      <h2>Movie Box-Office Prediction Engine</h2>
    </div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper col1">
  <div id="topbar" class="clear">
    <ul id="topnav">
    </ul>
    <form action="PredictionEngine" method="post" id="search">
      <fieldset>
        <legend>Site Search</legend>
        <input name="movieName" type="text" value="Search for a movie&hellip;" onfocus="this.value=(this.value=='Search for a movie&hellip;')? '' : this.value ;" />
        <input type="image" id="go" src="images/search.gif" alt="Search" />
      </fieldset>
    </form>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper col2">
  <div id="container" class="clear">
    <!-- ####################################################################################################### -->
    <div id="portfolio">
    <% 	
    	MovieMetrics movie = (MovieMetrics) session.getAttribute("movie");
    	DecimalFormat df = new DecimalFormat("#,##0");
    %>
      <div class="portfoliocontainer clear">
        <div class="fl_left">
        	<img src="images/temp_movies/<%=movie.getImageName()%>.jpg" alt="" />       	
        </div>
        <div class="fl_right">  
          <h2><label style="font-size:25px"><%=movie.getMovieName()%></label></h2>
          	<!-- <p style="padding-top: 5px; font-size:15px;"><label style="font-weight:bold; color:#FFA100">Stars:</label> Chris Pratt, Zoe Saldana, Dave Bautista, Vin Diesel, Bradley Cooper</p>-->
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Box Office Collection: </label><label style=" color:#FFA100"></label> <%=movie.getBoxOfficeEarnings() %> $$</p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold; color:#FFA100">Sentiment Factor:</label> <%=movie.getSentimentFactor() %></p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Hype Factor:</label><label style=" color:#FFA100"> <%=movie.getHypeFactor() %></label></p>
          	<p style="padding-top: 5px; font-size:17px; font-weight:bold; border-bottom: 1px solid #464646;">Detailed Metrics</p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Starpower:</label> <%=movie.getStarPower() %></p>
          	<p style="padding-top: 5px; font-size:17px; font-weight:bold; color:#FFA100">Rotten Tomatoes</p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Average Audience Sentiment:</label> <%=movie.getRtAudienceSentiment() %></p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Average Critic Sentiment:</label> <%=movie.getRtCriticSentiment() %></p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Audience Score:</label> <%=df.format(movie.getRtAudienceScore()) %>%</p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Tomatometer:</label> <%=df.format(movie.getRtCriticScore()) %>%</p>
          	<p style="padding-top: 1px; font-size:17px; font-weight:bold; color:#FFA100">YouTube</p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Average Comment Sentiment:</label> <%=movie.getYtCommentSentiment() %></p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Trailer Views:</label> <%=df.format(movie.getYtTrailerViews()) %></p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Likes:</label> <%=df.format(movie.getYtLikes()) %></p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Dislikes:</label> <%=df.format(movie.getYtDisLikes()) %></p>
          	<p style="padding-top: 1px; font-size:17px; font-weight:bold; color:#FFA100">Twitter</p>
          	<p style="padding-top: 5px; font-size:17px;"><label style="font-weight:bold;">Average Tweet Sentiment:</label> <%=movie.gettTweetSentiment() %></p>
        </div>
      </div>
      <!-- ########### -->
      
      <!-- ########### -->
    </div>
    <!-- ####################################################################################################### -->
    <!-- ####################################################################################################### -->
  </div>
</div>
<!-- ####################################################################################################### -->
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="copyright" class="clear">
    <p class="fl_left">Copyright &copy; 2011 - All Rights Reserved - <a href="#">Domain Name</a></p>
    <p class="fl_right">Template by <a href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
  </div>
</div>
</body>
</html>