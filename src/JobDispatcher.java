/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: An abstract class that functions as a blue-print for different types of job dispatchers.
 * Different job dispatchers can implement their own logic for how jobs are dispatched to servers.
 */

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public abstract class JobDispatcher {

    private ArrayList<Server> servers; // list of servers
    private double sysTime; // system time
    private LinkedList<Job> jobsHandled;// list of jobs handled
    private ServerFarmViz serverFarmViz; // visualization object

    /**
     * Constructor for the JobDispatcher class
     * @param k the number of servers
     * @param showViz whether to show visualization or not
     */
    public JobDispatcher(int k, boolean showViz){
        servers = new ArrayList<>();
        for(int i=0; i<k; i++){ // create k servers
            servers.add(new Server());
        }
        sysTime = 0.0;
        jobsHandled = new LinkedList<Job>();
        serverFarmViz = new ServerFarmViz(this, showViz);
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
    public ArrayList<Server> getServerList(){
        return servers;
    }

    /**
     * Calls the processTo method for each server in the list of servers
     * @param time the time until which to process jobs for each server
     */
    public void advanceTimeTo(double time){
        sysTime = time;
        for(Server server : servers){
            server.processTo(time);
        }
    }

    /**
     * Abstract method to pick a server for a job using different strategies
     * @param j the job required to be dispatched
     * @return the server to which the job should be dispatched
     */
    public abstract Server pickServer(Job j);

    public void handleJob(Job job){
        jobsHandled.offer(job);
        advanceTimeTo(job.getArrivalTime()); // advance time to the arrival time of the job
        serverFarmViz.repaint(); // update the visualization
        Server serverChoosen =  pickServer(job); // pick a server for the job
        serverChoosen.addJob(job); // add the job to the chosen server
        serverFarmViz.repaint(); // update the visualization
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
        for(Server s : getServerList()){
            double serverFinishTime = s.remainingWorkInQueue();
            if(serverFinishTime > minTimeReq) { // if the finish time of the server is greater than the minimum time required, update the minimum time required
                minTimeReq = serverFinishTime;
            }
        }
        advanceTimeTo(sysTime + minTimeReq);
    }

    /**
     * Draws the visualization of the job dispatcher
     * @param g the graphics object used for drawing
     */
    public void draw(Graphics g){
        double sep = (ServerFarmViz.HEIGHT - 20) / (getServerList().size() + 2.0);
        g.drawString("Time: " + getTime(), (int) sep, ServerFarmViz.HEIGHT - 20);
        g.drawString("Jobs handled: " + getNumJobsHandled(), (int) sep, ServerFarmViz.HEIGHT - 10);
        for(int i = 0; i < getServerList().size(); i++){
            getServerList().get( i ).draw(g, (i % 2 == 0) ? Color.GRAY : Color.DARK_GRAY, (i + 1) * sep, getServerList().size());
        }
    }
}
