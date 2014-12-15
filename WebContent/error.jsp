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
    
      <div class="portfoliocontainer clear">
        <div style="float:left; width:500px">
        <img src="images/404Error-3.jpg" alt="" />
        </div>
        <div style="float:right; width:420px">
        <label style="font-weight:bold; font-size:25px;">
        <p style="padding-bottom:20px">Oops!!</p>
        <p style="padding-bottom:20px">Looks like something is wrong...</p> 
        <p style="padding-bottom:20px">Please try again with a different keyword...</p>
        </label>
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