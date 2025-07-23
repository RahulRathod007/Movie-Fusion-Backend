package org.moviefusion.service;

import java.util.List;

import org.moviefusion.model.Watchlist;

public interface WatchListService {
	
	public boolean isAddMovieWatchList(int userId, int movieId);
	
	public List<Watchlist> getAllWatchList(int userId);
	
	public boolean removeMovieFromWatchlist(int userId, int movieId);

}
