package org.moviefusion.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.moviefusion.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class RatingRepositoryImpl implements RatingRepository{
	 
	@Autowired
	JdbcTemplate jdbcTemplate; 

	@Override
	public boolean addRating(Rating rating) {
		String sql = "INSERT INTO Rating(user_id, movie_id, rating_value) VALUES(?, ?, ?)";
		int value = jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, rating.getUser_id());
				ps.setInt(2, rating.getMovie_id());
				ps.setDouble(3, rating.getRating_value());
			}});
		return value > 0;
	}
	
	@Override
	public boolean existsByUserIdAndMovieId(int userId, int movieId) {
	    String sql = "SELECT COUNT(*) FROM Rating WHERE user_id = ? AND movie_id = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, movieId);
	    return count != null && count > 0;
	}

}
