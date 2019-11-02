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
		<c:when test="${! empty film}">
			<ul>
				<li>${film.title}</li>
				<li>${film.description}</li>
				<li>${film.releaseYear}</li>
			</ul>
			<form action="deleteFilm.do" method="GET">
				<input type="hidden" name="id" value=${ film.id} /> 
				<input type="submit" value="Delete this film" />
			</form>
			<form action="modifyFilm.do" method="GET">
				<input type="hidden" name="film" value=${ film} /> 
				<input type="submit" value="Modify" />
			</form>
			<form action = "index.html">
				<input type="submit" value="Back to Home"/>
			</form>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
</body>
</html>