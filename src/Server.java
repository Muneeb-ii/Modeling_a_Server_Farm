/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of the class: 
 * 
 */

public class Server {
    
    LinkedList<Job> jobQueue; // queue of jobs waiting to be processed
    private double sysTime; // system time
    private double remainingTime; // remaining time for all jobs to be processed
    private int numOfJobsProcessed; // number of jobs processed
    private int numOfJobsInQueue; // number of jobs in the queue
    
    /**
     * Constructor for the Server class
     */
    public Server(){
        jobQueue = new LinkedList<Job>();
        sysTime = 0.0;
        remainingTime = 0.0;
        numOfJobsProcessed = 0;
    }

    /**
     * Adds a job to the job queue
     * @param job the job to be added
     */
    public void addJob(Job job){
        jobQueue.offer(job);
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
            if(jobQueue.isEmpty()){
                sysTime += timeLeft; // if there are no jobs, just move the system time forward
            }
            else{
                Job currentJob = jobQueue.peek(); // get the job at the front of the queue
                double timeToProcess = currentJob.getProcessingTimeNeeded(); // time to process the current job

                if(timeToProcess > timeLeft){ // if the time to process is more than the time left, process for the time left
                    timeToProcess = timeLeft;
                }
                
                currentJob.process(timeToProcess, sysTime); // process the job
                timeLeft -= timeToProcess; // reduce the time left
                sysTime += timeToProcess; // move the system time forward
                remainingTime -= timeToProcess; // remove the time used in processing from the remaining time
                
                if(currentJob.isFinished()){ // if the job is finished, remove it from the queue
                    jobQueue.poll();
                    numOfJobsProcessed++;
                    numOfJobsInQueue--;
                }
            }
        }
    }

    /**
     * Returns the remaining time required to process all the jobs in the queue
     * @return 
     */
    public double remainingWorkInQueue(){
        return remainingTime;
    }

    /**
     * Returns the number of jobs currently in the queue
     * @return
     */
    public int size(){
        return numOfJobsInQueue;
    }
}