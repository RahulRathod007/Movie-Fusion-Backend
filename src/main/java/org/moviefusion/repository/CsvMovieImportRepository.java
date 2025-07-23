//package org.moviefusion.repository;
//
//import org.moviefusion.model.Movie;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class CsvMovieImportRepository {
//	
//	 @Autowired
//	    private JdbcTemplate jdbcTemplate;
//		
//		    public void saveMovies(Movie movie) {
//		    	 String sql = "INSERT INTO Movie_Info "
//		         		+ "(movie_title, movie_mapping_name, movie_description, movie_category, movie_director_name, movie_actor1, movie_actor2, movie_actor3,"
//		         		+ " movie_language, movie_type, movie_trailer_link, "
//		         		+ "watch_link, movie_budget, movie_release_date, movie_country) "
//		         		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//		        jdbcTemplate.update(sql,
//		        		movie.getMovie_title(),
//		                movie.getMovie_mapping_name(), 
//		                movie.getMovie_description(),
//		                movie.getMovie_category(), 
//		                movie.getMovie_director_name(),
//		                movie.getMovie_actor1(),
//		                movie.getMovie_actor2(),
//		                movie.getMovie_actor3(),
//		                movie.getMovie_language(),
//		                movie.getMovie_type(),
//		                movie.getMovie_trailer_link(),
//		                movie.getWatch_link(),
//		                movie.getMovie_budget(),
//		                movie.getMovie_release_date(),
//		                movie.getMovie_country()
//		        );
//		    }
//
//}
