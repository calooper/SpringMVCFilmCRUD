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

	@RequestMapping(path = "searchId.do")
	public ModelAndView filmSearchId(int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		mv.addObject("film", filmDao.filmSearchId(id));
		return mv;
	}

	@RequestMapping(path = "searchKeyword.do")
	public ModelAndView filmSearchKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/resultkeyword.jsp");
		mv.addObject("filmList", filmDao.filmSearchKeyword(keyword));
		return mv;
	}

	@RequestMapping(path = "createFilm.do")
	public ModelAndView createFilm(String title, String description, int release_year) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/result.jsp");
		mv.addObject("film", filmDao.createFilm(title, description, release_year));
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
}
