package app.movie;

public enum Genre
{
    ACTION("Action"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    SCI_FI("Sci-Fi");

    private final String displayName;

    Genre(String displayName)
    {
        this.displayName = displayName;
    }

    public String getDisplayName()
    {
        return displayName;
    }
}