/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taskone.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;


/**
 *
 * @author Seaboard
 */
public class POI implements Comparable<POI>{
    private int type;
    private double xVal, yVal;

    public POI (){
        type = 0;
        xVal = 0;
        yVal = 0;
    }
    
    public POI (int t, double x, double y){
        type = t;
        xVal = x;
        yVal = y;
    }
    
    public void setType(int t){
        type = t;
    }
    public void setX (double x){
        xVal = x;
    }
    public void setY (double y){
        yVal = y;
    }
    public double getX(){
        return xVal;
    }
    public double getY(){
        return yVal;
    }
    public int getType(){
        return type;
    }
    @Override
    public String toString(){
        return String.format("Type: %s\t\tX: %6s\tY: %6s", this.type, this.xVal, this.yVal);
    }
    @Override
    public int compareTo(POI o) {
        
        if (this.type == o.getType()) {
            if (this.xVal == o.getX() && this.yVal == o.getY())
                return 0;
            else if ( (this.xVal * this.yVal) < (o.getX() * o.getY()))
                return -1;
            else
                return 1;
        }
        else {
            if (this.type < o.getType())
                return -1;
            else
                return 1;
        }
    }
    
    public int HashCode(){
        return String.format("%s %s %s", type, xVal, yVal).hashCode();
    }

    //88888888888888888888888888888888888888888888888888888888888888888888888888*/
    
    
    static public HashSet<POI> LoadPOIArray(File file){

        BufferedReader br;
        String line;
        HashSet<POI> pts = new HashSet<> ();

        try{
            // creates a BufferedRaeder to access File with Object data as supplied by ITC313
            br = new BufferedReader(new FileReader("Ass1_Task1_POIs.txt"));
            
            // The file should be of format
            // TYPE(int)    X(int)  Y(int)
            
            
            // loop through the file reading each line
            while ((line = br.readLine()) != null){

                // breaks the line into parts
                String[] t1 = line.split("\t");
                                
                pts.add(new POI(Integer.parseInt(t1[0]),Integer.parseInt(t1[1]),Integer.parseInt(t1[2])));
            }

            Iterator it = pts.iterator();
            int i = 0;
            while (it.hasNext()){
                System.out.println(i+"\t"+it.next());
                i++;
            }
            return pts;
        }
        catch (IOException e){
            System.err.println( "Error - cant open the source file." );
            return null;
        }
    }
/*
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
  */  
    

}
