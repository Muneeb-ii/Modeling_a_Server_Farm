/*
file name:      ServerFarmSimulation.java
Authors:        Ike Lage (modified by [Muneeb Azfar Nafees])
last modified:  23 March 2025
*/

public class ServerFarmSimulation {

    public static void main(String[] args) {

        // Simulation parameters
        int meanArrivalTime = 3;       // Average interval between job arrivals
        int meanProcessingTime = 100;    // Average processing time for a job
        boolean showViz = false;         // Disable visualization for experiments

        // Experiment 1: Dispatcher Comparison
        int numServersExp1 = 34;         // Fixed number of servers for Experiment 1
        int numJobsExp1 = 10000000;      // Number of jobs for Experiment 1

        System.out.println("Experiment 1: Dispatcher Comparison (34 servers, " + numJobsExp1 + " jobs)");
        String[] dispatcherTypes = {"random", "round", "least", "shortest"};
        for (String dType : dispatcherTypes) {
            // Initialize the job maker for this experiment
            JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

            // Create the appropriate dispatcher based on the type
            JobDispatcher dispatcher = null;
            if (dType.equals("random")) {
                dispatcher = new RandomDispatcher(numServersExp1, showViz);
            } else if (dType.equals("round")) {
                dispatcher = new RoundRobinDispatcher(numServersExp1, showViz);
            } else if (dType.equals("shortest")) {
                dispatcher = new ShortestQueueDispatcher(numServersExp1, showViz);
            } else if (dType.equals("least")) {
                dispatcher = new LeastWorkDispatcher(numServersExp1, showViz);
            }

            // Dispatch jobs
            for (int i = 0; i < numJobsExp1; i++) {
                dispatcher.handleJob(jobMaker.getNextJob());
            }
            dispatcher.finishUp();

            // Print out the formatted result for this dispatcher
            System.out.println("Dispatcher: " + dType + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());
        }

        System.out.println("\nExperiment 2: Varying Server Count (ShortestQueueDispatcher, " + numJobsExp1 + " jobs)");
        // Experiment 2: Vary the number of servers from 30 to 40 for the ShortestQueueDispatcher
        for (int s = 30; s <= 40; s++) {
            // Reinitialize JobMaker for each simulation run
            JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

            // Use the ShortestQueueDispatcher for this experiment
            JobDispatcher dispatcher = new ShortestQueueDispatcher(s, showViz);

            // Dispatch jobs
            for (int i = 0; i < numJobsExp1; i++) {
                dispatcher.handleJob(jobMaker.getNextJob());
            }
            dispatcher.finishUp();

            // Print out the formatted result for this server count
            System.out.println("Servers: " + s + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());
        }
    }
}