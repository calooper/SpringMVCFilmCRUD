package com.skilldistillery.film.dao;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	public Film filmSearchId(int id);

	public List<Film> filmSearchKeyword(String keyword);

	public List<Actor> findActorsByFilmId(int filmId);

	public Film createFilm(String title, String description, int releaseYear);

	public boolean deleteFilm(int id);

	public boolean modifyFilm(int id, String title, String description, int rereleaseYear, int languageId,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures);

	public String findCategory(int id);
}
