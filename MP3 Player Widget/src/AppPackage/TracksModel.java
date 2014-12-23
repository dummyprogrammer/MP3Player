/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author razvan.milea
 */
public class TracksModel extends AbstractListModel implements Serializable
{
    private List<Track> mTracks = new ArrayList<Track>();
    
    @Override
    public int getSize() 
    {
        return mTracks.size();
    }

    @Override
    public Object getElementAt(int index) 
    {
        return mTracks.get(index);
    }
    
    public void addTrack(Track melodie)
    {
        mTracks.add(melodie);
        fireContentsChanged(this, 0, mTracks.size()-1);
    }
    
    public void removeTrack(int index)
    {
        mTracks.remove(index);
        fireContentsChanged(this, 0, mTracks.size()-1);
    }
    
    
    
}
