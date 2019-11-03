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

		<input type="hidden" name="id" value="${ film.id}" /> <input
			type="text" name="title" value="${ film.title}" /> <input
			type="text" name="description" value="${ film.description}" /> <input
			type="text" name="releaseYear" value="${ film.releaseYear}" /> <input
			type="text" name="languageId" value="${ film.languageId}" /> <input
			type="text" name="rentalDuration" value="${ film.rentalDuration}" />
		<input type="text" name="rentalRate" value="${ film.rentalRate}" /> <input
			type="text" name="length" value="${ film.length}" /> <input
			type="text" name="replacementCost" value="${ film.replacementCost}" />
		<input type="text" name="rating" value="${ film.rating}" /> <input
			type="text" name="specialFeatures" value="${ film.specialFeatures}" />
		<input type="submit" value="Submit changes" /> ======= <input
			type="hidden" name="id" value="${ film.id}" /> Title<br> <input
			type="text" name="title" value="${ film.title}" /><br>
		Description<br> <input type="text" name="description"
			value="${ film.description}" /><br> Release Year<br> <input
			type="text" name="releaseYear" value="${ film.releaseYear}" /><br>
		Language ID<br> <input type="text" name="languageId"
			value="${ film.languageId}" /><br> Rental Duration<br> <input
			type="text" name="rentalDuration" value="${ film.rentalDuration}" /><br>
		Rental Rate<br> <input type="text" name="rentalRate"
			value="${ film.rentalRate}" /><br> Length<br> <input
			type="text" name="length" value="${ film.length}" /><br>
		Replacement Cost<br> <input type="text" name="replacementCost"
			value="${ film.replacementCost}" /><br> Rating<br> <input
			type="text" name="rating" value="${ film.rating}" /><br> Special
		Features<br> <input type="text" name="specialFeatures"
			value="${ film.specialFeatures}" /><br> <input type="submit"
			value="Submit changes" /> >>>>>>>
		d6151a3b3a5223895ae33db0b9c254813d37a145
	</form>
</body>
</html>