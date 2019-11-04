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
	<a href="index.html" class="fas fa-home" aria-hidden="true"
		style="font-size: 40px; color: orange; float: right"></a>
	<c:choose>
		<c:when test="${! empty filmList}">
			<ol>
				<c:forEach items="${filmList}" var="film">
					<li>
						<ul style="list-style: none;">
							<li><strong>${film.title}</strong></li>
							<li>${film.description}</li>
							<li>Release Year: ${film.releaseYear}</li>
							<li>Rental Duration: ${film.rentalDuration } days</li>
							<li>Rental Rate: $${film.rentalRate } per night</li>
							<li>Length: ${film.length } Hours</li>
							<li>Replacement Cost: $${film.replacementCost }</li>
							<li>Rating: ${film.rating }</li>
							<li>Special Features ${film.specialFeatures }</li>
							<li>Language: ${film.filmLanguage }</li>
							<li>Category: ${film.category }</li>
						</ul>
						<ul>
							<strong>Cast:</strong>
							<c:forEach items="${film.cast }" var="actor">
								<li>${actor.firstName }${actor.lastName }</li>

							</c:forEach>
						</ul>
					</li>
					<form action="deleteFilm.do" method="GET">
						<input type="hidden" name="id" value=${ film.id} /> <input
							type="submit" value="Delete this film" />
					</form>
					<form action="modifyFilm.do" method="GET">
						<input type="hidden" name="id" value=${ film.id} /> <input
							type="submit" value="Modify" />
					</form>
					</form>
					<form action="getNewActor.do" method="GET">
						<input type="hidden" name="filmid" value=${ film.id} /> <input
							type="submit" value="Add Actor to Film" />
					</form>
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