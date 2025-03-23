/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: 
 * This class implements a Hybrid Dispatcher that selects the server
 * based on a composite metric that takes into account both the number of jobs (queue size)
 * and the remaining processing time (work load) on each server.
 * By assigning weights to these factors, it attempts to balance between load and waiting time.
 */
public class HybridQueueDispatcher extends JobDispatcher {
 
    /**
     * Constructor for the HybridQueueDispatcher class.
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public HybridQueueDispatcher(int k, boolean showViz){
        super(k, showViz);
    }

    /**
     * This method selects a server to dispatch the job based on a composite metric.
     * The metric is computed as a weighted sum of the server's queue size and its remaining processing time.
     *
     * @param j the job to be dispatched 
     * @return the server with the minimum composite metric score
     */
    @Override
    public Server pickServer(Job j){
        // Weights for each component of the composite metric
        double weightOfShortestQueue = 0.5; 
        double weightOfLeastWorkQueue = 0.5; 
        
        // Initialize with the first server's composite metric
        int leastWeightQueueIndex = 0; 
        double leastWeight = getServerList().get(0).size() * weightOfShortestQueue 
                + getServerList().get(0).remainingWorkInQueue() * weightOfLeastWorkQueue; 

        // Iterate over all servers to find the one with the smallest composite score
        for (int i = 1; i < getServerList().size(); i++){
            // Calculate the composite metric for the current server
            double currentMetric = getServerList().get(i).size() * weightOfShortestQueue 
                    + getServerList().get(i).remainingWorkInQueue() * weightOfLeastWorkQueue;
            
            // If the current server's composite score is lower, update the chosen index and metric
            if (currentMetric < leastWeight){
                leastWeightQueueIndex = i; // update the index of the server with the smallest metric
                leastWeight = currentMetric; // update the smallest composite metric found
            }
        }

        // Return the server with the smallest composite metric
        return getServerList().get(leastWeightQueueIndex);
    }
}