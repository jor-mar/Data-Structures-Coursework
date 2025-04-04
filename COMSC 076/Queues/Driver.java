
import java.util.Objects;

public class Driver extends DriverBase {

    private static boolean isEqual(Song s1, Song s2) {

        return Objects.equals(s1.getTitle(), s2.getTitle())
                && Objects.equals(s1.getArtist(), s2.getArtist())
                && Objects.equals(s1.getGenre(), s2.getGenre())
                && s1.getDuration() == s2.getDuration();

    }

    public static void main(String[] args) {
        final Song s1 = new Song("t", "a", "g", 1);
        final Song s2 = new Song(null, null, null, 2);
        final Song s3 = new Song("t3", "a3", "g3", 3);
        final Song s4 = new Song("t4", "a4", "g4", 4);

        // Check if Song.equals works when a non-Song is passed in.
        setCurrentTestCase("equals on a non-Song");
        checkDoesNotThrowException(() -> {
            if (s1.equals(new String())) {
                setTestResult("Comparing with non-song returned true instead of false");
            } else {
                setTestResult(null);
            }
        });
        // Check if Song.equals works with null titles etc.
        setCurrentTestCase("equals on a song with null elements");
        checkDoesNotThrowException(() -> {
            if (s1.equals(s2)) {
                setTestResult("Comparing song with empty elements returned true instead of false");
            } else {
                setTestResult(null);
            }
        });

        // Check if Song.equals works with null titles in the this object.
        setCurrentTestCase("equals on a song with this object having null elements");
        checkDoesNotThrowException(() -> {
            if (s2.equals(s1)) {
                setTestResult("Comparing song with empty elements returned true instead of false");
            } else {
                setTestResult(null);
            }
        });

        // Check two songs are equal
        setCurrentTestCase("Checking two songs are equals");
        checkDoesNotThrowException(() -> {
            if (s1.equals(new Song("t", "a", "g", 1))) {
                setTestResult(null);
            } else {
                setTestResult("equals failed to compare two equal objects");
            }
        });

        // Check adding one song and playing it and checking if thats the right song
        setCurrentTestCase("Adding one song to playlist");
        checkDoesNotThrowException(() -> {
            final Playlist p1 = new Playlist(3);
            p1.addSong(s1);
            p1.play();
            if (isEqual(p1.getCurrentlyPlayingSong(), s1)) {
                setTestResult(null);
            } else {
                setTestResult("Playing and then getCurrentlyPlayingSong does not return the first song");
            }

        });

        // Check adding multiple songs (past queue size) and checking what the first song is
        setCurrentTestCase("Adding more than max number of songs to playlist");
        checkDoesNotThrowException(() -> {
            final Playlist p1 = new Playlist(3);
            p1.addSong(s1);
            p1.addSong(s2);
            p1.addSong(s3);
            p1.addSong(s4);
            p1.play();
            if (isEqual(p1.getCurrentlyPlayingSong(), s2)) {
                setTestResult(null);
            } else {
                setTestResult("Adding more than max songs did not remove first song from queue");
            }
        });

        // Check next method to make sure it plays the next song
        setCurrentTestCase("Checking next() method on playlist");
        checkDoesNotThrowException(() -> {
            final Playlist p1 = new Playlist(3);
            p1.addSong(s1);
            p1.addSong(s2);
            p1.addSong(s3);
            p1.play();
            p1.next();
            if (isEqual(p1.getCurrentlyPlayingSong(), s2)) {
                setTestResult(null);
            } else {
                setTestResult("next() does not skip a song");
            }
        });

        // Check pause and then getCurrentPlaying Song
        setCurrentTestCase("Pausing a song");
        checkDoesNotThrowException(() -> {
            final Playlist p1 = new Playlist(3);
            p1.addSong(s1);
            p1.addSong(s2);
            p1.addSong(s3);
            p1.play();
            p1.pause();
            if (p1.getCurrentlyPlayingSong() == null) {
                setTestResult(null);
            } else {
                setTestResult("Pausing and then getCurrentlyPlayingSong does not return null");
            }
        });

        // Do next more than the number of songs in the playlist
        setCurrentTestCase("Calling next more times than there are songs in the playlist");
        checkDoesNotThrowException(() -> {
            final Playlist p1 = new Playlist(3);
            p1.addSong(s1);
            p1.addSong(s2);
            p1.next();
            p1.next();
            p1.next();
            setTestResult(null);
        });

        // Keep hitting play multiple times.
        setCurrentTestCase("Calling play multiple times");
        checkDoesNotThrowException(() -> {
            final Playlist p1 = new Playlist(3);
            p1.addSong(s1);
            p1.addSong(s2);
            p1.play();
            p1.play();
            p1.play();
            p1.play();
            p1.play();
            if (isEqual(p1.getCurrentlyPlayingSong(), s1)) {
                setTestResult(null);
            } else {
                setTestResult("Calling play multiple times did not return the expected song. Got "
                        + p1.getCurrentlyPlayingSong() + " expected " + s1);
            }
        });
        printJsonOutput();
      }
    }
