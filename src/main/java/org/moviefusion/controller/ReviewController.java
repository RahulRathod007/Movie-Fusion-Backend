package org.moviefusion.controller;

import org.moviefusion.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.moviefusion.model.Review;

@RestController
@CrossOrigin("http://localhost:3000")
public class ReviewController {

	@Autowired 
	ReviewService reviewService;
	
	@PostMapping("/review/addreview")
	public String addReview(@RequestBody Review review){
		System.out.println(review.toString());
		boolean added = reviewService.addReview(review);
		if(added)
			return "Review Added";
		else
			return "You have already reviewed this movie!";
	}
}
