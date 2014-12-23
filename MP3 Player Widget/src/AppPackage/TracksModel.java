/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AppPackage;


import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author razvan.milea
 */
public class TracksModel extends AbstractListModel
{
    private List<Track> tracks = new ArrayList<Track>();
    
    @Override
    public int getSize() 
    {
        return tracks.size();
    }

    @Override
    public Object getElementAt(int index) 
    {
        return tracks.get(index);
    }
    
    public void addTrack(Track melodie)
    {
        tracks.add(melodie);
        fireContentsChanged(this, 0, tracks.size()-1);
    }
    
    public void removeTrack(int index)
    {
        tracks.remove(index);
        fireContentsChanged(this, 0, tracks.size()-1);
    }
    
    
    
}
