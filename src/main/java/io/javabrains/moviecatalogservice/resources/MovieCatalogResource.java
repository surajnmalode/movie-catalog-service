package io.javabrains.moviecatalogservice.resources;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //TODO hardcoded ratings as of now
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
        // Call the API
        UserRating userRating = userRatingInfo.getUserRating(userId);
        return userRating.getUserRating().stream()
                .map(rating -> movieInfo.getCatalogItem(rating))
                .collect(Collectors.toList());
                }
}






// with web client
            /*
            Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8083/movies/\" + rating.getMovieId()")
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();

           Notes
           1.   Are both call asyncronous?
           both calls are syncronous. TO do asyncronous we use Webclientbuilder above.

           2. If the application is being accessed in multithreaded envt, do we have to handle concurrency issues
           rest template is thread safe.One template doesnt effect another

           3. ANy recommendations as when to use restTemplate and when to use WebClient?
           WebClient -async way

           4. Is it possible to make these calls to an external API which is not microservice?
           Yes feasible

           5.How do we handle security when communication happens between two microservice?
            1. HTTPS
            2. Another using aunthentication

           */