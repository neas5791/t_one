package taskone.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import javax.swing.JFileChooser;
import taskone.model.POI;
import taskone.view.TaskOneView;

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
    
    
    
    public static void main (String[] args){
        // initially set frame size to 750 x 750 px
        int size = 750;
        
        /* This opens JFileChooser and System.out's the filename */
        JFileChooser fc = new JFileChooser(".");
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fc.getSelectedFile();
          System.out.println(selectedFile.getName());
        }
        
        // need to add jfilechooser file here
        HashSet<POI> list = POI.LoadPOIArray(fc.getSelectedFile());
        
        // creates the new frame
        TaskOneView t1 = new TaskOneView(size/100,size);
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
        }
    }
}
