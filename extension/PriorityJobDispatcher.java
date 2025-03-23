/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: An abstract class that functions as a blue-print for different types of job dispatchers.
 * Different job dispatchers can implement their own logic for how jobs are dispatched to servers.
 */
import java.util.ArrayList;
public abstract class PriorityJobDispatcher {

    private ArrayList<PriorityServer> servers; // list of servers
    private double sysTime; // system time
    private LinkedList<Job> jobsHandled;// list of jobs handled

    /**
     * Constructor for the JobDispatcher class
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public PriorityJobDispatcher(int k, boolean showViz){
        servers = new ArrayList<>();
        for(int i=0; i<k; i++){ // create k servers
            servers.add(new PriorityServer());
        }
        sysTime = 0.0;
        jobsHandled = new LinkedList<Job>();
    }
    
    /**
     * Returns the system time
     * @return the system time
     */
    public double getTime(){
        return sysTime;
    }

    /**
     * Returns the list of servers
     * @return the list of servers
     */
    public ArrayList<PriorityServer> getServerList(){
        return servers;
    }

    /**
     * Calls the processTo method for each server in the list of servers
     * @param time the time until which to process jobs for each server
     */
    public void advanceTimeTo(double time){
        sysTime = time;
        for(PriorityServer server : servers){
            server.processTo(time);
        }
    }

    /**
     * Abstract method to pick a server for a job using different strategies
     * @param j the job required to be dispatched
     * @return the server to which the job should be dispatched
     */
    public abstract PriorityServer pickServer(Job j);

    public void handleJob(Job job){
        jobsHandled.offer(job);
        advanceTimeTo(job.getArrivalTime()); // advance time to the arrival time of the job
        PriorityServer serverChoosen =  pickServer(job); // pick a server for the job
        serverChoosen.addJob(job); // add the job to the chosen server
    }

    /**
     * Returns the number of jobs handled
     * @return the number of jobs handled
     */
    public int getNumJobsHandled(){
        return jobsHandled.size();
    }

    /**
     * Returns the average waiting time of all the jobs handled
     * @return the average waiting time of all the jobs handled
     */
    public double getAverageWaitingTime(){
        double averageWaitingTime = 0.0;
        for(Job j :jobsHandled){
            averageWaitingTime += j.timeInQueue();
        }
        averageWaitingTime = averageWaitingTime / getNumJobsHandled();
        return averageWaitingTime;
    }

    /**
     * Finishes up all the jobs in the queue by advancing the system time to the time required to finish all the jobs
     * in each server
     */
    public void finishUp(){
        double minTimeReq = 0.0; // minimum time required to finish all the jobs in the list
        for(PriorityServer s : getServerList()){
            double serverFinishTime = s.remainingWorkInQueue();
            if(serverFinishTime > minTimeReq) { // if the finish time of the server is greater than the minimum time required, update the minimum time required
                minTimeReq = serverFinishTime;
            }
        }
        advanceTimeTo(sysTime + minTimeReq);
    }
}
