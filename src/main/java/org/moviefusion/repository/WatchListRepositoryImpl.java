package org.moviefusion.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.moviefusion.model.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WatchListRepositoryImpl  implements WatchListRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean isAddMovieWatchList(int userId, int movieId) {
		
		String sql = "INSERT INTO Watchlist (user_id, movie_id) VALUES(?, ?)";
		int value = jdbcTemplate.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, userId);
				ps.setInt(2, movieId);
				
			}});
		return value>0;
	}

	@Override
	public boolean existsByUserIdAndMovieId(int userId, int movieId) {
	    String sql = "SELECT COUNT(*) FROM Watchlist WHERE user_id = ? AND movie_id = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, movieId);
	    return count != null && count > 0;
	}

	@Override
	public List<Watchlist> getAllWatchList(int userId) {
		String sql = "SELECT * FROM Watchlist where user_id = ?";
		List<Watchlist> watchList = new ArrayList<Watchlist>();
		watchList = jdbcTemplate.query(sql, new RowMapper<Watchlist>() {
			@Override
			public Watchlist mapRow(ResultSet rs, int rowNum) throws SQLException {
				Watchlist list = new Watchlist();
				 list.setMovie_id(rs.getInt("movie_id"));
				return list;
			}}, userId);
		return watchList;
	}

	@Override
	public boolean removeMovieFromWatchlist(int userId, int movieId) {
		String sql = "DELETE FROM Watchlist where user_id = ? AND movie_id = ? ";
		int value = jdbcTemplate.update(sql, userId, movieId);
		return value > 0;
	}

}
