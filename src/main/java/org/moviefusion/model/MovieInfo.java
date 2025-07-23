package org.moviefusion.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovieInfo {

	private int movie_id;
	private int original_movie_id;
	private String movie_title;
	private String movie_mapping_name;
	private String movie_description;
	private String movie_category;
	private String movie_director_name;
	private String movie_actor1;
	private String movie_actor2;
	private String movie_actor3;
	private String movie_language;
	private String movie_trailer_link;
	private String watch_link;
	private String movie_duration;
	private BigDecimal movie_budget;
	private LocalDate movie_release_date;
	private String movie_country;
	
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public String getMovie_mapping_name() {
		return movie_mapping_name;
	}
	public void setMovie_mapping_name(String movie_mapping_name) {
		this.movie_mapping_name = movie_mapping_name;
	}
	public String getMovie_description() {
		return movie_description;
	}
	public void setMovie_description(String movie_description) {
		this.movie_description = movie_description;
	}
	public String getMovie_category() {
		return movie_category;
	}
	public void setMovie_category(String movie_category) {
		this.movie_category = movie_category;
	}
	public String getMovie_director_name() {
		return movie_director_name;
	}
	public void setMovie_director_name(String movie_director_name) {
		this.movie_director_name = movie_director_name;
	}
	public String getMovie_actor1() {
		return movie_actor1;
	}
	public void setMovie_actor1(String movie_actor1) {
		this.movie_actor1 = movie_actor1;
	}
	public String getMovie_actor2() {
		return movie_actor2;
	}
	public void setMovie_actor2(String movie_actor2) {
		this.movie_actor2 = movie_actor2;
	}
	public String getMovie_actor3() {
		return movie_actor3;
	}
	public void setMovie_actor3(String movie_actor3) {
		this.movie_actor3 = movie_actor3;
	}
	public String getMovie_language() {
		return movie_language;
	}
	public void setMovie_language(String movie_language) {
		this.movie_language = movie_language;
	}

	public String getMovie_trailer_link() {
		return movie_trailer_link;
	}
	public void setMovie_trailer_link(String movie_trailer_link) {
		this.movie_trailer_link = movie_trailer_link;
	}
	public String getWatch_link() {
		return watch_link;
	}
	public void setWatch_link(String watch_link) {
		this.watch_link = watch_link;
	}
	public BigDecimal getMovie_budget() {
		return movie_budget;
	}
	public void setMovie_budget(BigDecimal movie_budget) {
		this.movie_budget = movie_budget;
	}
	public LocalDate getMovie_release_date() {
		return movie_release_date;
	}
	public void setMovie_release_date(LocalDate movie_release_date) {
		this.movie_release_date = movie_release_date;
	}
	public String getMovie_country() {
		return movie_country;
	}
	public void setMovie_country(String movie_country) {
		this.movie_country = movie_country;
	}
	public String getMovie_duration() {
		return movie_duration;
	}
	public void setMovie_duration(String movie_duration) {
		this.movie_duration = movie_duration;
	}
	public int getOriginal_movie_id() {
		return original_movie_id;
	}
	public void setOriginal_movie_id(int original_movie_id) {
		this.original_movie_id = original_movie_id;
	}
	
	
	

}
