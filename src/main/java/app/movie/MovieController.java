package app.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class MovieController
{
    private final MovieService movieService;

    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ModelAndView getMoviesPage(@RequestParam(required = false) String search)
    {
        List<Movie> movies = movieService.searchMovies(search);

        ModelAndView modelAndView = new ModelAndView("movies");

        modelAndView.addObject("movies", movies);
        modelAndView.addObject("search", search);

        return modelAndView;
    }

    @GetMapping("/movies/{id}")
    public ModelAndView movieDetails(@PathVariable UUID id)
    {
        Movie movie = movieService.findById(id);

        ModelAndView modelAndView = new ModelAndView("movie-details");
        modelAndView.addObject("movie", movie);

        return modelAndView;
    }
}
