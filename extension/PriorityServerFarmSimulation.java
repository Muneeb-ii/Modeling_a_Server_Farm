/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: The main class that simulates a server farm with different types of priority-based job dispatchers.
 */

public class PriorityServerFarmSimulation {

    public static void main(String[] args) {
        // Simulation parameters
        int meanArrivalTime = 3;       // Average interval between job arrivals
        int meanProcessingTime = 100;  // Average processing time for a job

        int numServers = 34;           // Number of servers in the farm
        int numJobs = 100000;         // Number of jobs to process

        // Array of dispatcher types to test
        String[] dispatcherTypes = {"random", "round", "shortest", "least"};

        // Loop through each dispatcher type
        for (String dispatcherType : dispatcherTypes) {
            // Initialize the job maker for each simulation run
            JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

            // Create a PriorityJobDispatcher instance based on the dispatcher type
            PriorityJobDispatcher dispatcher = null;
            if (dispatcherType.equals("random")) {
                dispatcher = new PriorityRandomDispatcher(numServers);
            } else if (dispatcherType.equals("round")) {
                dispatcher = new PriorityRoundRobinDispatcher(numServers);
            } else if (dispatcherType.equals("shortest")) {
                dispatcher = new PriorityShortestQueueDispatcher(numServers);
            } else if (dispatcherType.equals("least")) {
                dispatcher = new PriorityLeastWorkDispatcher(numServers);
            }

            // Process the specified number of jobs
            for (int i = 0; i < numJobs; i++) {
                dispatcher.handleJob(jobMaker.getNextJob());
            }
            dispatcher.finishUp(); // Finish all remaining jobs

            // Print out the result for the current dispatcher type
            System.out.println("Dispatcher: " + dispatcherType + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());
        }
    }
}