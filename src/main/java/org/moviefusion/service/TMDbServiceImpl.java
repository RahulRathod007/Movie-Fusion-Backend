package org.moviefusion.service;


import org.moviefusion.model.MovieInfo;
import org.moviefusion.repository.TMDBRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TMDbServiceImpl { 

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.themoviedb.org/3";
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TMDBRepositoryImpl tmdbRepository; 
    private static int page = 1; 
    // Marathi - 4
    // Hindi - 5
    // Tamil - 2
    //English - 1
    public void fetchAndSavePopularMovies() {
 
    	String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/discover/movie")
    	        .queryParam("api_key", apiKey)
    	        .queryParam("language", "en-US")
    	        .queryParam("sort_by", "popularity.desc") 
    	        .queryParam("with_original_language", "en")         // Filter for Hindi language
    	        .queryParam("with_origin_country", "IN")            // Filter for Indian movies    	 
    	        .queryParam("page", page)
    	        .toUriString();


//    	String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/discover/movie")
//    	        .queryParam("api_key", apiKey)
//    	        .queryParam("language", "en-US")
//    	        .queryParam("sort_by", "popularity.desc") 
//    	        .queryParam("with_original_language", "hi")         // Filter movies originally in Hindi
//    	        .queryParam("page", page) 
//    	        .toUriString();
  
 
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
        
        

        for (Map<String, Object> item : results) {
        	
        	String movieTitle = (String) item.get("title");
        	
        	// Skip the movie if title is not available
        	if (movieTitle == null || movieTitle.isEmpty()) {
        	    continue;
        	}
        	 
        	//Check if the movie already exists in the database by title
        	if(tmdbRepository.checkMovieExistsByMovieTitle(movieTitle))
        	{
        		System.out.println("Movie with title : \t" +  movieTitle + "\t" + "Already exists. Skipping..");
        		continue;// Skip saving this movie
        	}
        	
        	// Skip if poster is missing
        	String posterPath = (String) item.get("poster_path");
        	if (posterPath == null || posterPath.isEmpty()) {
        	    System.out.println("Movie with title: \t" + movieTitle + "\t has no poster. Skipping...");
        	    continue;
        	}
        	
        	 Integer movieId = (Integer) item.get("id");
	         // 2. Get movie details
	         Map<String, Object> details = restTemplate.getForObject(BASE_URL + "/movie/" + movieId + "?api_key=" + apiKey, Map.class);
	 
	         // 3. Get credits
	         Map<String, Object> credits = restTemplate.getForObject(BASE_URL + "/movie/" + movieId + "/credits?api_key=" + apiKey, Map.class);
	         String director = "", actor1 = "", actor2 = "", actor3 = "";
	 
	         List<Map<String, Object>> crew = (List<Map<String, Object>>) credits.get("crew");
	         for (Map<String, Object> person : crew) {
	             if ("Director".equals(person.get("job"))) {
	                 director = (String) person.get("name");
	                 break;
	             }
	         }
	 
	         List<Map<String, Object>> cast = (List<Map<String, Object>>) credits.get("cast");
	      // If no cast available, skip this movie
	         if (cast == null || cast.isEmpty()) {
	             continue;
	         }
	         
	         if (cast.size() > 0) actor1 = (String) cast.get(0).get("name");
	         if (cast.size() > 1) actor2 = (String) cast.get(1).get("name");
	         if (cast.size() > 2) actor3 = (String) cast.get(2).get("name");
	         
	         List<Map<String, Object>> genres = (List<Map<String, Object>>) details.get("genres");
	         
	         // If no genres found, skip this movie
	         if (genres == null || genres.isEmpty()) {
	             continue;
	         }
	         
	        String genreList = genres.stream()
	                                 .map(g -> (String) g.get("name"))
	                                 .collect(Collectors.joining(", "));
	         
            MovieInfo movie = new MovieInfo();
            movie.setOriginal_movie_id(movieId);
            movie.setMovie_title((String) item.get("title"));
            movie.setMovie_mapping_name(((String) item.get("title")).toLowerCase().replace(" ", "-"));
            movie.setMovie_description((String) item.get("overview"));
            String langCode = (String) item.get("original_language");
            movie.setMovie_language(getLanguageFullName(langCode));     
            movie.setMovie_category(genreList);
            movie.setMovie_director_name(director);
	        movie.setMovie_actor1(actor1);
	        movie.setMovie_actor2(actor2);
	        movie.setMovie_actor3(actor3);

            // Set release date
            String dateStr = (String) item.get("release_date");
            if (dateStr != null && !dateStr.isEmpty()) {
                movie.setMovie_release_date(LocalDate.parse(dateStr));
            }

            movie.setMovie_country(((List<Map<String, Object>>) details.get("production_countries")).stream().map(c -> (String) c.get("name")).collect(Collectors.joining(", ")));
//          movie.setWatch_link("https://www.themoviedb.org/movie/" + item.get("id"));
            movie.setWatch_link("https://www.youtube.com/results?search_query=" + ((String) item.get("title")).replace(" ", "+") + "+trailer");
            movie.setMovie_trailer_link("https://www.youtube.com/results?search_query=" + ((String) item.get("title")).replace(" ", "+") + "+trailer");
			
            BigDecimal budget = null;
            Object budgetObj = details.get("budget");

            // If the budget is an Integer or Long, convert it to BigDecimal
            if (budgetObj instanceof Integer) {
                budget = BigDecimal.valueOf((Integer) budgetObj);
            } else if (budgetObj instanceof Long) {
                budget = BigDecimal.valueOf((Long) budgetObj);
            }

            // Set default budget to 100 crore (100,000,000) if budget is null or 0
            if (budget == null || budget.compareTo(BigDecimal.ZERO) == 0) {
                movie.setMovie_budget(BigDecimal.valueOf(100000000)); // 100 crore
            } else {
                movie.setMovie_budget(budget); // Use the actual budget if available
            }


   
            movie.setMovie_duration(convertMinutesIntoHr(String.valueOf(details.get("runtime")))) ;
            
            tmdbRepository.saveMovies(movie);
        }
        System.out.println("Page number is : " + page++);
    }
    
    public String convertMinutesIntoHr(String runtime) {
        if (runtime == null || runtime.isEmpty() || runtime.equals("0")) {
            return "2 hr 15 min"; // Default duration if runtime is null, empty, or 0
        }

        try {
            int minutes = Integer.parseInt(runtime);
            
            // If the parsed runtime is 0, return default duration
            if (minutes == 0) {
                return "2 hr 15 min"; // Default duration if 0 minutes
            }

            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            return hours + " hr " + remainingMinutes + " min";
        } catch (NumberFormatException e) {
            return "2 hr 15 min"; // Default duration if parsing fails
        }
    }

    
    private String getLanguageFullName(String code) {
        switch (code) {
            case "en": return "English";
            case "hi": return "Hindi";
            case "mr": return "Marathi";
            case "te": return "Telugu";
            case "ta": return "Tamil";
            case "bn": return "Bengali";
            case "ml": return "Malayalam";
            case "kn": return "Kannada";
            case "gu": return "Gujarati";
            case "pa": return "Punjabi";
            case "or": return "Odia";
            case "ur": return "Urdu";
            case "as": return "Assamese";
            case "kok": return "Konkani";

            // Other global languages (in case TMDb gives foreign content)
            case "es": return "Spanish";
            case "fr": return "French";
            case "de": return "German";
            case "it": return "Italian";
            case "ru": return "Russian";
            case "ja": return "Japanese";
            case "ko": return "Korean";
            case "zh": return "Chinese";
            case "pt": return "Portuguese";
            case "ar": return "Arabic";
            case "tr": return "Turkish";
            case "nl": return "Dutch";
            case "sv": return "Swedish";
            case "pl": return "Polish";
            case "fa": return "Persian";
            case "id": return "Indonesian";
            case "vi": return "Vietnamese";
            case "th": return "Thai";
            case "cs": return "Czech";
            case "el": return "Greek";
            case "he": return "Hebrew";
            case "no": return "Norwegian";
            case "fi": return "Finnish";
            case "ro": return "Romanian";
            case "uk": return "Ukrainian";
            case "hu": return "Hungarian";

            default: return code; // fallback to code if unknown
        }
    }

}