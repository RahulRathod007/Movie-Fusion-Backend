package org.moviefusion.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.moviefusion.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate; 

	@Override
	public boolean addReview(Review review) {
		String sql = "INSERT INTO Review(user_id, movie_id, review_text) VALUES(?, ?, ?)";
		int value = jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
		 	public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, review.getUser_id());
				ps.setInt(2, review.getMovie_id());
				ps.setString(3, review.getReview_text());	
			}});
		return value > 0;
	}
	
	@Override
	public boolean existsByUserIdAndMovieId(int userId, int movieId) {
	    String sql = "SELECT COUNT(*) FROM Review WHERE user_id = ? AND movie_id = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, movieId);
	    return count != null && count > 0;
	}

}
