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
		<c:when test="${! empty filmList}">
			<ol>
			<c:forEach items="${filmList}" var="film">
				<li>
				<ul><strong>${film.title }</strong>
					<li>${film.description}</li>
					<li>${film.releaseYear}</li>
					
				</ul>	
			<ul><strong>Cast:</strong>
					<c:forEach items="${film.cast }" var="actor">
							<li> ${actor.firstName } ${actor.lastName }</li>
							
					</c:forEach>
					</ul>
			</li>
			<br>
			</c:forEach>
			</ol>
		</c:when>
		<c:otherwise>
			<p>No films found for that keyword</p>
		</c:otherwise>
	</c:choose>
</body>
</html>