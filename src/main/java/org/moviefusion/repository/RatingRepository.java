package org.moviefusion.repository;

import org.moviefusion.model.Rating;

public interface RatingRepository { 
	
	public boolean addRating(Rating rating);

	boolean existsByUserIdAndMovieId(int userId, int movieId);

}
 