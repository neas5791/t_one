/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskone.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import taskone.model.POI;

/**
 *
 * @author Seaboard
 */
public class TaskOneView extends JFrame{
    private double scale = 1.0;
    private int size = 500;
    
    private TaskOne taskPanel;
    
    public TaskOneView(double scale, int size){
        // Creates the body of the frame
        taskPanel = new TaskOne(scale, size);
        
        this.setSize(size+50, size+100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(taskPanel);
        this.setVisible(true);
    }
    
    /**
     *
     * @author SeAN
     */
    public class TaskOne extends JPanel{

        public TaskOne(double scale, int dimension){

            this.setLayout(new BorderLayout());

            this.add(new Panel2(),BorderLayout.WEST);
            this.add(new Panel2(),BorderLayout.EAST);
            this.add(new Panel2(),BorderLayout.SOUTH);
            this.add(new Panel2(), BorderLayout.NORTH);
            this.add(new Panel3(scale, dimension), BorderLayout.CENTER);

            //this.setTitle(String.format("TestPaintComponent %2.2f ",scale));
            this.setSize((int)(scale * dimension), (int)(scale * dimension));
        }

        class MapPanel extends JPanel {
            // Sets up arraylist to store POI objects
            private java.util.ArrayList<POI> pts;
            private java.util.ArrayList<java.util.ArrayList<POI>> pts1;
            //(1) Petrol station, (2) Taxi Stand, (3) ATM, (4) Hospital and (5) Shopping
            private java.util.ArrayList<POI> petrol;
            private java.util.ArrayList<POI> taxi;
            private java.util.ArrayList<POI> atm;
            private java.util.ArrayList<POI> hosp;
            private java.util.ArrayList<POI> shop;

            // Scale factor to apply to graphic
            private double scale;
            // Size of graphic
            private int dimension;

            public MapPanel(double scale, int dimension){
                this.scale = scale;
                this.dimension = dimension;

                LoadPOIArray();
                SortPOI();
            }

            public void setScale (double newScale){
                // limits the scale factor
                if (newScale <= 0 || newScale > 1.0)
                    return;
                scale = newScale;
            }

            public void setSize(int dimension){
                this.dimension = dimension;
            }

            public double getScale(){  return scale;  }
            public int getDimension() { return dimension;  }

            @Override
            protected void paintComponent(Graphics g){
                Font f = new Font("Serif", Font.BOLD, ((int)(12 * scale))) ;
                g.setFont(f);
                super.paintComponent(g);
                Iterator it = pts.iterator();
                int i = 0;
                while(it.hasNext()){
                    POI temp = (POI) it.next();
                    int x = (int) (scale * temp.getX()*(dimension/100));
                    int y = (int) (scale * temp.getY()*(dimension/100));

                    g.drawString((String.format("%s", i)), x, y);
                    i++;
                }
                g.drawString("x",0,0);
                g.drawString("x",(int)(scale * dimension),(int)(scale * dimension));
            }

            private void LoadPOIArray(){
                BufferedReader br;
                String line;
                pts = new java.util.ArrayList<>();

                try{
                    // creates a BufferedRaeder to access File with Object data as supplied by ITC313
                    br = new BufferedReader(new FileReader("Ass1_Task1_POIs.txt"));

                    // loop through the file reading each line
                    while ((line = br.readLine()) != null){

                        // breaks the line into parts
                        String[] t1 = line.split("\t");

                        // tests the string to make sure line conforms to data format
                        if (t1.length != 2 ) break;

                        String[] t2 = t1[1].split(",");

                        // tests the string to make sure line conforms to data format
                        if (t2.length != 2) break;

                        pts.add(new POI(Integer.parseInt(t1[0]),Double.parseDouble(t2[0]),Double.parseDouble(t2[1])));
                    }
                    Iterator it = pts.iterator();
                    int i = 0;
                    while (it.hasNext()){
                        System.out.println(i+"\t"+it.next());
                        i++;
                    }
                }
                catch (IOException e){
                    System.err.println( "Error - cant create the file." );
                    return;
                }
            }

            private void SortPOI(){
                petrol = new java.util.ArrayList<>();
                taxi = new java.util.ArrayList<>();
                atm  = new java.util.ArrayList<>();
                hosp = new java.util.ArrayList<>();
                shop = new java.util.ArrayList<>();

                for (POI p : pts){
                    switch (p.getType()){
                        case 1:
                            petrol.add(p);
                            break;
                        case 2:
                            taxi.add(p);
                            break;
                        case 3:
                            atm.add(p);
                            break;
                        case 4:
                            hosp.add(p);
                            break;
                        case 5:
                            shop.add(p);
                            break;
                        default:
                            break;
                    }
                }

                System.out.println("Petrol: " + petrol.size());
                System.out.println("taxi: " + taxi.size());
                System.out.println("atm: " + atm.size());
                System.out.println("hosp: " + hosp.size());
                System.out.println("shop: " + shop.size());
            }
        }

        class Panel1 extends JPanel{
            private JButton in;
            private JButton out;
            private JButton load;
            //(1) Petrol station, (2) Taxi Stand, (3) ATM, (4) Hospital and (5) Shopping centre
            private JCheckBox Petrol, Taxi, ATM, Hospital, Shopping;

            Panel1(){
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

                /* moove these to controller class*/
                /*this.addZoomListener(new TestPaintComponent.ZoomClick());*/
            }

            public void addLoadListener(ActionListener listenForLoad){
                load.addActionListener(listenForLoad);
            }

            public void addZoomListener (ActionListener listenForZoom){
                in.addActionListener(listenForZoom);
                out.addActionListener(listenForZoom);
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
                this.add(new Panel1(), BorderLayout.NORTH);
                this.add(new MapPanel(scale, dimension), BorderLayout.CENTER);
            }
        }
    }
    
    
    // Setters and getters
    public void setScale (double scale){
        this.scale = scale;
    }
    public void setSize(int size){
        this.size = size;
    }
    
    public double[] getSizeScale() {
        double [] temp = new double[2];
        
        temp[0] = this.scale;
        temp[1] = this.size;
        
        return temp;  
    }
    
}
