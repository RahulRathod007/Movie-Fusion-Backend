package org.moviefusion.service;

import java.util.List;

import org.moviefusion.model.Watchlist;
import org.moviefusion.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchListServiceImpl implements WatchListService {

	@Autowired
	WatchListRepository watchListRepo;

	@Override
	public boolean isAddMovieWatchList(int userId, int movieId) {
	    boolean exists = watchListRepo.existsByUserIdAndMovieId(userId, movieId);
	    if (exists) {
	        return false; // Already exists
	    } else {
	        watchListRepo.isAddMovieWatchList(userId, movieId); // Custom insert method
	        return true;
	    }
	}

	@Override
	public List<Watchlist> getAllWatchList(int userId) {
		return watchListRepo.getAllWatchList(userId);
	}

	@Override
	public boolean removeMovieFromWatchlist(int userId, int movieId) {
		return watchListRepo.removeMovieFromWatchlist(userId, movieId);
	}
}
