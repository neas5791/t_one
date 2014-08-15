package taskone.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import javax.swing.JFileChooser;
import taskone.model.POI;
import taskone.view.TaskOneView;
import taskone.view.TaskOneViewTwo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SeAN
 */
public class TaskOneController {
    //private int frameSize;
    private TaskOneView theView;

    private HashSet<POI> theMap;
    
    public TaskOneController(TaskOneView theView){
        this.theView = theView;

        
        theView.addLoadListener(new LoadListener());
    }
    
    
    public static void main (String[] args){
        int frameSize = 750;

        TaskOneView theView = new TaskOneView(frameSize/100, frameSize);
        
        TaskOneController theController = new TaskOneController(theView);        
    }
    
    
    class LoadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            /* This opens JFileChooser and System.out's the filename */
            JFileChooser fc = new JFileChooser(".");
            int returnValue = fc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                System.out.println(selectedFile.getName());
            }
            
            theMap = POI.LoadPOIArray(fc.getSelectedFile());
            //theView.setMap(theMap);
            //theView.repaint();
        }
    }
}


/*      
    public void setFrameSize(int frameSize) {
        this.frameSize = frameSize;
    }
    
    public int getFrameSize() {  
        return this.frameSize;
    }

    private void init(){
        // initially set frame size to 750 x 750 px
        setFrameSize(750);
        
        // create task one frame
        
        
    }

/* This opens JFileChooser and System.out's the filename 
        JFileChooser fc = new JFileChooser(".");
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fc.getSelectedFile();
          System.out.println(selectedFile.getName());
        }
        
        // need to add jfilechooser file here
//        HashSet<POI> list = POI.LoadPOIArray(fc.getSelectedFile());
*/