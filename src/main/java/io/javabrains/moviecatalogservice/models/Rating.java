package io.javabrains.moviecatalogservice.models;

public class Rating {
    private String movieId;
    private int rating;

    public  Rating() {

    }
    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

// NOte:

/*
Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue Apr 28 19:59:27 IST 2020
There was an unexpected error (type=Internal Server Error, status=500).
Type definition error: [simple type, class io.javabrains.moviecatalogservice.models.Rating]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `io.javabrains.moviecatalogservice.models.Rating` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator) at [Source: (PushbackInputStream); line: 1, column: 17] (through reference chain: io.javabrains.moviecatalogservice.models.UserRating["userRating"]->java.util.ArrayList[0])

soln: no public constructor
 */
