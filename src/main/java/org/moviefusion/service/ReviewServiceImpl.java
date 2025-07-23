package org.moviefusion.service;

import org.moviefusion.model.Review;
import org.moviefusion.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewRepository reviewRepo;

	@Override
	public boolean addReview(Review review) {
		boolean exists = reviewRepo.existsByUserIdAndMovieId(review.getUser_id(), review.getMovie_id());
	    if (exists) {
	        return false; // Already exists
	    } else {
	    	reviewRepo.addReview(review); // Custom insert method
	        return true;
	    }
	} 
} 
