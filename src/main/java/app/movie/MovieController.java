package app.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
}
