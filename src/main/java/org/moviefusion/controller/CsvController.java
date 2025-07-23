//package org.moviefusion.controller;
//
//import org.moviefusion.service.CsvMovieImportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.opencsv.exceptions.CsvException;
//
//import java.io.IOException;
//
//@RestController
//public class CsvController {
//
//    @Autowired
//    private CsvMovieImportService service;
//
//    @GetMapping("/importMovies")
//    public String importMovies() {
//        // You can externalize these to application.properties if needed
//        String moviePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/movie.csv";
//        String creditsPath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/cr.csv";
//
//        try {
//            boolean success = service.importMoviesAndCredits(moviePath, creditsPath);
//            return success ? "Movie data import Done...!!" : "Movie data import Failed...!!";
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//            return "Exception occurred: " + e.getMessage();
//        }
//    }
//}
