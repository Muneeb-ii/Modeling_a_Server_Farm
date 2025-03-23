/**
* Author: Muneeb Azfar Nafees
* 
* Purpose of the class: The main class that simulates a server farm with different types of job dispatchers.
*/

public class PriorityServerFarmSimulation {

    public static void main(String[] args) {

        if (args.length < 3){
            System.out.println("Please provide the number of servers, number of jobs, and dispatcher type as command line arguments.");
            return;
        }
        

        // You can explore how these change your results if you want!
        // How often a new job arrives at the server farm, on average
        int meanArrivalTime = 3;
        // How long a job takes to process, on average
        int meanProcessingTime = 100;

        // Debugging settings
        int numServers = Integer.parseInt(args[0]); // Numbers of servers in the farm
        int numJobs = Integer.parseInt(args[1]); // Number of jobs to process
    
        // Main experiment settings
        /**
         * int numServers = 34 ; //Numbers of servers in the farm
         * int numJobs = 10000000 ; //Number of jobs to process
         * boolean showViz = false ; //Set to true to see the visualization, and false
         * to run your experiments
         */

        String dispatcherType = args[2]; // Which jobDispatcher to use

        // Initialize the job maker with the mean arrival and processing time
        JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

        // Create a dispatcher of the appropriate type
        PriorityJobDispatcher dispatcher = null;
        if (dispatcherType.equals("random")) {
            dispatcher = new PriorityRandomDispatcher(numServers);
        }
        /* 
        else if (dispatcherType.equals("round")) {
            dispatcher = new RoundRobinDispatcher(numServers, showViz);
        } 
        else if (dispatcherType.equals("shortest")) {
            dispatcher = new ShortestQueueDispatcher(numServers, showViz);
        } 
        else if (dispatcherType.equals("least")) {
            dispatcher = new LeastWorkDispatcher(numServers, showViz);
        }
        */

        // Have the dispatched handle the specified number of jobs
        for (int i = 0; i < numJobs; i++) {
            dispatcher.handleJob(jobMaker.getNextJob());
        }
        dispatcher.finishUp(); // Finish all of the remaining jobs in Server queues

        // Print out the mean processing time
        System.out.println("Dispatcher: " + dispatcherType + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());

    }

}