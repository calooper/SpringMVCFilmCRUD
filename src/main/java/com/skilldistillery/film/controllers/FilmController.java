package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = "searchId.do")
	public ModelAndView filmSearchId(int id) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("WEB-INF/result.jsp");
		mv.addObject("film", filmDao.filmSearchId(id));
		mv.addObject("category", filmDao.findCategory(id));

		return mv;
	}

	@RequestMapping(path = "searchKeyword.do")
	public ModelAndView filmSearchKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();

		List<Film> filmList = filmDao.filmSearchKeyword(keyword);
		String category;
		
		for (Film film : filmList) {

			category = filmDao.findCategory(film.getId());
			film.setCategory(category);
		}

		mv.setViewName("WEB-INF/resultkeyword.jsp");
		mv.addObject("filmList", filmList);
		return mv;
	}

	@RequestMapping(path = "createFilm.do")
	public ModelAndView createFilm(String title, String description, int releaseYear, int rentalDuration, double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {
		String s = ""+releaseYear;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		mv.addObject("film", filmDao.createFilm(title, description, s, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures));
		return mv;
	}
	
	@RequestMapping(path = "createActor.do")
	public ModelAndView createActor(String firstName, String lastName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result_actor.jsp");
		mv.addObject("actor", filmDao.addActor(firstName, lastName));
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do")
	public ModelAndView deleteFilm(int id) {
		boolean result = filmDao.deleteFilm(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("WEB-INF/removedmessage.jsp");
		return mv;
	}

//
	@RequestMapping(path = "updateFilm.do")
	public ModelAndView modifyFilm(String id, String title, String description, String releaseYear, String languageId,
			String rentalDuration, String rentalRate, String length, String replacementCost, String rating,
			String specialFeatures) {

		int idInt = Integer.parseInt(id);
		int releaseYearInt = Integer.parseInt(releaseYear);
		int languageIdInt = Integer.parseInt(languageId);
		int rentalDurationInt = Integer.parseInt(rentalDuration);
		double rentalRateDouble = Double.parseDouble(rentalRate);
		int lengthInt = Integer.parseInt(rentalDuration);
		double replacementCostDouble = Double.parseDouble(rentalRate);

		boolean result = filmDao.modifyFilm(idInt, title, releaseYearInt, description, languageIdInt, rentalDurationInt,
				rentalRateDouble, lengthInt, replacementCostDouble, rating, specialFeatures);

		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.setViewName("WEB-INF/modifiedmessage.jsp");
		return mv;
	}

	@RequestMapping(path = "modifyFilm.do")
	public ModelAndView modifyFilmInputReRoute(int id) {

		Film film = filmDao.filmSearchId(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);

		mv.setViewName("WEB-INF/modify.jsp");
		return mv;
	}
	
	@RequestMapping(path= "getNewActor.do")
	public ModelAndView addActorReRoute(int filmid) {
		Film film = filmDao.filmSearchId(filmid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/add_actor.jsp");
		return mv;
	}
	@RequestMapping(path="addActorToFilm.do")
	public ModelAndView addActor(int filmid, String firstName, String lastName) {
		ModelAndView mv = new ModelAndView();
		Film copy = filmDao.addActorToFilm(filmid, firstName, lastName);
		mv.addObject(copy);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
	@RequestMapping(path="removeActorFromFilm.do")
	public ModelAndView deleteActorFromFilm(int filmid, int actorid) {
		ModelAndView mv = new ModelAndView();
		boolean result = filmDao.deleteActorFromFilm(filmid, actorid);
		mv.setViewName("WEB-INF/actorremovedmessage.jsp");
		mv.addObject("result", result);
		return mv;
				
	}
	
}
