//package org.moviefusion.service;
//
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvException;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.moviefusion.model.Movie;
//import org.moviefusion.repository.CsvMovieImportRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Service
//public class CsvMovieImportService {
//
//    @Autowired
//    private CsvMovieImportRepository repo; 
//
//    public boolean importMoviesAndCredits(String moviesCsvPath, String creditsCsvPath) throws IOException, CsvException {
//        
//    	boolean b = false;
//        // 1. Load movies.csv
//        Map<String, Map<String, String>> moviesMap = loadCsvToMap(moviesCsvPath);
//
//        // 2. Load credits.csv
//        Map<String, Map<String, String>> creditsMap = loadCsvToMap(creditsCsvPath);
//
//        // 3. Process each movie
//        for (String movieId : moviesMap.keySet()) {
//            Map<String, String> movieData = moviesMap.get(movieId);
//            Map<String, String> creditData = creditsMap.get(movieId); // can be null
//
//            Movie movie = new Movie(); 
//            
//            // Fill movie data
//            movie.setMovie_title(movieData.get("title")); 
//            movie.setMovie_mapping_name(movieData.get("original_title")); 
//            movie.setMovie_description(movieData.get("overview"));
//            movie.setMovie_category(extractGenres(movieData.get("genres")));
//            movie.setMovie_language(movieData.get("original_language"));
//            movie.setMovie_type(movieData.get("status"));
//            movie.setMovie_trailer_link(movieData.get("homepage")); // optional
//            movie.setMovie_budget(parseBigDecimal(movieData.get("budget")));
//            movie.setMovie_release_date(parseDate(movieData.get("release_date")));
//            movie.setMovie_country(extractCountry(movieData.get("production_countries")));
//
//            if (creditData != null) {
//                movie.setMovie_director_name(extractDirector(creditData.get("crew")));
//                List<String> actors = extractActors(creditData.get("cast"));
//                if (actors.size() > 0) movie.setMovie_actor1(actors.get(0));
//                if (actors.size() > 1) movie.setMovie_actor2(actors.get(1));
//                if (actors.size() > 2) movie.setMovie_actor3(actors.get(2));
//            }
//
//            // Save movie
//            repo.saveMovies(movie);
//            b = true;
//        }
//        System.out.println("✅ Movies import completed!");
//        return b;
//    }
//
//    private Map<String, Map<String, String>> loadCsvToMap(String csvFilePath) throws IOException, CsvException {
//        Map<String, Map<String, String>> dataMap = new HashMap<>();
//        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
//            List<String[]> rows = reader.readAll();
//            String[] headers = rows.get(0); // First row is header
//
//            for (int i = 1; i < rows.size(); i++) {
//                String[] row = rows.get(i);
//                Map<String, String> rowMap = new HashMap<>();
//                for (int j = 0; j < headers.length && j < row.length; j++) {
//                    rowMap.put(headers[j].trim(), row[j]);
//                }
//                String id = rowMap.get("id");
//                if (id != null && !id.isEmpty()) {
//                    dataMap.put(id, rowMap);
//                }
//            }
//        }
//        return dataMap;
//    }
//
//    private String extractGenres(String genresJson) {
//        if (genresJson == null || genresJson.isEmpty()) return null;
//        JSONArray genres = new JSONArray(genresJson);
//        List<String> genreList = new ArrayList<>();
//        for (int i = 0; i < genres.length(); i++) {
//            JSONObject obj = genres.getJSONObject(i);
//            genreList.add(obj.getString("name"));
//        }
//        return String.join(", ", genreList);
//    }
//
//    private String extractCountry(String countriesJson) {
//        if (countriesJson == null || countriesJson.isEmpty()) return null;
//        JSONArray countries = new JSONArray(countriesJson);
//        if (countries.length() > 0) {
//            return countries.getJSONObject(0).getString("name");
//        }
//        return null;
//    }
//
//    private String extractDirector(String crewJson) {
//        if (crewJson == null || crewJson.isEmpty()) return null;
//        JSONArray crewArray = new JSONArray(crewJson);
//        for (int i = 0; i < crewArray.length(); i++) {
//            JSONObject obj = crewArray.getJSONObject(i);
//            if ("Director".equalsIgnoreCase(obj.optString("job"))) {
//                return obj.getString("name");
//            }
//        }
//        return null;
//    }
//
//    private List<String> extractActors(String castJson) {
//        List<String> actors = new ArrayList<>();
//        if (castJson == null || castJson.isEmpty()) return actors;
//        JSONArray castArray = new JSONArray(castJson);
//        for (int i = 0; i < Math.min(3, castArray.length()); i++) {
//            actors.add(castArray.getJSONObject(i).getString("name"));
//        }
//        return actors;
//    }
//
//    private BigDecimal parseBigDecimal(String value) {
//        try {
//            if (value == null || value.isEmpty()) return null;
//            return new BigDecimal(value);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private Date parseDate(String dateStr) {
//        try {
//            if (dateStr == null || dateStr.isEmpty()) return null;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            return sdf.parse(dateStr);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
//
