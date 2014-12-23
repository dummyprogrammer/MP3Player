/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;

/**
 *
 * @author razvan.milea
 */
public class Track 
{

    private String songname;
    private String artist;
    private int songLength;
    private String songPath;
    
    public Track(String songname, String artist, int songLength, String songPath)
    {
        this.songname = songname;
        this.artist = artist;
        this.songPath = songPath;
        this.songLength = songLength;
    }
    
    public String getSongName()
    {
        return songname;
    }
    
    public String getArtist()
    {
        return artist;
    }

    public String getSongPath()
    {
        return songPath;
    }

    
    @Override
    public String toString() {
        return artist + " - " + songname; 
    }

    public int getSongLength() {
        return songLength;
    }
    
}
