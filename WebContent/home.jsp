<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@page import="com.ufl.augur.MovieMetrics"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Augur</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="scripts/jquery-photostack.js"></script>
<script type="text/javascript" src="scripts/jquery-coin-slider.min.js"></script>
<script type="text/javascript" src="scripts/jquery-ui-1.8.12.custom.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $('#portfolioslider').coinslider({
        width: 480,
        height: 300,
        navigation: true,
        links: false,
        hoverPause: false,
    });
    $("#tabcontainer").tabs({
        event: "click"
    });
});
</script>
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
      <h2>Top 10 Box Office</h2>
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
<div class="wrapper col1">
  <div id="featured_slide">
    <!-- ####################################################################################################### -->
    <div id="slider">
      <ul id="categories">
      <% List<MovieMetrics>topMovies = (ArrayList<MovieMetrics>)session.getAttribute("topMovies");
   		request.setAttribute("topMovieList", topMovies);
		%>
	  <c:set var="counter" value="0" scope="page"/>
      <c:forEach items="${topMovieList}" var="movie">
      	<li class="category">
      	  <c:set var="counter" value="${counter + 1}" scope="page"/>
      	  <form action="PredictionEngine" method="post" id="${counter}">
      		<input type="hidden" name="movieName" value="${movie.movieName}"/>
      	  </form>	
          <a href="#"><img src='images/temp_movies/<c:out value="${movie.imageName}"/>.jpg' alt="" /></a>
          <h2><c:out value="${movie.movieName}"></c:out></h2>
          <p style="font-size:14px;"><label style="color:#FFA100">Box Office Collection: </label><c:out value="${movie.boxOfficeEarnings}"></c:out> $</p>
          <p style="font-size:14px;"><label style="color:#FFA100">Sentiment Factor:</label> <c:out value="${movie.sentimentFactor}"></c:out></p>
          <p style="font-size:14px;"><label style="color:#FFA100">Hype Factor:</label> <c:out value="${movie.hypeFactor}"></c:out></p>
          <p class="readmore"><a href="#" onclick="document.getElementById(${counter}).submit();return false;" style="color:#FFA100">Read More &raquo;</a></p>
        </li>  
      </c:forEach>
      </ul>
      <a class="prev disabled"></a> <a class="next disabled"></a>
      <div style="clear:both"></div>
    </div>
    <!-- ####################################################################################################### -->
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="copyright" class="clear">
    <p class="fl_left">Copyright &copy; 2011 - All Rights Reserved - <a href="#">Domain Name</a></p>
    <p class="fl_right">Template by <a href="http://www.os-templates.com/" title="Free Website Templates">OS Templates</a></p>
  </div>
</div>
</body>
</html>