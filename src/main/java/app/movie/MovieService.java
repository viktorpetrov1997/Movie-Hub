package app.movie;

import app.movie.exception.MovieNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService
{
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies()
    {
        return movieRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Movie> searchMovies(String query)
    {
        if(query == null || query.isBlank())
        {
            return getAllMovies();
        }

        return movieRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(query);
    }

    public Movie findById(UUID id)
    {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }
}
