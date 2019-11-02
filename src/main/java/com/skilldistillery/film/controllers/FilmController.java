package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;
@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDao;
	
	@RequestMapping(path="searchId.do", params="id")
	public ModelAndView filmSearchId(@RequestParam("id") int n) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		Film filmSearch = filmDao.filmSearchId(n);
		System.out.println(filmSearch);
		mv.addObject("film", filmDao.filmSearchId(n));
		
		return mv;
	}
	@RequestMapping(path="createFilm.do")
	public ModelAndView filmSearchId(@RequestParam String title, String description, int release_year) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		mv.addObject("film", filmDao.createFilm(title, description, release_year));
		return mv;
	}
}
