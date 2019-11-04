<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
	integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
	crossorigin="anonymous">
<title>Film</title>
</head>
<body>


  <a href="index.html" class="fas fa-home" aria-hidden="true"  style="font-size:40px; color:orange; float:right"></a>
  
	<c:choose>
		<c:when test="${result}">
			<h1>Film has been updated!</h1>
		</c:when>
		<c:otherwise>
			<h3>Something went wrong, unable to modify film form database</h3>
		</c:otherwise>
	</c:choose>
	<form action="index.html">
		<input type="submit" value="Back to Home" />
	</form>
</body>
</html>