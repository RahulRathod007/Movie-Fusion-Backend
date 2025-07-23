package org.moviefusion.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.moviefusion.model.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	List<MovieInfo> list;
	
	@Override
	public boolean isAddNewMovie(MovieInfo movieInfo) {
		 String sql = "INSERT INTO Movie_Info "
	         		+ "(original_movie_id, movie_title, movie_mapping_name, movie_description, movie_category, movie_director_name, movie_actor1, movie_actor2, movie_actor3,"
	         		+ " movie_language, movie_trailer_link, "
	         		+ "watch_link, movie_duration, movie_budget, movie_release_date, movie_country) "
	         		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		int value = jdbcTemplate.update(sql,
				movieInfo.getOriginal_movie_id(),
				movieInfo.getMovie_title(),
				movieInfo.getMovie_mapping_name(),
				movieInfo.getMovie_description(),
				movieInfo.getMovie_category(),
				movieInfo.getMovie_director_name(),
                movieInfo.getMovie_actor1(),
                movieInfo.getMovie_actor2(),
                movieInfo.getMovie_actor3(),
                movieInfo.getMovie_language(),    
                movieInfo.getMovie_trailer_link(),
                movieInfo.getWatch_link(),
                movieInfo.getMovie_duration(),
                movieInfo.getMovie_budget(),
                movieInfo.getMovie_release_date(),
                movieInfo.getMovie_country()
				);
		return value> 0;
	}

	@Override
	public List<MovieInfo> getAllMovies() {
		list = jdbcTemplate.query("select * from movie_info", new RowMapper<MovieInfo>() {

			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				 MovieInfo movieInfo = new MovieInfo();
		            movieInfo.setMovie_id(rs.getInt(1));
		            movieInfo.setOriginal_movie_id(rs.getInt(2));
		            movieInfo.setMovie_title(rs.getString(3));
		            movieInfo.setMovie_mapping_name(rs.getString(4));
		            movieInfo.setMovie_description(rs.getString(5));
		            movieInfo.setMovie_category(rs.getString(6));
		            movieInfo.setMovie_director_name(rs.getString(7));
		            movieInfo.setMovie_actor1(rs.getString(8));
		            movieInfo.setMovie_actor2(rs.getString(9));
		            movieInfo.setMovie_actor3(rs.getString(10));
		            movieInfo.setMovie_language(rs.getString(11));	        
		            movieInfo.setMovie_trailer_link(rs.getString(12));
		            movieInfo.setWatch_link(rs.getString(13));
		            movieInfo.setMovie_duration(rs.getString(14));
		            movieInfo.setMovie_budget(rs.getBigDecimal(15));
		            movieInfo.setMovie_release_date(rs.getObject(16, java.time.LocalDate.class));
		            movieInfo.setMovie_country(rs.getString(17));
		            return movieInfo;
			}});
		return list;
	}

	@Override
	public MovieInfo getMovieByTitle(String movie_title) {
		String sql = "SELECT * FROM Movie_Info where movie_title = ?";
		List<MovieInfo> movieInfo = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MovieInfo.class), movie_title);
		return movieInfo.isEmpty() ? null : movieInfo.get(0);
	}
	
	@Override
	public List<MovieInfo> getMovieByCategory(String movie_category)
	{
		String sql = "SELECT * FROM Movie_Info where movie_category LIKE ?";
		String likeQuery = "%" + movie_category + "%";
		list = jdbcTemplate.query(sql, new RowMapper<MovieInfo>() {
			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				 MovieInfo movieInfo = new MovieInfo();
				 	movieInfo.setMovie_id(rs.getInt(1));
		            movieInfo.setOriginal_movie_id(rs.getInt(2));
		            movieInfo.setMovie_title(rs.getString(3));
		            movieInfo.setMovie_mapping_name(rs.getString(4));
		            movieInfo.setMovie_description(rs.getString(5));
		            movieInfo.setMovie_category(rs.getString(6));
		            movieInfo.setMovie_director_name(rs.getString(7));
		            movieInfo.setMovie_actor1(rs.getString(8));
		            movieInfo.setMovie_actor2(rs.getString(9));
		            movieInfo.setMovie_actor3(rs.getString(10));
		            movieInfo.setMovie_language(rs.getString(11));	        
		            movieInfo.setMovie_trailer_link(rs.getString(12));
		            movieInfo.setWatch_link(rs.getString(13));
		            movieInfo.setMovie_duration(rs.getString(14));
		            movieInfo.setMovie_budget(rs.getBigDecimal(15));
		            movieInfo.setMovie_release_date(rs.getObject(16, java.time.LocalDate.class));
		            movieInfo.setMovie_country(rs.getString(17));
		            
		            return movieInfo;
			}
			}, likeQuery);
		return list;
	}

	@Override
	public List<MovieInfo> searchMovies(String query) {
	    String sql = "SELECT * FROM Movie_Info WHERE "
	            + "movie_title LIKE ? OR "
	            + "movie_mapping_name LIKE ? OR "
	            + "movie_description LIKE ? OR "
	            + "movie_category LIKE ? OR "
	            + "movie_director_name LIKE ? OR "
	            + "movie_actor1 LIKE ? OR "
	            + "movie_actor2 LIKE ? OR "
	            + "movie_actor3 LIKE ? OR "
	            + "movie_language LIKE ? OR "	         
	            + "movie_country LIKE ?";

	    String likeQuery = "%" + query + "%"; 

	    return jdbcTemplate.query(sql, new RowMapper<MovieInfo>() {
	        @Override
	        public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	            MovieInfo movieInfo = new MovieInfo();
	            movieInfo.setMovie_id(rs.getInt(1));
	            movieInfo.setOriginal_movie_id(rs.getInt(2));
	            movieInfo.setMovie_title(rs.getString(3));
	            movieInfo.setMovie_mapping_name(rs.getString(4));
	            movieInfo.setMovie_description(rs.getString(5));
	            movieInfo.setMovie_category(rs.getString(6));
	            movieInfo.setMovie_director_name(rs.getString(7));
	            movieInfo.setMovie_actor1(rs.getString(8));
	            movieInfo.setMovie_actor2(rs.getString(9));
	            movieInfo.setMovie_actor3(rs.getString(10));
	            movieInfo.setMovie_language(rs.getString(11));	        
	            movieInfo.setMovie_trailer_link(rs.getString(12));
	            movieInfo.setWatch_link(rs.getString(13));
	            movieInfo.setMovie_duration(rs.getString(14));
	            movieInfo.setMovie_budget(rs.getBigDecimal(15));
	            movieInfo.setMovie_release_date(rs.getObject(16, java.time.LocalDate.class));
	            movieInfo.setMovie_country(rs.getString(17));
	            return movieInfo;
	        }
	    }, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery, likeQuery);
	}

	@Override
	public boolean deleteMovieById(int deleteId) {
		String sql = "DELETE FROM Movie_info where movie_id = ?";
		int value = jdbcTemplate.update(sql , deleteId);	
		return value> 0;
	}

	
	@Override
	public MovieInfo getMovieById(int movieId) {
		String sql = "SELECT * FROM Movie_info where movie_id = ?";
		List<MovieInfo> movieList =	jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MovieInfo.class), movieId);
		return movieList.isEmpty() ? null : movieList.get(0);
	}

	@Override
	public boolean updateMovieById(int movieId, MovieInfo movieinfo) {
	    String sql = "UPDATE Movie_info SET movie_title = ?, movie_mapping_name = ?, movie_description = ?, "
	            + "movie_category = ?, movie_director_name = ?, movie_actor1 = ?, movie_actor2 = ?, movie_actor3 = ?, "
	            + "movie_language = ?, movie_trailer_link = ?, watch_link = ?, movie_duration = ?, movie_budget = ?, movie_release_date = ?, movie_country = ? "
	            + "WHERE movie_id = ?";  

	    int value = jdbcTemplate.update(sql,
	           movieinfo.getMovie_title(),
	           movieinfo.getMovie_mapping_name(),
	           movieinfo.getMovie_description(),
	           movieinfo.getMovie_category(),
	           movieinfo.getMovie_director_name(),
	           movieinfo.getMovie_actor1(),
	           movieinfo.getMovie_actor2(),
	           movieinfo.getMovie_actor3(),
	           movieinfo.getMovie_language(),
	           movieinfo.getMovie_trailer_link(),
	           movieinfo.getWatch_link(),
	           movieinfo.getMovie_duration(),
	           movieinfo.getMovie_budget(),
	           movieinfo.getMovie_release_date(),
	           movieinfo.getMovie_country(),
	           
	           movieId 
	    );

	    return value > 0;
	}

	@Override
	public List<String> getAllGenres()
	{
		String sql = "SELECT movie_category FROM movie_info WHERE movie_category IS NOT NULL";
		List<String> categories = jdbcTemplate.queryForList(sql, String.class);
		
		Set<String> genreSet = new HashSet<>();
		
		for(String category : categories) {
			if(category != null && !category.isEmpty()) 
			{
				String[] genres = category.split(",");
				for(String genre : genres){
					genreSet.add(genre.trim());
				}
 			}
		}
		
		List<String> genreList = new ArrayList<String>(genreSet);
		Collections.sort(genreList);
		return genreList;
	}
	
	@Override
	public List<String> getAllGenresByLangauge(String selectedLanguage) {
	    
	    String sql = "SELECT movie_category FROM movie_info WHERE movie_category IS NOT NULL AND movie_language = ?";
	    List<String> categories = jdbcTemplate.queryForList(sql, String.class, selectedLanguage);
	    
	    Set<String> genreSet = new HashSet<>();
	    
	    for(String category : categories) {
	        if(category != null && !category.isEmpty()) {
	            String[] genres = category.split(",");
	            for(String genre : genres){
	                genreSet.add(genre.trim());
	            }
	        }
	    }
	    
	    List<String> genreList = new ArrayList<>(genreSet);
	    Collections.sort(genreList);
	    return genreList;
	}


	@Override
	public List<MovieInfo> getMoviesByLanguageAndGenre(String movie_language, String genres) {
		String sql = "SELECT * FROM movie_info WHERE movie_language = ? && movie_category like  ?";
		String likeQuery = "%" + genres + "%";
		list = jdbcTemplate.query(sql, new RowMapper<MovieInfo>() {
			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				 MovieInfo movieInfo = new MovieInfo();
				 	movieInfo.setMovie_id(rs.getInt(1));
		            movieInfo.setOriginal_movie_id(rs.getInt(2));
		            movieInfo.setMovie_title(rs.getString(3));
		            movieInfo.setMovie_mapping_name(rs.getString(4));
		            movieInfo.setMovie_description(rs.getString(5));
		            movieInfo.setMovie_category(rs.getString(6));
		            movieInfo.setMovie_director_name(rs.getString(7));
		            movieInfo.setMovie_actor1(rs.getString(8));
		            movieInfo.setMovie_actor2(rs.getString(9));
		            movieInfo.setMovie_actor3(rs.getString(10));
		            movieInfo.setMovie_language(rs.getString(11));	        
		            movieInfo.setMovie_trailer_link(rs.getString(12));
		            movieInfo.setWatch_link(rs.getString(13));
		            movieInfo.setMovie_duration(rs.getString(14));
		            movieInfo.setMovie_budget(rs.getBigDecimal(15));
		            movieInfo.setMovie_release_date(rs.getObject(16, java.time.LocalDate.class));
		            movieInfo.setMovie_country(rs.getString(17));
		            
		            return movieInfo;
			}
			}, movie_language, likeQuery);
		return list;
	}

	@Override
	public List<Map<String, Object>> getMovieCountByLanguage() {
	    String sql = "SELECT movie_language, COUNT(*) AS movie_count FROM movie_info GROUP BY movie_language ORDER BY movie_count DESC";
	    return jdbcTemplate.queryForList(sql);
	}


}
