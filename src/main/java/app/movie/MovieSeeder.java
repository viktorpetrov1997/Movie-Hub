package app.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovieSeeder implements CommandLineRunner
{
    private final MovieRepository movieRepository;

    public MovieSeeder(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args)
    {
        if(movieRepository.count() == 0)
        {
            Movie movie1 = Movie.builder()
                    .title("The Dark Knight")
                    .director("Christopher Nolan")
                    .genre(Genre.ACTION)
                    .releaseYear(2008)
                    .duration(152)
                    .description("Batman faces the criminal mastermind Joker.")
                    .posterUrl("/images/TheDarkKnight.jpg")
                    .averageRating(9.0)
                    .createdAt(LocalDateTime.now())
                    .build();

            Movie movie2 = Movie.builder()
                    .title("Interstellar")
                    .director("Christopher Nolan")
                    .genre(Genre.SCI_FI)
                    .releaseYear(2014)
                    .duration(169)
                    .description("A team of explorers travels through a wormhole in space.")
                    .posterUrl("/images/Interstellar.webp")
                    .averageRating(10.0)
                    .createdAt(LocalDateTime.now())
                    .build();

            Movie movie3 = Movie.builder()
                    .title("Star Wars: The Mandalorian and Grogu")
                    .director("Jon Favreau")
                    .genre(Genre.SCI_FI)
                    .releaseYear(2026)
                    .duration(132)
                    .description("A science fiction movie about humanity's first contact.")
                    .posterUrl("/images/StarWars.jpg")
                    .averageRating(8.0)
                    .createdAt(LocalDateTime.now())
                    .build();


            movieRepository.save(movie1);
            movieRepository.save(movie2);
            movieRepository.save(movie3);
        }
    }
}