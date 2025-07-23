package org.moviefusion.repository;

import org.moviefusion.model.Review;

public interface ReviewRepository {
	 
	public boolean addReview(Review review);

	boolean existsByUserIdAndMovieId(int userId, int movieId);

} 
