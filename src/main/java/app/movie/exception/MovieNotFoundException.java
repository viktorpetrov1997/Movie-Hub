package app.movie.exception;

import java.util.UUID;

public class MovieNotFoundException extends RuntimeException
{
    public MovieNotFoundException(UUID id)
    {
        super("Movie with id " + id + " was not found");
    }
}
