/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import com.sun.xml.internal.ws.encoding.MtomCodec;
import java.util.Stack;

/**
 *
 * @author Wycliffe
 */
public class OrderCombiner {
    
    double lat_pick_up1,long_pick_up1, // pick up 1
            lat_pick_up2 ,long_pick_up2, // pick up 2
            lat_drop_ff1,long_drop_ff1, // drop off for pick up 1
            lat_drop_ff2, long_drop_ff2; // drop off for pick up 2
    
    double radius = 4.0;
    
    // parametarised constructor
    public OrderCombiner(double lat_pick_up1, double long_pick_up1, double lat_pick_up2 , double long_pick_up2,
           double lat_drop_ff1,double long_drop_ff1, double lat_drop_ff2, double long_drop_ff2)
    {
        //X
       lat_pick_up1 = this.lat_pick_up1;
       long_pick_up1 = this.long_pick_up1;
       
       //W
       lat_pick_up2 = this.lat_pick_up2;
       long_pick_up2 = this.long_pick_up2;
       
       //Y
       lat_drop_ff1 = this.lat_drop_ff1;
       long_drop_ff1 = this.long_drop_ff1;
       
       //Z
       lat_drop_ff2 = this.lat_drop_ff2;
       long_drop_ff2 = this.long_drop_ff2;
    }
    
    // constructor
    public OrderCombiner(){
    
    }
    
    
    
    public  void determiner(double lat_pick_up1, double long_pick_up1, double lat_pick_up2 , double long_pick_up2,
           double lat_drop_ff1,double long_drop_ff1, double lat_drop_ff2, double long_drop_ff2){
    
        // Get distance between pickups , then 
        double dist_pick_ups = distance(lat_pick_up1,lat_pick_up2, long_pick_up1, long_pick_up2);
        
        // distance between the destinations.
        double dist_drop_offs = distance(lat_drop_ff1 ,lat_drop_ff2, long_drop_ff1, long_drop_ff2);
        
        if( dist_pick_ups < radius){
        
            if(dist_drop_offs <radius){
            
            System.out.println("The orders are combinable: \n ");
            System.out.println("Radius: \n " + radius );
            System.out.println("distance between pickups: \t " + dist_pick_ups);
            System.out.println("distance between drop-offs: \t " + dist_drop_offs);
            
            /*
            Then return an adjacency matrix to be passed to TSP
            */
            
            }
        }
        
        else{
        
            System.out.println("The orders are not combinable");
            System.out.println("Radius: \n " + radius );
            System.out.println("distance between pickups: \t " + dist_pick_ups);
            System.out.println("distance between drop-offs: \t " + dist_drop_offs);
        }
        
//    return null;
    }
   
    
 /**
 * Haversine method .
 * lat1, lon1 Start point lat2, lon2 End 
 * @returns Distance between to places in KM
 */
    
public static double distance(double lat1, double lat2, double lon1,
        double lon2  ) {

    final int R = 6371; // Radius of the earth

    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    
    double distance = R * c ; // in Kilometers
   
    return distance;
}

public static void main(String... arg)
    {
        
         // Points
        double lat_x = 44.968046  , long_x= -94.420307,
               lat_w = 44.33328   , long_w= -89.132008,
                
               lat_y = 33.755787  , long_y= -116.359998,
               lat_z = 33.844843  , long_z= -116.54911 ;
        
        OrderCombiner orderCombiner = new OrderCombiner();
        
        orderCombiner.determiner(lat_x, long_x, lat_w, long_w, lat_y, long_y, lat_z, long_z);
        
        
    }
  
}
