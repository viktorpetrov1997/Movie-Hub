package app.movie;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
