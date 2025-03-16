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
    private LinkedList<Job> jobQueue; // queue of jobs waiting to be dispatched
    private ServerFarmViz serverFarmViz; // visualization object
}
