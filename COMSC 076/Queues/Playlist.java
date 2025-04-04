/**
 * Represents a playlist that can hold a limited number of songs.
 * It allows adding songs, playing, pausing, and skipping to the next song.
 * @author Jordan Marcelo
 * @version 1.0
 * 2/23/25
 * EVC COMSC 076 Professor Srinivasan
 */
public class Playlist {
    private final Queue<Song> contents = new Queue<>();
    private final int maxSongs;
    private boolean playing;
    private Song nowPlaying;

    /**
     * Constructs a Playlist with a specified maximum number of songs.
     *
     * @param maxSongs the maximum number of songs the playlist can hold
     */
    public Playlist(int maxSongs) {
        this.maxSongs = maxSongs;
    }

    /**
     * Adds a song to the playlist. If the playlist is full, the next song is removed.
     *
     * @param song the song to add to the playlist
     */
    public void addSong(Song song) {
        if (contents.size() == maxSongs) {
            contents.dequeue();
        }
        contents.enqueue(song);
    }

    /**
     * Skips to the next song in the playlist.
     * If the playlist is paused, it will start playing the next song.
     */
    public void next() {
        if (!contents.isEmpty()) {
            nowPlaying = contents.dequeue();
            playing = true;
        }
    }

    /**
     * Starts playing the playlist. If a song is already playing, it continues.
     * If no song is currently playing and there is at least one in the playlist,
     * it starts playing the next song in the queue.
     */
    public void play() {
        if (nowPlaying == null && !contents.isEmpty()) {
            nowPlaying = contents.dequeue();
        }
        if (nowPlaying != null) {
            playing = true;
        }
    }

    /**
     * Pauses the currently playing song, if there is one.
     */
    public void pause() {
        playing = false;
    }

    /**
     * Returns the currently playing song if the playlist is playing; otherwise, returns null.
     *
     * @return the currently playing song, or null if no song is playing or the playlist is paused
     */
    public Song getCurrentlyPlayingSong() {
        if (!playing) {
            return null;
        }
        return nowPlaying;
    }
}