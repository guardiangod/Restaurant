<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:url var="insertURL" value="/owner/restaurant/insert" />
<form:form modelAttribute="restaurantEditTo" action="${insertURL}" method="post" id="restForm" name="restForm" >
	<table border="0" summary="restaurant" width="100%" cellspacing="10px">
		<tr>
			<td><label for="restaurantName">Restaurant Name*</label></td>
			<td><form:input path="restaurant.restaurantName" id="restaurantName" size="20" maxlength="30"/></td>
			<td><label for="description">Description</label></td>
			<td colspan="3" rolspan="3">
				<form:textarea path="restaurant.description" id="description" rows="3" cols="10"></form:textarea>
			</td>
		</tr>
		<tr>
			<td><label for="cuisine">Cuisine*</label></td>
			<td>
				<form:select path="restaurant.cuisine" id="cuisine">
					<form:option value="0" label="Select"></form:option>
					<form:options items="${restaurantEditTo.cuisineList}" itemValue="cuisineId" itemLabel="cuisineName" />
				</form:select>
			</td>
		<tr>
		<tr>
			<td><label for="streetAddress">Street Address*</label></td>
			<td><form:input path="restaurant.streetAddress" id="streetAddress" size="30" maxlength="100"/></td>
		</tr>
		<tr>
			<td><label for="city">City*</label></td>
			<td><form:input path="restaurant.city" id="city" size="20" maxlength="30"/></td>
			<td><label for="state">State*</label></td>
			<td><form:input path="restaurant.state" id="state" size="20" maxlength="30"/></td>
			<td><label for="postal">Postal Code*</label></td>
			<td>
				<form:input path="restaurant.postal" id="postal" size="6" maxlength="6"/>
				<span class="tiny">(123456)</span>
			</td>
		</tr>
		<tr>
			<td><label for="phone">Phone</label></td>
			<td>
				<form:input path="restaurant.phone" id="phone" size="13" maxlength="30"/>
				<span class="tiny">(+65 1234 5678)</span>
			</td>
			<td><label for="website">Website</label></td>
			<td colspan="3"><form:input path="restaurant.website" id="website" size="80" maxlength="200"/></td>
		</tr>
		<tr>
			<td><label for="restaurantImage">Restaurant Image</label></td>
			<td colspan="5">
				<form:select path="restaurant.restaurantImage" id="restaurantImage">
					<form:option value="reserved.jpg" label="Select"></form:option>
					<c:forEach begin="1" end="15" step="1" var="i">
						<form:option value="rest-${i}.jpg" label="rest-${i}.jpg" />
					</c:forEach>
				</form:select>
			</td>
		</tr>
	</table>
	<br/><br/>
	<p id="restError" class="ui-state-error ui-corner-all">
		<span class="ui-icon ui-icon-alert"></span>
		<span>Fields marked with * are required</span>
	</p>	
</form:form>
