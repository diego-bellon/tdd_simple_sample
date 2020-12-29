import model.Movie;
import model.MovieStore;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class MovieStoreTest {

    private MovieStore movieStore;
    private Movie harryPotterMovie;
    private Movie starWars;
    private Movie starTrek;
    private Movie shawshank;
    private Movie takeThat;

    @Before
    public void setup() {
        movieStore = new MovieStore();
        harryPotterMovie = new Movie("Harry Potter", "Rowling",2000);
        starWars = new Movie("Star wars","Shwimmer",1999);
        starTrek = new Movie("STAR trek","Shwimmer",2002);
        shawshank = new Movie("Shawshank","Bob",2001);
        takeThat = new Movie("take That","Shwimmer",2010);
        movieStore.add(harryPotterMovie);
        movieStore.add(starWars);
        movieStore.add(starTrek);
        movieStore.add(takeThat);
        movieStore.add(shawshank);
    }
    @Test
    public void returnsNoResultsWhenNoTitlesPartialMatchSearch() throws Exception{
        MovieStore movieStore = new MovieStore();
        List<Movie> results =  movieStore.findByPartialTitle("abc");
        assertThat(results.size(),is(0));
    }

    @Test
    public void findsAMovieWhenTitleIsPartiallyMatched() throws Exception{
        List<Movie> results =  movieStore.findByPartialTitle("arry");
        assertThat(results.size(),is(1));
        assertThat(results, contains(harryPotterMovie));
    }
    @Test
    public void findsMoviesWhenTitlesArePartiallyMatchedCaseInsensitive() throws Exception{
        List<Movie> results =  movieStore.findByPartialTitle("tar");
        assertThat(results.size(),is(2));
        assertThat(results, containsInAnyOrder(starTrek,starWars));
    }
    @Test
    public void findsMoviesWhenDirectorExactlyMatched() throws Exception{
        List<Movie> results =  movieStore.findByDirector("Shwimmer");
        assertThat(results.size(),is(3));
        assertThat(results, containsInAnyOrder(starTrek, starWars, takeThat));
    }

    @Test
    public void findsMoviesWhenReleaseYearIsBetweenTwoValues() throws Exception{
        List<Movie> results =  movieStore.findByReleaseYears(1999,2002);
        assertThat(results.size(),is(2));
        assertThat(results, containsInAnyOrder(harryPotterMovie, shawshank));
    }

}
