package model;

import java.util.LinkedList;
import java.util.List;

public class MovieStore {
    LinkedList<Movie> movies= new LinkedList<Movie>();

    public void add(Movie newMovie) {
        movies.add(newMovie);
    }

    public List<Movie> findByPartialTitle(String partialTitle) {
        return findBy(movie -> movie.getTitle().toUpperCase().contains(partialTitle.toUpperCase()));
    }


    public List<Movie> findByDirector(String director) {
        return findBy(movie -> movie.getDirector().equals(director));
    }

    public List<Movie> findByReleaseYears(int from, int to) {
        return findBy(movie -> movie.getReleaseYear() > from && movie.getReleaseYear() < to);
    }

    private List<Movie> findBy( Predicate predicate) {
        LinkedList<Movie> findResult = new LinkedList<Movie>();
        for (Movie movie : movies) {
            if (predicate.matches(movie)) {
                findResult.add(movie);
            }
        }
        return findResult;
    }

    private interface Predicate{
        boolean matches(Movie movie);
    }
}
