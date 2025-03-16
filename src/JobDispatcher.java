/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: An abstract class that functions as a blue-print for different types of job dispatchers.
 * Different job dispatchers can implement their own logic for how jobs are dispatched to servers.
 */

import java.util.ArrayList;

public abstract class JobDispatcher {

    private ArrayList<Server> servers; // list of servers
    private double sysTime; // system time
    private LinkedList<Job> jobsHandled;// list of jobs handled
    private int numOfJobsHandled; // number of jobs handled 
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
        numOfJobsHandled = 0;
        serverFarmViz = new ServerFarmViz(this, showViz);
    }
    
    /**
     * Returns the system time
     * @return the system time
     */
    public double getTime(){
        return sysTime;
    }
}
