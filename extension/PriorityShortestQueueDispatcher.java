/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A job dispatcher that dispatches jobs to servers with the shortest queue.
 */

public class PriorityShortestQueueDispatcher extends PriorityJobDispatcher {
 
    /**
     * Constructor for the ShortestQueueDispatcher class
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public PriorityShortestQueueDispatcher(int k){
        super(k);
    }

    /**
     * Method to pick a server for a job with the shortest queue
     * @param j the job to be dispatched
     * @return the server to which the job will be dispatched
     */
    @Override
    public PriorityServer pickServer(Job j){
        int shortestQueueIndex = 0; // index of the server with the shortest queue
        int shortestQueueSize = getServerList().get(0).size(); // size of the queue of the first server

        for (int i = 0; i < getServerList().size(); i++){
            if (getServerList().get(i).size() < shortestQueueSize){
                shortestQueueIndex = i; // update the index of the server with the shortest queue
                shortestQueueSize = getServerList().get(i).size(); // update the size of the queue of the server with the shortest queue
            }
        }

        return getServerList().get(shortestQueueIndex); // return the server with the shortest queue
    }
}