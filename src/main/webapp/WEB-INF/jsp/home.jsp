<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<c:url var="chief" value="../images/happy-chef.png" />
<form action="" method="get" id="home" name="home" target="_blank">
	<h3> Welcome to HPE - your online restaurant service.</h3>
	<br/>
	<div class="homeBox">
		<p>
			You want to go out to eat with your relatives and friends today, but don't know which restaurant is great?<br />
			- We, at HPE, have a solution for you: Search for your dining place through our online, easy to use restaurant service!
			<br /><br />
			Do you get bored visiting the same old restaurant? Would you like to try something new?<br />
			- Check out our user reviews. You don't even need to register to read restaurant reviews!<br />
			- Let's search for popular restaurants in your vicinity, see restaurant reviews from our members, and book a reservation today!
			<br /><br />
			Looking for your favorite Asian food or are you a vegetarian food lover?<br />
			- Find a suitable menus through HPE. It is simple!
			<br /><br />
			If you are a restaurant owner and want to introduce your restaurant to everyone?
			- Register your restaurant here and share your delicious menu
		</p>
		<br/><br />
		<h3>What are you waiting for?</h3>
		<br />
		<h4 style="color:#a68115;">Sign up for a membership today - to manage your restaurant or share your dining experience!</h4>		
	</div>
	<img src="${chief}" alt="food" height="300px" width="300px"/>
</form>
