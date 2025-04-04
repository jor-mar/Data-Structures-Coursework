/**
 * Represents a song with a title, artist, genre, and duration in seconds
 * @author Balaji Srinivasan, Jordan Marcelo
 * @version 1.0
 * 2/23/25
 * EVC COMSC 076 Professor Srinivasan
 */
public class Song {
    final private String title;
    final private String artist;
    final private String genre;
    final private int duration; // Duration in seconds.

    /**
     * Constructor.
     * @param title The title to use.
     * @param artist The artist of this song.
     * @param genre The genre of this song.
     * @param duration The duration of this song in seconds.
     */
    public Song(String title, String artist, String genre, int duration) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }
        
    /**
     * @returns the title of this song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @returns The artist for this song.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @returns The genre of the song.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @returns The duration of this song in seconds.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @returns a string representation of the song including title, artist, genre, and duration.
     */
    @Override
    public String toString() {
        return title + " by " + artist + ", " + genre + " [" + duration + " seconds]";
    }

    /**
     * Checks to see if this song and another have the same attributes
     * @param that the object to be compared to this
     * @return true if the parameter is a song with the same attributes as this, false otherwise
     */
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Song)) {
            return false;
        }

        Song other = (Song)that;

        boolean sameTitle = java.util.Objects.equals(title, other.title);
        boolean sameArtist = java.util.Objects.equals(artist, other.artist);
        boolean sameGenre = java.util.Objects.equals(genre, other.genre);
        boolean sameDuration = duration == other.duration;

        return sameTitle && sameArtist && sameGenre && sameDuration;
    }
}