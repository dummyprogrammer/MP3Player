package AppPackage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class TrackPlayer 
{
    private FileInputStream mFileInput;
    private BufferedInputStream mBufferedInput;
    
    private Player mPlayer;
    
    private long mPauseLocation;
    private long mTotalSongLength;
    
    private String mFileLocation;
    
    public void Stop()
    {
        if(mPlayer != null)
        {
            mPlayer.close();
            
            mPauseLocation = 0;
            mTotalSongLength = 0;
        }
    }
    
    public void Play(String path) 
    {
        try 
        {
            mFileInput = new FileInputStream(path); 
            mBufferedInput = new BufferedInputStream(mFileInput);
            
            mPlayer = new Player(mBufferedInput);
            mTotalSongLength = mFileInput.available();
            
            mFileLocation = path + "";
        } 
        catch (FileNotFoundException | JavaLayerException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(TrackPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
            public void run()
            {
                try 
                {
                    mPlayer.play();
                } 
                catch (JavaLayerException ex) 
                {
                    
                }
            }
        }.start();
        
    }
    
    public void Resume() 
    {
        try 
        {
            mFileInput = new FileInputStream(mFileLocation); 
            mBufferedInput = new BufferedInputStream(mFileInput);
            
            mPlayer = new Player(mBufferedInput);
            
            mFileInput.skip(mTotalSongLength - mPauseLocation);
         } 
        catch (FileNotFoundException | JavaLayerException ex) 
        {
            
        } 
        catch (IOException ex) 
        {
            
        }
        
        new Thread()
        {
            public void run()
            {
                try 
                {
                    mPlayer.play();
                } 
                catch (JavaLayerException ex) 
                {
                    
                }
            }
        }.start();
        
    }
    
    
    public void Pause()
    {
        if (mPlayer != null)
        {
            try 
            {
                mPauseLocation = mFileInput.available();
                mPlayer.close();
            } 
            catch (IOException ex) 
            {
                
            }
            
        }
    }
    
}
