/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import taskone.model.POI;

/**
 *
 * @author Seaboard
 */
public class TaskOneViewTwo extends JPanel {
    private double scale = 1.0;
    private int size = 500;
    private HashSet<POI> theMap;
    
    
    private JButton in;
    private JButton out;
    private JButton load;
    //(1) Petrol station, (2) Taxi Stand, (3) ATM, (4) Hospital and (5) Shopping centre
    private JCheckBox Petrol, Taxi, ATM, Hospital, Shopping;
    
    
    public TaskOneViewTwo(double scale, int size){
        // Creates the body of the frame
        Frame myFrame = new Frame(size);
        
        init();
        
        
        
        myFrame.add(this);
        
        
        
        myFrame.setVisible(true);
    }
    
    private void init(){
        this.setLayout(new BorderLayout());
        this.setSize((int)(scale * size), (int)(scale * size));
    }

    private class Frame extends JFrame{
        public Frame(int size){
            this.setTitle("Welcome to ITC322 assignment two task one");
            this.setSize(size+50, size+100);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
    
    private class UserInterface extends JPanel {
        public UserInterface(){
                        in = new JButton("ZOOM IN");
            in.setActionCommand("IN");
            out = new JButton("ZOOM OUT");
            out.setActionCommand("OUT");
            load = new JButton("LOAD MAP");
            load.setActionCommand("LOAD");
            Petrol = new JCheckBox("Petrol");
            Taxi = new JCheckBox("Taxi Rank");
            ATM = new JCheckBox("ATM");
            Hospital = new JCheckBox("Hospital");
            Shopping = new JCheckBox("Shopping Centre");

            this.setLayout(new GridLayout(2,6));

            //this.add(new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, 0, 1000), BorderLayout.NORTH);
            this.setBorder(new LineBorder(Color.GREEN));
            this.add(Petrol);
            this.add(Taxi);
            this.add(ATM);
            this.add(Hospital);
            this.add(Shopping);

            this.add(in);
            this.add(new JPanel());
            this.add(load);
            this.add(new JPanel());
            this.add(out);

        }
    }
    
    class Panel2 extends JPanel{
        Panel2(){
            this.setBackground(Color.BLACK);
        }
    }
    
    class Panel3 extends JPanel{
        Panel3(double scale, int dimension){
            this.setLayout(new BorderLayout(5,5));
            this.add(new Panel2(), BorderLayout.NORTH);
            this.add(new Panel2(), BorderLayout.CENTER);
            //this.add(new TaskOneView.MapPanel(scale, dimension), BorderLayout.CENTER);
        }
    }
    
    
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                TaskOneViewTwo ex = new TaskOneViewTwo (1.0, 750);
                ex.setVisible(true);
            }
        });
    }
}
