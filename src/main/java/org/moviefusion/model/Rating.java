package org.moviefusion.model;

import lombok.Data;

@Data
public class Rating {
	
	private int rating_id;
	private int user_id;
	private int movie_id;
	private double rating_value;

}
 