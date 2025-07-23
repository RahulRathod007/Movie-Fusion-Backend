package org.moviefusion.controller;

import org.moviefusion.model.Rating;
import org.moviefusion.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@CrossOrigin("http://localhost:3000")
public class RatingController {
	
	@Autowired 
	RatingService ratingService;
	 
	@PostMapping("/rating/addrating")
	public String addRating(@RequestBody Rating rating)
	{
		//System.out.println(rating.toString());
		boolean added = ratingService.addRating(rating);
		if(added)
			return "Rating Added";
		else
			return "You have Already given rating to this Movie...!!";
	}

}
 