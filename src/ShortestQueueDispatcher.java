/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A job dispatcher that dispatches jobs to servers randomly.
 */

public class ShortestQueueDispatcher extends JobDispatcher {
 
    /**
     * Constructor for the RandomDispatcher class
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public ShortestQueueDispatcher(int k, boolean showViz){
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j){
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