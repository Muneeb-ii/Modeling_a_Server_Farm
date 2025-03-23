/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A job dispatcher that dispatches jobs to servers with the queue having smallest remaining time.
 */

 public class LeastWorkDispatcher extends JobDispatcher {
 
    /**
     * Constructor for the LeastWorkDispatcher class
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public LeastWorkDispatcher(int k, boolean showViz){
        super(k, showViz);
    }

    @Override
    public Server pickServer(Job j){
        int shortestQueueIndex = 0; // index of the server with the shortest queue
        double minimumRemainingTime = getServerList().get(0).remainingWorkInQueue(); // minimum remaining time of the server

        for (int i = 0; i < getServerList().size(); i++){
            if (getServerList().get(i).remainingWorkInQueue() < minimumRemainingTime){
                shortestQueueIndex = i; // update the index of the server with the shortest queue
                minimumRemainingTime = getServerList().get(i).remainingWorkInQueue(); // update the minimum remaining time of the server with the shortest queue
            }
        }

        return getServerList().get(shortestQueueIndex); // return the server with the shortest queue
    }
}