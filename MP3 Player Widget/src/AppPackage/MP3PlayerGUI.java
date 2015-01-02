package AppPackage;

//All code is added to create the widget. Follow the "Create a Widget" tutorial if you want to know how I did it.

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class MP3PlayerGUI extends javax.swing.JDialog 
{
    TrackPlayer mTrackPlayer = new TrackPlayer();
    
    private TracksModel mTracksModel;

    private String mTrackListInfo;
    
    
    private Track mPlayingTrack;
    
    private boolean mIsSeekerTimerRunning = false;
    private Timer mSeekerTimer;
    private int mMinutes;
    private int mSeconds;
    private int mTrackPlayingTime = 0;
    int mSeekBaVal = 0; 
    
    
    private String mFileMusicPath = "E:\\Other music";
    
    private int mPlayingTrackIndex = -1;
    
    private int mDragStartX;
    private int mDragStartY;
    
    private int mWindowInitialX = (Toolkit.getDefaultToolkit().getScreenSize().width / 2) - 185;
    private int mWindowInitialY = (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - 185;
    
    public MP3PlayerGUI(java.awt.Frame parent, boolean modal) 
    {
        super(parent, modal);
        
        initComponents();
        this.setLocation(mWindowInitialX, mWindowInitialY);
        InitPlaylistRenderer();
        LoadPlaylistTracks();
        Playlist.setModel(mTracksModel);
    }

    private void InitPlaylistRenderer()
    {
        Playlist.setCellRenderer(new DefaultListCellRenderer()
        {
            public Component getListCellRendererComponent(
                JList<?> list,           // the list
                Object value,            // value to display
                int index,               // cell index
                boolean isSelected,      // is the cell selected
                boolean cellHasFocus)    // does the cell have focus
            {
                //System.out.println("getListCellRendererComponent() index " + index);
                
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == mPlayingTrackIndex) 
                {
                    setFont(list.getFont().deriveFont(Font.BOLD));
                }
                else 
                {
                    setFont(list.getFont().deriveFont(Font.PLAIN));
                }
                return this;
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Display = new javax.swing.JLabel();
        SeekBar = new javax.swing.JProgressBar();
        exitLabel = new javax.swing.JLabel();
        Play = new javax.swing.JLabel();
        SelectFile = new javax.swing.JLabel();
        Pause = new javax.swing.JLabel();
        Stop = new javax.swing.JLabel();
        Loop = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Playlist = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MP3 Player");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Display.setFont(new java.awt.Font("SimHei", 1, 14)); // NOI18N
        Display.setForeground(new java.awt.Color(255, 255, 255));
        Display.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Display.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(Display, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 20));

        SeekBar.setForeground(new java.awt.Color(255, 156, 0));
        SeekBar.setMaximum(0);
        SeekBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SeekBarMouseClicked(evt);
            }
        });
        getContentPane().add(SeekBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 370, 30));

        exitLabel.setText("Exit");
        exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitLabelMouseClicked(evt);
            }
        });
        getContentPane().add(exitLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        Play.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayMouseClicked(evt);
            }
        });
        getContentPane().add(Play, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, 83));

        SelectFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SelectFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectFileMouseClicked(evt);
            }
        });
        getContentPane().add(SelectFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 60, 40, 40));

        Pause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PauseMouseClicked(evt);
            }
        });
        getContentPane().add(Pause, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 60, 60));

        Stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StopMouseClicked(evt);
            }
        });
        getContentPane().add(Stop, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 70, 60));

        Loop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(Loop, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, 40));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AppPackage/Background.png"))); // NOI18N
        Background.setText("cxvxcvxcvx");
        Background.setToolTipText("");
        Background.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BackgroundMousePressed(evt);
            }
        });
        Background.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BackgroundMouseDragged(evt);
            }
        });
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 130));

        Playlist.setBackground(new java.awt.Color(242, 155, 42));
        Playlist.setBorder(new javax.swing.border.MatteBorder(null));
        Playlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlaylistMouseClicked(evt);
            }
        });
        Playlist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PlaylistKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(Playlist);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 370, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackgroundMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackgroundMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - mDragStartX, y - mDragStartY);
    }//GEN-LAST:event_BackgroundMouseDragged

    public void RemoveElementJlist()
    {
        int index = Playlist.getSelectedIndex();
        mTracksModel.removeTrack(index);
    }
    
    private void HighlightTrack(int trackIndex)
    {
        System.out.println("highlightTrack() trackIndex " + trackIndex);
        
        if(mPlayingTrackIndex != trackIndex)
        {
            mPlayingTrackIndex = trackIndex;
            Playlist.repaint();
        }
    }
    
    public void ConvertSongLength()
    {
        int songLength = mPlayingTrack.getSongLength();
        mMinutes = songLength / 60;
        mSeconds = songLength - (mMinutes * 60);
        System.out.println("Durata melodiei este " + mMinutes + ":" +mSeconds);
    }
    
    public void ConfigureSeekBar() 
    {
        if(mSeekerTimer != null)
        {
            if(mIsSeekerTimerRunning)
            {
                mSeekerTimer.cancel();
                mIsSeekerTimerRunning = false;
            }
        }
        mSeekerTimer = new Timer();
        
        final int songLength = mPlayingTrack.getSongLength();
        SeekBar.setMinimum(0);
        SeekBar.setMaximum(songLength);
        
        System.out.println("Melodia are  " + songLength + " secunde");
        
        mTrackPlayingTime = 0;
        mIsSeekerTimerRunning = true;
        
        java.util.TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {
                ++mTrackPlayingTime;
                
                //System.out.println("TimerTask::run() temp = " + trackPlayingTime);
                
                if (mTrackPlayingTime == songLength)
                {
                    mSeekerTimer.cancel();
                    mIsSeekerTimerRunning = false;
                    mTrackPlayingTime = 0;
                }
                UpdateBar(mTrackPlayingTime);
            }
        };
        
        mSeekerTimer.schedule(task, 0, 1000);
    }    
    
    public void UpdateBar(final int newVaIue)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                SeekBar.setValue(newVaIue);
                SeekBar.repaint();
            }
        });
    }
    
    private void SavePlaylistTracks()
    {
        try 
        {
            OutputStream file = new FileOutputStream("data/Playlist.ply");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream (buffer); 
            output.writeObject(mTracksModel);
            output.close();
            buffer.close();
            file.close();
        }
        catch (FileNotFoundException ex) 
        {
            ex.printStackTrace();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }  
    }
       
    public void LoadPlaylistTracks()
    {
        try 
        {
            InputStream file = new FileInputStream("data/Playlist.ply");
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream (buffer); 
            mTracksModel = (TracksModel)input.readObject();
            input.close();
            buffer.close();
            file.close();
        }
        catch (FileNotFoundException ex) 
        {
            ex.printStackTrace();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }  
        catch (ClassNotFoundException ex) 
        {
            ex.printStackTrace();
        }
        
        if(mTracksModel == null)
        {
            mTracksModel = new TracksModel();
        }  
    }
    
    private int GetIntegerValueOrZero(String value)
    {
        int intValue = 0;
        try
        {
            intValue = Integer.valueOf(value.trim());
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        return intValue;
    }
    
    private void BackgroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BackgroundMousePressed
        mDragStartX = evt.getX();
        mDragStartY = evt.getY();
    }//GEN-LAST:event_BackgroundMousePressed
    
    private Mp3File GetMP3File(String filePath)
    {
        Mp3File mp3File = null;
        try
        {
            mp3File = new Mp3File(filePath);
        }
        catch (IOException | UnsupportedTagException | InvalidDataException ex) 
        {
            Logger.getLogger(MP3PlayerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mp3File;
    }
    
    private ID3v1 ExtractTagsFromMusicFile(Mp3File mp3File)
    {
        if (mp3File.hasId3v1Tag())
        {
            return mp3File.getId3v1Tag();
        }
        else if(mp3File.hasId3v2Tag())
        {
            return mp3File.getId3v2Tag();
        }
        else
        {
            return null;
        }
    }
    
    private void SaveTrackToPlaylistFile(Track track)
    {
        try
        {
            BufferedWriter bfw = new BufferedWriter(new FileWriter("data/Playlist.txt", true));
            mTrackListInfo = track.getSongPath() + "," + track.getSongName() + 
                    "," + track.getArtist() + "," + track.getSongLength();
            bfw.write(mTrackListInfo);
            bfw.write("\t");
            bfw.newLine();
            bfw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void UpdateDisplay()
    {
        Display.setText(mPlayingTrack.getSongName() + " by " + mPlayingTrack.getArtist());
        Display.setHorizontalAlignment(SwingConstants.CENTER);
        Display.setVerticalAlignment(SwingConstants.CENTER);
    }
    
    private void StartPlayingSong(String songFilePath)
    {
        ConvertSongLength();
        ConfigureSeekBar();
        
        UpdateDisplay();
        
        mTrackPlayer.Stop();
        mTrackPlayer.Play(songFilePath);
    }
      
    
    private void PlaylistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlaylistMouseClicked
        if(evt.getClickCount() == 2)
        {
            PlaySelectedSong();
        }
    }//GEN-LAST:event_PlaylistMouseClicked

    private void PlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayMouseClicked
        if(mTracksModel.getSize() > 0)
        {
            int pozitie = Playlist.getSelectedIndex();
            if(pozitie < 0)
            {
                pozitie = 0;
                Playlist.setSelectedIndex(pozitie);
            }
            PlaySelectedSong();
        }
    }//GEN-LAST:event_PlayMouseClicked

    private void exitLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseClicked
        SavePlaylistTracks();
        System.exit(0);
    }//GEN-LAST:event_exitLabelMouseClicked

    private void StopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopMouseClicked
        mTrackPlayer.Stop();
        ResetDisplay();
        StopTimer();
        ResetSeekBar();
    }//GEN-LAST:event_StopMouseClicked
    private void ResetSeekBar()
    {
        UpdateBar(0);
    }
    
    private void StopTimer()
    {
        mSeekerTimer.cancel();
    }
    
    private void ResetDisplay()
    {
        Display.setText("");
    }
    
    private void PauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PauseMouseClicked
        mTrackPlayer.Pause();
    }//GEN-LAST:event_PauseMouseClicked

    private void SelectFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectFileMouseClicked
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 files", "mp3", "mpeg3");
        
        JFileChooser chooser = new JFileChooser(mFileMusicPath);
        chooser.addChoosableFileFilter(filter);
        
        int returnValue = chooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            String selectedMusicFilePath = chooser.getSelectedFile().getAbsolutePath();
            
            Mp3File mp3File = GetMP3File(selectedMusicFilePath);
            if(mp3File != null)
            {
                ID3v1 tag = ExtractTagsFromMusicFile(mp3File);
                String songTitle = tag.getTitle();
                String artist = tag.getArtist();
                int songLength = (int)mp3File.getLengthInSeconds();
                
                Track newTrack = new Track(songTitle, artist, songLength, selectedMusicFilePath);
                mTracksModel.addTrack(newTrack);
                //SaveTrackToPlaylistFile(newTrack);
                
                final int lastTrackIndex = mTracksModel.getSize() - 1;
                Playlist.setSelectedIndex(lastTrackIndex);
                PlaySelectedSong();
            }
        }
    }//GEN-LAST:event_SelectFileMouseClicked

    private void PlaylistKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PlaylistKeyReleased
 
        if (evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            RemoveTrackFromPlaylist();
        }
    }//GEN-LAST:event_PlaylistKeyReleased

    private void SeekBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeekBarMouseClicked

        int v = SeekBar.getValue();

       //Retrieves the mouse position relative to the component origin.
        int mouseX = evt.getX();

       //Computes how far along the mouse is relative to the component width then multiply it by the progress bar's maximum value.
        mSeekBaVal = (int)Math.round(((double)mouseX / (double)SeekBar.getWidth()) * SeekBar.getMaximum());
        
        
        SeekBarJump();
    }//GEN-LAST:event_SeekBarMouseClicked
    
    private void SeekBarJump()
    {
        mTrackPlayingTime = mSeekBaVal;
        long totalSongBytes = mTrackPlayer.getSongBytes();
        long skipSongBytes = (totalSongBytes*mTrackPlayingTime)/mPlayingTrack.getSongLength();
        //long valutSkipped = totalSongBytes - skipSongBytes;
        System.out.println("Valoarea sarita este skipSongBytes:" + skipSongBytes);
        mTrackPlayer.StopJump();
        mTrackPlayer.Jump(skipSongBytes);
        SeekBar.setValue(mSeekBaVal);
    }
    
    private void RemoveTrackFromPlaylist()
    {
        int index = Playlist.getSelectedIndex();
        if( index >= 0 )
        {
            mTracksModel.removeTrack(index);
        }
    }
    
    private void PlaySelectedSong()
    {
        UpdateCurrentlyPlayingTrack();
        StartPlayingSong(mPlayingTrack.getSongPath());
        HighlightTrack(Playlist.getSelectedIndex());
    }
    
    private void UpdateCurrentlyPlayingTrack()
    {
        int selectedTrack = Playlist.getSelectedIndex();
        if(selectedTrack >= 0)
        {
            mPlayingTrack = (Track)mTracksModel.getElementAt(selectedTrack);
        }
    }
    
    public static void main(String args[]) 
    {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MP3PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MP3PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MP3PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MP3PlayerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                MP3PlayerGUI dialog = new MP3PlayerGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() 
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) 
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    public static javax.swing.JLabel Display;
    private javax.swing.JLabel Loop;
    private javax.swing.JLabel Pause;
    private javax.swing.JLabel Play;
    private javax.swing.JList Playlist;
    private javax.swing.JProgressBar SeekBar;
    private javax.swing.JLabel SelectFile;
    private javax.swing.JLabel Stop;
    private javax.swing.JLabel exitLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

   
}
