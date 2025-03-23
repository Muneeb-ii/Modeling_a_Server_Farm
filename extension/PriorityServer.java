/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: A server that processes jobs in a priority queue based on their priority (the higher the time required, the higher the priority).
 * 
 */

 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Toolkit;

public class PriorityServer {
    
    LinkedList<Job> jobQueue; // queue of jobs waiting to be processed
    private double sysTime; // system time
    private double remainingTime; // remaining time for all jobs to be processed
    private int numOfJobsProcessed; // number of jobs processed
    private int numOfJobsInQueue; // number of jobs in the queue
    private double totalWaitingTime; // total waiting time for all jobs
    
    /**
     * Constructor for the Server class
     */
    public PriorityServer(){
        jobQueue = new LinkedList<Job>();
        sysTime = 0.0;
        remainingTime = 0.0;
        numOfJobsProcessed = 0;
        numOfJobsInQueue = 0;
        totalWaitingTime = 0.0;
    }

    /**
     * Adds a job to the job queue based on priority (higher processing time = higher priority)
     * @param job the job to be added
     */
    public void addJob(Job job){
        if (jobQueue.isEmpty()) {
            jobQueue.offer(job);
        } 
        else {
            // Insert job in the queue based on priority (higher processing time = higher priority)
            boolean added = false;
            for (int i = 0; i < jobQueue.size(); i++) {
                if (job.getProcessingTimeNeeded() > jobQueue.get(i).getProcessingTimeNeeded()) {
                    jobQueue.add(i, job);
                    added = true;
                    break;
                }
            }
            if (!added) {
                jobQueue.offer(job);
            }
        }
        remainingTime += job.getProcessingTimeNeeded();
        numOfJobsInQueue++;
    }

    /**
     * Processes jobs in the queue until the specified time
     * @param time the time until which to process jobs
     */
    public void processTo(double time){
        double timeLeft = time - sysTime; // time left to process
        while(timeLeft>0.0){
            if(!jobQueue.isEmpty()){
                Job currentJob = jobQueue.peek(); // get the job at the front of the queue
                double timeToProcess = currentJob.getProcessingTimeRemaining(); // time to process the current job

                if(timeToProcess > timeLeft){ // if the time to process is more than the time left, process for the time left
                    timeToProcess = timeLeft;
                }
                
                currentJob.process(timeToProcess, sysTime); // process the job
                timeLeft -= timeToProcess; // reduce the time left
                sysTime += timeToProcess; // move the system time forward
                remainingTime -= timeToProcess; // remove the time used in processing from the remaining time
                
                if(currentJob.isFinished()){ // if the job is finished, remove it from the queue
                    jobQueue.poll();
                    totalWaitingTime += currentJob.timeInQueue(); // add the time spent in the queue to the total waiting time
                    numOfJobsProcessed++;
                    numOfJobsInQueue--;
                }
            }
            else{
                sysTime += timeLeft; // if there are no jobs in the queue, just move the system time forward
                timeLeft = 0.0;
            }
        }
    }

    /**
     * Returns the remaining time required to process all the jobs in the queue
     * @return the remaining time
     */
    public double remainingWorkInQueue(){
        return remainingTime;
    }

    /**
     * Returns the number of jobs currently in the queue
     * @return the number of jobs
     */
    public int size(){
        return numOfJobsInQueue;
    }

    /**
     * Returns the number of jobs processed
     * @return the number of jobs processed
     */
    public int jobsProcessed(){
        return numOfJobsProcessed;
    }

    /**
     * Returns the total waiting time for all jobs
     * @return the total waiting time
     */
    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    
    /**
     * Draws the server's state on the screen
     * @param g 
     * @param c
     * @param loc
     * @param numberOfServers
     */
    public void draw(Graphics g, Color c, double loc, int numberOfServers){
        double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
        g.setColor(Color.BLACK);
        g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), (int) (72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
        g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2, (int) (loc + .2 * sep));
        g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5 , (int) (loc + .55 * sep));
        g.setColor(c); 
        g.fillRect((int) (3 * sep), (int) loc, (int) (.8 * remainingWorkInQueue()), (int) sep);
        g.drawOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
        if (remainingWorkInQueue() == 0) g.setColor(Color.GREEN.darker());
        else g.setColor(Color.RED.darker());
        g.fillOval(2 * (int) sep, (int) loc, (int) sep, (int) sep);
    }
}