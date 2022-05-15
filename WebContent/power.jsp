<%@ page import="com.power"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
	<html>
		<head>
		<meta charset="ISO-8859-1">
		<title>Power Consumption Management</title>
		
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/power.js"></script>
		
		</head>
	<body>
	
	<center>
	<h1>Power Consumption Management</h1>
	</center>
	<div class="container">
		<div class="row">
			<div class="col">
				<form id="formpower" name="formpower" method="post" action="power.jsp">
					ID:
					<input id="ID" name="ID" type="text"
					class="form-control form-control-sm">
					<br> usage:
					<input id="usage" name="usage" type="text"
					class="form-control form-control-sm">
					<br> Unit type:
					<input id="unitType" name="unitType" type="text"
					class="form-control form-control-sm">
					<br> description:
					<input id="description" name="description" type="text"
					class="form-control form-control-sm">
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save"
					class="btn btn-primary btn-lg">
					<input type="hidden" id="hidIDSave" name="hidIDSave" value="">
				</form>
				
			</div>
		</div>
	</div>
	<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
	<br>
		<div id="divpowerGrid">
			 <%
				 power powerObj = new power(); 
				 out.print(powerObj.readpower()); 
			 %>
		</div>
	</body>
	</html>