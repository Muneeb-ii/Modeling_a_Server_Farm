/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: 
 * 
 */

public class Server {
    
    LinkedList<Job> jobQueue; // queue of jobs waiting to be processed
    double sysTime; // system time
    double remainingTime; // remaining time for the current job being processed
    int numOfJobsProcessed; // number of jobs processed
    
    /**
     * Constructor for the Server class
     */
    public Server(){
        jobQueue = new LinkedList<Job>();
        sysTime = 0.0;
        remainingTime = 0.0;
        numOfJobsProcessed = 0;
    }
}