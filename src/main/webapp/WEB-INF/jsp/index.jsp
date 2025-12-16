<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>HPE</title>
	
	<link rel="stylesheet" type="text/css" href="jquery/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" href="jquery/jquery-ui-timepicker.css"/>		
	<link rel="stylesheet" type="text/css" href="css/app.css"/>
	
	<script src="jquery/jquery.js" type="text/javascript"></script>
	<script src="jquery/jquery-ui.js" type="text/javascript"></script>
	<script src="jquery/jquery-ui-timepicker.js" type="text/javascript"></script>
	<script src="js/app.js" type="text/javascript"></script>
	<script src="js/utility.js" type="text/javascript"></script>
</head>
<c:url var="home" value="/home" />
<c:url var="restReview" value="/review/search" />
<c:url var="myRestaurant" value="/owner/restaurant" />
<c:url var="browseMenu" value="/menu/rest/search" />
<body>
	<div id="header">
		<table summary="header" width="100%">
			<tr>
				<td width="30%"><img id=logo alt="Site logo" src="images/logo.jpg" /></td>
				<td class="title">Restaurant Service</td>
				<td width="20%">
					<button id="signin">Sign in</button>
				</td>
			</tr>
		</table>
	</div>
	<div id="loginbox" class="ui-widget-content ui-corner-all"></div>
	<div id="registerDialog" title="Member Registration"></div>
	<div id="newReviewDialog" title="Write a Review"></div>
	<div id="newRestaurantDialog" title="Restaurant Registration"></div>
	<div class="restaurantInfo" title=""></div>
	<div id="content">
		<div id="menu">
			<ul>
				<li>
					<a href="${home}">Home</a>
				</li>
				<li>
					<a href="${restReview}">Restaurant Reviews</a>
				</li>
				<li>
					<a href="${myRestaurant}">My Restaurants</a>
				</li>
				<li>
					<a href="${browseMenu}">Browse Menus</a>
				</li>
			</ul>
		</div>
	</div>
	<div id="footer">
		<p id="copyright">
			&copy;2021 Ryan Le
		</p>
	</div>
</body>
</html>