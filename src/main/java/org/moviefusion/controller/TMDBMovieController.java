package org.moviefusion.controller;

import org.moviefusion.service.TMDbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class TMDBMovieController {

    @Autowired
    private TMDbServiceImpl tmdbService;
    
//    @GetMapping("/fetch-popular")
//    public ResponseEntity<String> fetchPopularMovies() {
//    	tmdbService.fetchAndSavePopularMovies();
//        return ResponseEntity.ok("Movies fetched and saved.");
//    }
    
    @GetMapping("/all")
    public ResponseEntity<String> fetchPopularMovies() {
    	tmdbService.fetchAndSavePopularMovies();
        return ResponseEntity.ok("Movies fetched and saved.");
    }
    
    
}

