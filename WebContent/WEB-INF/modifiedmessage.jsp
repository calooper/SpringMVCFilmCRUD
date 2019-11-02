<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>
</head>
<body>
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