# Modeling_a_Server_Farm

## Project Overview
This project simulates the operation of a server farm where jobs arrive continuously and are processed by multiple servers. Each server maintains a FIFO queue of jobs, and the simulation explores several job dispatching strategies to assign incoming jobs to servers. By comparing different dispatchers—such as RandomDispatcher, RoundRobinDispatcher, ShortestQueueDispatcher, and LeastWorkDispatcher—the project demonstrates how load balancing and job scheduling strategies impact overall system performance and waiting times.

## Core Features
- **Job Scheduling Simulation:**
  - **Job Arrival and Processing:** Jobs arrive over time with a specified mean arrival time and require a set amount of processing time.
  - **FIFO Queue Structure:** Each server uses a linked-list-based queue to ensure jobs are processed in the order they arrive.
  - **System Metrics:** Each server tracks its system time, remaining processing time, and total waiting time to facilitate performance analysis.

- **Dispatcher Strategies:**
  - **RandomDispatcher:** Assigns jobs to servers at random.
  - **RoundRobinDispatcher:** Cycles through the servers in order.
  - **ShortestQueueDispatcher:** Selects the server with the fewest jobs waiting.
  - **LeastWorkDispatcher:** Chooses the server with the least total remaining processing time.

## Extensions
Two major extensions were explored:
- **Priority Queue Extension:** The standard FIFO queue was modified to a priority queue that prioritizes jobs with longer processing times. This extension investigates how changing the job processing order affects efficiency and fairness.
- **Hybrid Dispatcher Extension:** Combining the best features of the LeastWork and ShortestQueue dispatchers, a Hybrid Dispatcher was implemented. By assigning weights to both the servers queue size and its remaining processing time, the Hybrid Dispatcher aims to further optimize load distribution and minimize average waiting time.

## Experiments
The project includes automated experiments to:
1. **Compare Dispatcher Performance:** 
   - Run simulations using 34 servers and 10,000,000 jobs.
   - Compare average waiting times for each dispatcher (Random, Round-Robin, Shortest Queue, and Least Work).
2. **Assess Server Count Impact:**
   - Evaluate the effect of varying the number of servers (from 30 to 40) on the average waiting time using the ShortestQueueDispatcher.
3. **Extension Evaluations:**
   - Compare the performance of the Priority Queue and Hybrid Dispatcher extensions against the baseline FIFO approach.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or later.
- An IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools.
- Git (to clone the repository).

### Compilation and Execution

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Muneeb-ii/Modeling_a_Server_Farm.git
   cd Modeling_a_Server_Farm
2. **Compile the Project:** 
   ```bash
   javac *.java
3. **Run the Simulation:**
   The simulation is set up to automatically run all experiments in a single execution. Simply run:
   ```bash
   cd src
   java ServerFarmSimulation
4. **Running Extensions:**
   - Priority Queue Extension: Navigate to the extension directory, compile the priority-based files, and run:
     ```bash
     java PriorityServerFarmSimulation
   - Hybrid Dispatcher Extension: Adjust weights within the HybridQueueDispatcher as needed and run:
     ```bash
     java ServerFarmSimulation 34 10000000 hybrid
   _Note_: For fair comparisons with the priority-based simulations, modify the number of jobs in the baseline FIFO simulation to 100,000 as required.
     
