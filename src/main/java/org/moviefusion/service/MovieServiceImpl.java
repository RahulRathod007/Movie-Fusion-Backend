package org.moviefusion.service;

import java.util.List;
import java.util.Map;

import org.moviefusion.model.MovieInfo;
import org.moviefusion.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepo;
	@Override
	public boolean isAddNewMovie(MovieInfo movieInfo) {
		return movieRepo.isAddNewMovie(movieInfo);
	}
	
	@Override
	public List<MovieInfo> getAllMovies() {
		return movieRepo.getAllMovies();
	}
	
	@Override
	public MovieInfo getMovieByTitle(String movie_title) {
		return movieRepo.getMovieByTitle(movie_title);
	}
	
	@Override
	public List<MovieInfo> getMovieByCategory(String movie_category) { 
		return movieRepo.getMovieByCategory(movie_category);
	}
	
	@Override
	public List<MovieInfo> searchMovies(String query) {
		return movieRepo.searchMovies(query);
	}

	@Override
	public boolean deleteMovieById(int deleteId) {
		return movieRepo.deleteMovieById(deleteId);
	}

	@Override
	public MovieInfo getMovieById(int movieId) {
		return movieRepo.getMovieById(movieId);
	}

	@Override
	public boolean updateMovieById(int movieId, MovieInfo movieinfo) {
		return movieRepo.updateMovieById(movieId, movieinfo);
	}

	@Override
	public List<String> getAllGenres() {
		return movieRepo.getAllGenres();
	}
	
	@Override
	public List<String> getAllGenresByLangauge(String selectedLanguage) {
		return movieRepo.getAllGenresByLangauge(selectedLanguage);
	}

	@Override
	public List<MovieInfo> getMoviesByLanguageAndGenre(String movie_langauge, String genre) {
		return movieRepo.getMoviesByLanguageAndGenre(movie_langauge, genre);
	}

	@Override
	public List<Map<String, Object>> getMovieCountByLanguage() {		
		return movieRepo.getMovieCountByLanguage();
	}
	

}
