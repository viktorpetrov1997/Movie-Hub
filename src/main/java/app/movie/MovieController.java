package app.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MovieController
{
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public ModelAndView getMoviesPage()
    {
        List<Movie> movies = movieRepository.findAllByOrderByCreatedAtDesc();

        ModelAndView modelAndView = new ModelAndView("movies");
        modelAndView.addObject("movies", movies);

        return modelAndView;
    }
}
