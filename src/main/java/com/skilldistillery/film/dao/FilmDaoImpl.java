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
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();

			if (res.next()) {
				cast = findActorsByFilmId(id);
				String s = ""+res.getInt("release_year");
				film = new Film(res.getInt("id"), res.getString("title"), res.getString("description"),
						s, res.getInt("language_id"), res.getInt("rental_duration"),
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

	public List<Film> filmSearchKeyword(String keyword) {
		Film film = null;
		List<Actor> cast = new ArrayList<>();
		List<Film> filmList = new ArrayList<>();
		String user = "student";
		String pass = "student";
		String sql = "select * FROM film join language on film.language_id = language.id WHERE film.title like ? or film.description like ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				String s = "" + res.getInt("release_year");
				cast = findActorsByFilmId(res.getInt("film.id"));
				film = new Film(res.getInt("id"), res.getString("title"), res.getString("description"),
						s, res.getInt("language_id"), res.getInt("rental_duration"),
						res.getDouble("rental_rate"), res.getInt("length"), res.getDouble("replacement_cost"),
						res.getString("rating"), res.getString("special_features"), cast,
						res.getString("language.name"));
				filmList.add(film);
			}

			res.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return filmList;
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

	public Film createFilm(String title, String description, String releaseYear, int rentalDuration, double rentalRate, int length, double replacementCost, String rating, String specialFeatures) {

		Film newFilm = new Film(0, title, description, releaseYear, 1, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, null, "English");
		String user = "student";
		String pass = "student";
		String sql = "Insert into film (title, description, language_id, rental_duration, rental_rate, length, replacement_cost, rating) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, title);
			stmt.setString(2, description);
//			stmt.setString(3, releaseYear);
			stmt.setInt(3, newFilm.getLanguageId());
			stmt.setInt(4, newFilm.getRentalDuration());
			stmt.setDouble(5, newFilm.getRentalRate());
			stmt.setInt(6, newFilm.getLength());
			stmt.setDouble(7, newFilm.getReplacementCost());
			stmt.setString(8, newFilm.getRating());
//			stmt.setString(9, newFilm.getSpecialFeatures());

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
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}

		}
		return null;
	}

	public boolean deleteFilm(int id) {
		String user = "student";
		String pass = "student";
		String sql = "Delete from film where id = ?";
		boolean result = true;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			result = false;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
		}
		return result;
	}

	public boolean modifyFilm(int id, String title, int releaseYear, String description, int languageId,
			int rentalDuration, double rentalRate, int length, double replacementCost, String rating,
			String specialFeatures) {

		String user = "student";
		String pass = "student";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, "
					+ "rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ? "
					+ "WHERE film.id=?";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setInt(3, releaseYear);
			stmt.setInt(4, languageId);
			stmt.setInt(5, rentalDuration);
			stmt.setDouble(6, rentalRate);
			stmt.setInt(7, length);
			stmt.setDouble(8, replacementCost);
			stmt.setString(9, rating);
//			stmt.setString(10, specialFeatures);
			stmt.setInt(10, id);

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public String findCategory(int id) {

		String user = "student";
		String pass = "student";
		String sql = "SELECT category from film_list join film on film.id = film_list.FID  where film.id = ? ";
		String filmId = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet res = stmt.executeQuery();

			while (res.next()) {

				filmId = res.getString(1);

			}

			res.close();
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return filmId;
	}
	
	public Actor addActor(String firstName, String lastName) {
		Actor actor = new Actor(0, firstName, lastName);
		String user = "student";
		String pass = "student";
		String sql = "Insert into actor (first_name, last_name) values (?, ?) ";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			
			conn.setAutoCommit(false);
			int uc = stmt.executeUpdate();
			
			if (uc == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					actor.setId(keys.getInt(1));
					conn.commit();
				}
				stmt.close();
				conn.close();
				return actor;
			}
			stmt.close();
			conn.close();
		}

		catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
		}
		
	}
		return null;
}
	
	public Film addActorToFilm(int filmid, String firstName, String lastName) {
		Film updatedFilm = null;
		
		String user = "student";
		String pass = "student";
		String sql = "Insert into actor (first_name, last_name) values (?, ?) ";
		String sql2= "Insert into film_actor (film_id, actor_id) values (?, ?) ";
		int actorId;
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			
			conn.setAutoCommit(false);
			int uc = stmt.executeUpdate();
			
			if (uc == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					actorId=keys.getInt(1);
					
					stmt = conn.prepareStatement(sql2);
					stmt.setInt(1, filmid);
					stmt.setInt(2, actorId);
					int uc2 = stmt.executeUpdate();
					conn.commit();
					updatedFilm = filmSearchId(filmid);
					stmt.close();
					conn.close();
					return updatedFilm;
				}
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
		}
		
	}
		return null;
	}
	
	public boolean deleteActorFromFilm(int filmid, int actorid) {
		String user = "student";
		String pass = "student";
		String sql = "Delete from film_actor where film_id = ? AND actor_id = ?";
		boolean result = true;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmid);
			stmt.setInt(2,  actorid);
			stmt.executeUpdate();
//			conn.commit();
			conn.close();
			stmt.close();

		} catch (SQLException e) {
			result = false;
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
		}
		return result;
	}
}

