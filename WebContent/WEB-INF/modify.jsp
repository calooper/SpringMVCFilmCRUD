<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<title>Edit Film</title>
</head>

<body>

	<form action="updateFilm.do">


		<input type="hidden" name="id" value="${ film.id}" style="opacity: 0.5;"/>
		Title <input type="text" name="title" value="${ film.title}" style="opacity: 0.5;"/> 
		About <input type="text" name="description"value="${ film.description}" style="opacity: 0.5;"/> 
		Year <input type="text" name="releaseYear" value="${ film.releaseYear}" style="opacity: 0.5;"/><br><br> 
		Language ID <input type="text" name="languageId"value="${ film.languageId}" style="opacity: 0.5;"/> 
		Rental Duration <input type="text" name="rentalDuration" value="${ film.rentalDuration}" style="opacity: 0.5;"/> 
		Rate <input type="text" name="rentalRate" value="${ film.rentalRate}" style="opacity: 0.5;"/><br><br>
		Length <input type="text" name="length" value="${ film.length}" style="opacity: 0.5;"/> 
		Replacement Cost <input type="text" name="replacementCost" value="${ film.replacementCost}" style="opacity: 0.5;"/> 
		Rating <input type="text" name="rating" value="${ film.rating}" style="opacity: 0.5;"/><br><br> 
		Features<input type="text" name="specialFeatures" value="${ film.specialFeatures}" style="opacity: 0.5;"/><br> 
		<input type="submit" value="Submit changes" />
		</form>
		
</body>
</html>