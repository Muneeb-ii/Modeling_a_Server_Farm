/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A job dispatcher that dispatches jobs to servers in a round-robin fashion.
 */

public class RoundRobinDispatcher extends JobDispatcher {

    private int currServerIndex; // index of the current server to which the job will be dispatched

    /**
     * Constructor for the RoundRobinDispatcher class
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public RoundRobinDispatcher(int k, boolean showViz){
        super(k, showViz);
        currServerIndex = -1;
    }

    /**
     * Method to pick a server for a job in a round-robin fashion
     * @param j the job to be dispatched
     * @return the server to which the job will be dispatched
     */
    @Override
    public Server pickServer(Job j){
        currServerIndex++; // increment the index of the current server
        if (currServerIndex > getServerList().size() - 1){
            currServerIndex = 0;
        }
        return getServerList().get(currServerIndex); // return the server at the current index
    }
}