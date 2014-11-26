<%@page import="com.ufl.augur.MovieMetrics"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>PhotoFolio | Portfolio</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
</head>
<body id="top">
<div class="wrapper col1">
  <div id="header" class="clear">
    <div class="fl_left">
      <h1><a href="index.html">PhotoFolio</a></h1>
      <p>Free CSS Website Template</p>
    </div>
    <div class="fl_right"><a href="#"><img src="images/demo/468x60.gif" alt="" /></a></div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper col1">
  <div id="topbar" class="clear">
    <ul id="topnav">
      <li><a href="index.html">Home</a></li>
      <li><a href="style-demo.html">Style Demo</a></li>
      <li><a href="full-width.html">Full Width</a></li>
      <li><a href="#">DropDown</a>
        <ul>
          <li><a href="#">Link 1</a></li>
          <li><a href="#">Link 2</a></li>
          <li><a href="#">Link 3</a></li>
        </ul>
      </li>
      <li class="active"><a href="portfolio.html">Portfolio</a></li>
      <li class="last"><a href="gallery.html">Gallery</a></li>
    </ul>
    <form action="#" method="post" id="search">
      <fieldset>
        <legend>Site Search</legend>
        <input type="text" value="Search Our Website&hellip;" onfocus="this.value=(this.value=='Search Our Website&hellip;')? '' : this.value ;" />
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
    <%MovieMetrics movie = (MovieMetrics) session.getAttribute("movie");%>
      <div class="portfoliocontainer clear">
        <div class="fl_left">
        	<h2><%=movie.getMovieName()%></h2>
          	<p><label>Box Office Earnings : </label><%=movie.getBoxOfficeEarnings() %></p>
          	<p><label>Rotten Tomatoes : </label><%=movie.getRtRating()%></p>       	
        </div>
        <div class="fl_right">  
          <img src="images/temp_movies/<%=movie.getImageName()%>.jpg" alt="" />
        </div>
      </div>
      <!-- ########### -->
      <div class="portfoliocontainer clear">
        <div class="fl_left">
          <h2>Photography Category 2</h2>
          <p>Nullapretium ipsum maurien nulla nunc ut ametur montes habitur habitur at. Disseaenean aliquam molesuada ristibulum consectetus quis ipsum tor sed aliquam sem. Wisiadipiscinia pellus sollis interdum massa felit sed a elis sus orci.</p>
          <p>Etvivamus justo eu condisse integet quis pede at vivamus nunc ultris. Vestibulumristibus et maecenatibulum consectetus aucibus ut lobortor aenean non sempor nibh.</p>
        </div>
        <div class="fl_right">
          <ul>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li class="last"><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li class="last"><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
          </ul>
        </div>
      </div>
      <!-- ########### -->
      <div class="portfoliocontainer clear">
        <div class="fl_left">
          <h2>Photography Category 3</h2>
          <p>Nullapretium ipsum maurien nulla nunc ut ametur montes habitur habitur at. Disseaenean aliquam molesuada ristibulum consectetus quis ipsum tor sed aliquam sem. Wisiadipiscinia pellus sollis interdum massa felit sed a elis sus orci.</p>
          <p>Etvivamus justo eu condisse integet quis pede at vivamus nunc ultris. Vestibulumristibus et maecenatibulum consectetus aucibus ut lobortor aenean non sempor nibh.</p>
        </div>
        <div class="fl_right">
          <ul>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li class="last"><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
            <li class="last"><img src="images/demo/210x150.gif" alt="" />
              <p class="name">Project Name</p>
              <p class="readmore"><a href="#">View Project Details &raquo;</a></p>
            </li>
          </ul>
        </div>
      </div>
      <!-- ########### -->
    </div>
    <!-- ####################################################################################################### -->
    <div class="pagination">
      <ul>
        <li class="prev"><a href="#">&laquo; Previous</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li class="splitter">&hellip;</li>
        <li><a href="#">6</a></li>
        <li class="current">7</li>
        <li><a href="#">8</a></li>
        <li><a href="#">9</a></li>
        <li class="splitter">&hellip;</li>
        <li><a href="#">14</a></li>
        <li><a href="#">15</a></li>
        <li class="next"><a href="#">Next &raquo;</a></li>
      </ul>
    </div>
    <!-- ####################################################################################################### -->
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="footer" class="clear">
    <div class="footbox">
      <h2>About Us</h2>
      <p>Lornunc tincidunt nec nequat risus convallisis elit vestiquat justo et volutpat. Urnanec monterdum turistibus semportis non vivamus justo pellus ac integestiquat eros. Turet cursuspend ero nulla dapienteger quisque nullamcorper lorem in ut pellus.</p>
      <p>Atmaecenas nec non nam nullamcorper magna id id nisl ac in. Sedfauctortis fuscetus estibus gravida id dui curabitur commodo facilisi loborttitorttitor vitae.</p>
    </div>
    <div class="footbox">
      <h2>Our Skillset</h2>
      <ul>
        <li><a href="#">Lorem ipsum dolor sit</a></li>
        <li><a href="#">Amet consectetur</a></li>
        <li><a href="#">Praesent vel sem id</a></li>
        <li><a href="#">Curabitur hendrerit est</a></li>
        <li><a href="#">Aliquam eget erat nec sapien</a></li>
        <li><a href="#">Cras id augue nunc</a></li>
        <li><a href="#">In nec justo non</a></li>
        <li><a href="#">Vivamus mollis enim ut</a></li>
        <li class="last"><a href="#">Sed a nulla urna</a></li>
      </ul>
    </div>
    <div class="footbox">
      <h2>Blog Links</h2>
      <ul>
        <li><a href="#">Lorem ipsum dolor sit</a></li>
        <li><a href="#">Amet consectetur</a></li>
        <li><a href="#">Praesent vel sem id</a></li>
        <li><a href="#">Curabitur hendrerit est</a></li>
        <li><a href="#">Aliquam eget erat nec sapien</a></li>
        <li><a href="#">Cras id augue nunc</a></li>
        <li><a href="#">In nec justo non</a></li>
        <li><a href="#">Vivamus mollis enim ut</a></li>
        <li class="last"><a href="#">Sed a nulla urna</a></li>
      </ul>
    </div>
    <div class="footbox last">
      <h2>Keep in Touch</h2>
      <ul>
        <li><a href="#">Check out our Facebook page</a></li>
        <li><a href="#">Get the latest Tweets</a></li>
        <li><a href="#">Grab our latest Deviants</a></li>
        <li><a href="#">View our LinkedIn profile</a></li>
      </ul>
      <h2>Contact Us</h2>
      <ul>
        <li><strong class="title">Tel:</strong><br />
          xxxxx xxxxxxxxxx</li>
        <li><strong class="title">Email:</strong><br />
          <a href="#">contact@mydomain.com</a></li>
      </ul>
    </div>
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