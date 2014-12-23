package AppPackage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MainClass 
{
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public Player player;
    
    public long pauseLocation;
    public long totalSongLength;
    
    public String fileLocation;
    
    public void Stop()
    {
        if(player != null)
        {
            player.close();
            
            pauseLocation = 0;
            totalSongLength = 0;
        }
    }
    
    public void Play(String path) 
    {
        try 
        {
            FIS = new FileInputStream(path); 
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            totalSongLength = FIS.available();
            
            fileLocation = path + "";
        } 
        catch (FileNotFoundException | JavaLayerException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
            public void run()
            {
                try 
                {
                    player.play();
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
            FIS = new FileInputStream(fileLocation); 
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            
            FIS.skip(totalSongLength - pauseLocation);
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
                    player.play();
                } 
                catch (JavaLayerException ex) 
                {
                    
                }
            }
        }.start();
        
    }
    
    
    public void Pause()
    {
        if (player != null)
        {
            try 
            {
                pauseLocation = FIS.available();
                player.close();
            } 
            catch (IOException ex) 
            {
                
            }
            
        }
    }
    
}
