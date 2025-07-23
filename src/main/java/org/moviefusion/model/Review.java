package org.moviefusion.model;

import lombok.Data;

@Data
public class Review {

	private int review_id;
	private int user_id;
	private int movie_id;
	private String review_text;

}
