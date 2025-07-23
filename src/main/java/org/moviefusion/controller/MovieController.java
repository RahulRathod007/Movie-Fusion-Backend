package org.moviefusion.controller;

import java.util.*;

import org.moviefusion.model.MovieInfo;
import org.moviefusion.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class MovieController {

	@Autowired 
	MovieService movieService;

	List<MovieInfo> list;

	@PostMapping("movie/addmovie")
	public String isAddNewMovie(@RequestBody MovieInfo movieInfo) {
		if (movieService.isAddNewMovie(movieInfo))
			return "Movie Added....!!";
		else
			return "Movie adding failed..!!";
	}

	@GetMapping("/movie/movies")
	public List<MovieInfo> getAllMovies() {
		list = movieService.getAllMovies();
		if (!list.isEmpty())
			return list;
		else
			return null;
	}

	@GetMapping("movie/movietitle/{movietitle}")
	public MovieInfo getMovieByTitle(@PathVariable("movietitle") String movie_title) {
		return movieService.getMovieByTitle(movie_title);
	}

	@GetMapping("/movie/category/{moviecategory}")
	public List<MovieInfo> getMovieByCategory(@PathVariable("moviecategory") String movie_category) {
		list = movieService.getMovieByCategory(movie_category);
		if (!list.isEmpty()) {
			return list;
		} else
			return null;
	}

	@GetMapping("/movie/search/{query_}")
	public List<MovieInfo> searchMovies(@PathVariable("query_") String query) {
		List<MovieInfo> list = movieService.searchMovies(query);
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}

	@DeleteMapping("/movie/delete/{deleteid}")
	public String deleteMovieById(@PathVariable("deleteid") Integer deleteId) {
		if (movieService.deleteMovieById(deleteId))
			return "Movie Deleted...!!";
		else
			return "Movie Not Deleted.....!!";
	}

	@GetMapping("/movie/movieid/{movieid}")
	public MovieInfo getMovieById(@PathVariable("movieid") int movieId) {
		MovieInfo movie = movieService.getMovieById(movieId);
		if (movie != null)
			return movie;
		return null;
	}

	@PutMapping("/movie/update/{movieid}")
	public String updateMovieById(@PathVariable("movieid") Integer movieId, @RequestBody MovieInfo movieInfo) {
		if (movieService.updateMovieById(movieId, movieInfo))
			return "Movie Updated...!!";
		else
			return "Movie not updated...!!";
	}

	@GetMapping("/movie/getallgenres")
	public List<String> getAllGenres() {
		List<String> genres = movieService.getAllGenres();
		return genres;
	}
	
	@GetMapping("/movie/getallgenresbylanguage/{selectedlanguage}")
	public List<String> getAllGenresByLangauge(@PathVariable ("selectedlanguage") String selectedLanguage) {
		List<String> genres = movieService.getAllGenresByLangauge(selectedLanguage);
		return genres;
	}
	
	@GetMapping("/movie/getmoviesbylangaugewithgenre/{language}/{genre}")
	public List<MovieInfo> getMoviesByLanguageAndGenre(@PathVariable("language") String movie_language, @PathVariable("genre") String genres){
		
//		System.out.println(movie_language + " " + genres);
		list = movieService.getMoviesByLanguageAndGenre(movie_language, genres);
		if (!list.isEmpty()) {
			return list;
		} else
			return null;
	}
	
	
	
	@GetMapping("/movie/getmoviecountbylanguage")
	public List<Map<String, Object>> getMovieCountByLanguage(){
		 List<Map<String, Object>> map = movieService.getMovieCountByLanguage();
//	      System.out.println(map);
	       return map;
	}
	

}
