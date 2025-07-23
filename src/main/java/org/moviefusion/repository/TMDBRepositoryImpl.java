package org.moviefusion.repository;

import org.moviefusion.model.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TMDBRepositoryImpl {
 
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    public void saveMovies(MovieInfo movie) {
        String sql = "INSERT INTO Movie_Info "
                + "(original_movie_id, movie_title, movie_mapping_name, movie_description, movie_category, movie_director_name, "
                + "movie_actor1, movie_actor2, movie_actor3, movie_language, movie_trailer_link, "
                + "watch_link, movie_duration, movie_budget, movie_release_date, movie_country) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql,
            		movie.getOriginal_movie_id(),
                    movie.getMovie_title(),
                    movie.getMovie_mapping_name(),
                    movie.getMovie_description(),
                    movie.getMovie_category(),
                    movie.getMovie_director_name(),
                    movie.getMovie_actor1(),
                    movie.getMovie_actor2(),
                    movie.getMovie_actor3(),
                    movie.getMovie_language(),
                    movie.getMovie_trailer_link(),
                    movie.getWatch_link(),
                    movie.getMovie_duration(),
                    movie.getMovie_budget(),
                    movie.getMovie_release_date(),
                    movie.getMovie_country()
            );
            System.out.println("Movie saved: " + movie.getMovie_title());
        } catch (DataAccessException e) {
            System.err.println("Error inserting movie: " + movie.getMovie_title());
            System.err.println("Cause: " + e.getCause());
            System.err.println("Message: " + e.getMessage());
            // Optionally rethrow or log using a logging framework
        }
    }

	
	    public boolean checkMovieExistsByMovieTitle(String movieTitle) {
	    	String sql = "SELECT COUNT(*) FROM movie_info WHERE movie_title = ?";
	    	 Integer count = jdbcTemplate.queryForObject(sql, Integer.class, movieTitle );
	 	    return count != null && count > 0;
	    }

}

