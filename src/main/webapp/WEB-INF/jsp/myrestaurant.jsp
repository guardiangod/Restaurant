<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<c:url var="addURL" value="/owner/restaurant/new" />
<c:url var="editURL" value="/owner/restaurant/edit" />
<form:form action="${editURL}" method="post" id="myRestaurantForm" name="myRestaurantForm" >
	<p id="myRestauranError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span></span>
	</p>
	<br/><br/>
	<button id="newRstBtn" name="newRstBtn">
		<span class="ui-icon ui-icon-newwin"></span>Register a Restaurant
	</button>
	<br/><br/>
	<c:if test="${!empty restaurantList}">
		<table border="0" summary="result" width="100%" cellspacing="10px">
		<c:forEach items="${restaurantList}" var="rs">
			<tr><td colspan="5"><hr /></td></tr>
			<tr>
				<td width="20%"><img src="../images/${rs.restaurantImage}" alt="${rs.restaurantImage}" width="74px" /></td>
				<td width="20%">${rs.restaurantName}<br/><span class="subheading">Cuisine: </span>${rs.cuisine}</td>
				<td width="20%">${rs.streetAddress}<br/>${rs.city}, ${rs.state}&nbsp;${rs.postal}
					<br/><span class="subheading">Ph: </span>${rs.phone}
				</td>
				<td width="20%">
					<c:if test="${empty rs.avgRating}">Not yet rated</c:if>
					<c:if test="${!empty rs.avgRating}">
						<c:forEach begin="1" end="${rs.avgRating}">
							<img src='../images/star.png' alt='star' />&nbsp;
						</c:forEach>
					</c:if>
				</td>
				<td>
					<button class="editRestBtn" name="editRestBtn" id="editRestBtn${rs.restaurantId}">
						Edit
					</button>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty restaurantList}">
		<p>You do not have any restaurant registered with us yet.</p>
	</c:if>
</form:form>
