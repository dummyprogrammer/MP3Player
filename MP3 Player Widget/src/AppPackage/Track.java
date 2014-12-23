/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;

import java.io.Serializable;

/**
 *
 * @author razvan.milea
 */
public class Track implements Serializable
{

    private String mSongName;
    private String mArtist;
    private int mSongLength;
    private String mSongPath;
    
    public Track(String songname, String artist, int songLength, String songPath)
    {
        this.mSongName = songname;
        this.mArtist = artist;
        this.mSongPath = songPath;
        this.mSongLength = songLength;
    }
    
    public String getSongName()
    {
        return mSongName;
    }
    
    public String getArtist()
    {
        return mArtist;
    }

    public String getSongPath()
    {
        return mSongPath;
    }

    
    @Override
    public String toString() {
        return mArtist + " - " + mSongName; 
    }

    public int getSongLength() {
        return mSongLength;
    }
    
}
