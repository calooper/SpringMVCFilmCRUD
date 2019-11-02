package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDaoImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Film filmSearchId(int id) {
		Film film = null;
		List<Actor> cast = new ArrayList<>();

		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM film join language on film.language_id = language.id WHERE film.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();

			if (res.next()) {
				cast = findActorsByFilmId(id);
				film = new Film(res.getInt("id"), res.getString("title"), res.getString("description"),
						res.getInt("release_year"), res.getInt("language_id"), res.getInt("rental_duration"),
						res.getDouble("rental_rate"), res.getInt("length"), res.getDouble("replacement_cost"),
						res.getString("rating"), res.getString("special_features"), cast,
						res.getString("language.name"));
			}
			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return film;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();

		String user = "student";
		String pass = "student";
		String sql = "SELECT * FROM actor " + "join film_actor on actor.id = film_actor.actor_id "
				+ "join film on film.id = film_actor.film_id " + "WHERE film.id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet res = stmt.executeQuery();

			while (res.next())
				actorList.add(new Actor(res.getInt("id"), res.getString("first_name"), res.getString("last_name")));

			res.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

	public Film createFilm(String title, String description, int releaseYear) {

		Film newFilm = new Film(0, title, description, releaseYear, 1, 2, 0.0, 2, 0.0, "", "", null, "");
		String user = "student";
		String pass = "student";
		String sql = "Insert into film (title, description, release_year, language_id, length, replacement_cost) "
				+ "VALUES (?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setInt(3, releaseYear);
			stmt.setInt(4, newFilm.getLanguageId());
//			stmt.setInt(5, newFilm.getRentalDuration());
//			stmt.setDouble(6, newFilm.getRentalRate());
			stmt.setInt(5, newFilm.getLength());
			stmt.setDouble(6, newFilm.getReplacementCost());
//			stmt.setString(9, newFilm.getRating());
//			stmt.setString(10, newFilm.getSpecialFeatures());

			conn.setAutoCommit(false);
			int uc = stmt.executeUpdate();

			if (uc == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					newFilm.setId(keys.getInt(1));
					conn.commit();
				}
				stmt.close();
				conn.close();
				return newFilm;
			}

			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
//			if (conn != null) {
//				try {
//					conn.rollback();
//				} catch (SQLException sqle2) {
//					System.err.println("Error trying to rollback");
//				}
//			}

		}
		return null;
	}

	public void deleteFilm() {
		String user = "student";
		String pass = "student";
		String sql = "Delete from film where id = ?";
		String sql2 = "SELECT LAST_INSERT_ID();";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.executeQuery();
			int id = rs.getInt(1);
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
		}

	}
}
