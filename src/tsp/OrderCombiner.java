/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import com.sun.xml.internal.ws.encoding.MtomCodec;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import sun.rmi.runtime.Log;

/**
 *
 * @author Wycliffe
 */
public class OrderCombiner {
   
    double radius = 3.5;
    
     
    // constructor
    public OrderCombiner(){
        
    }
    
    
 /**
 * Haversine method .
 * lat1, lon1 Start point lat2, lon2 End 
 * @returns Distance between to places in KM
 */
    
    // TO DO: Use instead google maps Distance matrix to calculate distances instead. 
public  double distance(double lat1, double lat2, double lon1,
        double lon2) {

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
    

// Adjacency Matrix maker
   public double[][] adjacencyMaker( ArrayList<LatLong> nodes){
    
         System.out.println("============== The Points from start to end, starting with pickups");
         
        for(int n = 0; n < nodes.size(); n++){     
        System.out.println(n+1 + " )" +nodes.get(n).getLatitude()+" "+ nodes.get(n).getLongitude());
        
        } 
           System.out.println("===============================  \n");
           System.out.println("The adjacency matrix ");
       
          double adjacency_matrix[][] = new double[nodes.size()][nodes.size()];
//        System.out.println("Enter the adjacency matrix");
          
              // Accepting the adjacency matrix    
            for (int i = 0; i < nodes.size(); i++) // rows 
            {
                for (int j = 0; j < nodes.size(); j++) // columns
                {
                    nodes.get(i).getLatitude();
                    
                double dist = distance(nodes.get(j).getLatitude(), nodes.get(i).getLatitude(), nodes.get(j).getLongitude(), nodes.get(i).getLongitude());
                    adjacency_matrix[i][j] = dist;
//                      System.out.println(adjacency_matrix[i][j]);
                }
            }
            
     return adjacency_matrix; 
   }
   
  
   
   
   
   // Determines combinable orders from many, here 5
   public ArrayList<LatLong> determiner(){
       
              double lat_pick_ups[] =  {44.928046 , 44.903280,33.844843,44.92057,44.240309};
              double lat_drop_offs[] = {33.695787, 33.675843,33.844847,44.920474,44.240304};
              
              
              double long_pick_ups[] = {-94.410307,-94.392008,-116.54911,-93.44786,-91.493619};
              double long_drop_offs[] =  {-116.359998, -116.34911,-116.549069,-93.447851,-91.493768};
             
               ArrayList<LatLong> nodes = new ArrayList<>();
               
           for(int i= 1 ; i< lat_pick_ups.length ; i++){
               
                double dist_pick_ups = distance(lat_pick_ups[0],lat_pick_ups[i],long_pick_ups[0],long_pick_ups[i]);
                
                double dist_drop_offs  = distance(lat_drop_offs[0],lat_drop_offs[i],long_drop_offs[0],long_drop_offs[i]);
                
                                
                //Add viable point to the LatLong ArrayList
                if (dist_pick_ups <= radius && dist_drop_offs <= radius ){
                                
                    LatLong  obj_pick, obj_drop,obj_pick2, obj_drop2;
                           obj_pick = new LatLong(); 
                           obj_drop = new LatLong();
                           obj_pick2 = new LatLong(); 
                           obj_drop2 = new LatLong();
                      
                    // *********** First put the starting point *********************     
                    obj_pick.setLatitude(lat_pick_ups[0]);
                    obj_pick.setLongitude(long_pick_ups[0]);
                    
                    obj_drop.setLatitude(lat_drop_offs[0]);
                    obj_drop.setLongitude(long_drop_offs[0]);
                    
                    
                   //         
                    obj_pick2.setLatitude(lat_pick_ups[i]);
                    obj_pick2.setLongitude(long_pick_ups[i]);
                    
                    obj_drop2.setLatitude(lat_drop_offs[i]);
                    obj_drop2.setLongitude(long_drop_offs[i]);
                    
                    nodes.add(obj_pick);
                    nodes.add(obj_pick2);
                    
                    nodes.add(obj_drop);
                    nodes.add(obj_drop2);
                    
                }
           }
           
           
           if(nodes.size()>2){
               
           System.out.println("There are "+ nodes.size()/2+" combinable orders from the provided set");
           }
           else
           {
           System.out.println(" There are no combinable orders");
           }
           
              // 
       return nodes;
   
   }// end new determiner()
  
}// End Class
