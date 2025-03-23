/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A job dispatcher that dispatches jobs to servers randomly.
 */

 import java.util.Random;
 public class PriorityRandomDispatcher extends PriorityJobDispatcher {
     
     Random rand = new Random(); // Random number generator
 
     /**
      * Constructor for the RandomDispatcher class
      * @param k the number of servers
      * @param showViz whether to show visualization or not
      */
     public PriorityRandomDispatcher(int k){
        super(k);
     }
 
     /**
      * Picks a server randomly from the list of servers
      * @param j the job to be dispatched
      * @return the server to which the job is dispatched
      */
     @Override
     public PriorityServer pickServer(Job j){
         int randomIndex = rand.nextInt(getServerList().size());
         return getServerList().get(randomIndex);
     }
 }