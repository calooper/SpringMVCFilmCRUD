<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous"> -->
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

			<!-- <form action = "index.html">
				<input type="submit" class="fas fa-home" value="Back to Home"/>
			</form> -->
	<c:choose>
		<c:when test="${! empty film}">
			<ul style="list-style: none;">
				<li><strong>${film.title}</strong></li>
				<li>About: ${film.description}</li>
				<li>Release Year: ${film.releaseYear}</li>
				<li>Rental Duration: ${film.rentalDuration } days</li>
				<li>Rental Rate: $${film.rentalRate } per night</li>
				<li>Length: ${film.length } Minutes</li>
				<li>Replacement Cost: $${film.replacementCost }</li>
				<li>Rating: ${film.rating }</li>
				<li>Special Features ${film.specialFeatures }</li>
				<li>Language: ${film.filmLanguage }</li>
				<li>Category: ${category}</li>
				<ul>
					<strong>Cast:</strong>
					<c:forEach items="${film.cast }" var="actor">
						<li>${actor.firstName } ${actor.lastName } 
						<form action="removeActorFromFilm.do">
						<input type="hidden" name="filmid" value= ${ film.id} />
						<input type="hidden" name="actorid" value= ${ actor.id} />
						<input type="submit" value="Remove Actor"/> 
						</form> 
						</li>
					</c:forEach>
				</ul>
			</ul>
			<form action="deleteFilm.do" method="GET">
				<input type="hidden" name="id" value=${ film.id} /> <input
					type="submit" value="Delete this film" />
			</form>
			<form action="modifyFilm.do" method="GET">
				<input type="hidden" name="id" value=${ film.id} /> <input
					type="submit" value="Modify" />
			</form>
			<form action="getNewActor.do" method="GET">
			<input type="hidden" name="filmid" value=${ film.id} />
			<input type="submit" value="Add Actor to Film"/>
			</form>
			
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>