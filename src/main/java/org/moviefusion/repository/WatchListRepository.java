package org.moviefusion.repository;

import java.util.List;

import org.moviefusion.model.Watchlist;

public interface WatchListRepository {

	public boolean isAddMovieWatchList(int userId, int movieId);
	
	public boolean existsByUserIdAndMovieId(int userId, int movieId);
	
	public List<Watchlist> getAllWatchList(int userId);
	
	public boolean removeMovieFromWatchlist(int userId, int movieId);
}
