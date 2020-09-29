package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class JobQueue {
    private int numWorkers;
    private int[] jobs;
    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;
 
    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }


    
    
    
    
    
    static class Thread {
        private int thread;
        private long duration;

        public Thread(int assignThread) {
           thread = assignThread;
           duration = 0;
        }
        
        public void setDuration(long duration2) {
        	this.duration = duration2 + duration;
        }
        
        public long getDuration()
        {
        	return duration;
        }
        
        public int getThread() {
        	
        	return thread;
        }

    }
    
    public void assignJobs(long[] jobs, int numWorkers) {
        // keep this for junit testing.
    	
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers]; // essentially a list of each free time for [thread]
        List<Thread> queueNextFreeTime = new ArrayList<Thread>();
        //so we have a list of threads and the amount of time until the next job. So it needs to be a list of pairs(c)
        //then we have to sift these pairs by the time of next job.
        //we want a binary tree of objects, set up to be a binary heap of the variable "next FreeTime"
        //store those pairs in an array - adjust the sift down to handle moving pair position.
        for (int j = 0; j < numWorkers; ++j) { 
        	queueNextFreeTime.add(new Thread(j));// create the array of threads , already in a heap
        }
        
        
        
        for (int i = 0; i < jobs.length; i++) {// for each job let's pull the next avialable thread
            int duration = (int) jobs[i];
            
            //get the top result and save the worker and start time
            assignedWorker[i] = queueNextFreeTime.get(0).getThread();
            startTime[i] = queueNextFreeTime.get(0).getDuration();
            //assign that worker a new duration
            queueNextFreeTime.get(0).setDuration(duration);
            //now that we have a new duration in position 0, we need to sift down 
            queueNextFreeTime = siftDown(0, queueNextFreeTime);
            

        }
        
    }
    
    

    private List<Thread> siftDown(int currentNode, List<Thread> data){//TODO: adjust to sort pairs 
    	//We'll take in a number of pairs, and sift the one with the smallest duration to the top.

    	int left = 2*currentNode+1;
    	int right = 2*currentNode+ 2;
    	int smallest = currentNode;
    	int originalNode = currentNode;
    	

		if( (left) <= data.size()-1 &&( data.get(left).getDuration() < data.get(currentNode).getDuration()|| (data.get(left).getDuration() == data.get(currentNode).getDuration() && data.get(left).getThread() < data.get(currentNode).getThread()) )) {//checking the case where we only have the left node
			
			
			smallest = left;
			
			
		}
		else 
		{
			smallest = currentNode;
		}
		if( right <= data.size()-1 &&(data.get(right).getDuration() < data.get(smallest).getDuration()|| (data.get(right).getDuration() == data.get(smallest).getDuration() && data.get(right).getThread() < data.get(smallest).getThread()) ) )
		{
			//if the right node is in bounds, and the right node duration is smaller (right node will be same)
				
				smallest = right;
			
			
		}
		if( smallest != currentNode) {


    		Thread temp = data.get(currentNode);
    		data.set(currentNode, data.get(smallest));
    		data.set(smallest, temp);


    		siftDown(smallest,data);
    		
    	
		
		}
		return data;
		
    }//here will be the end of the copy paste
    
    
    
    public void assignJobsCheck(long[] jobs, int numWorkers){
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            long duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }
    
    public long[] getstartTime() {
    	return startTime;
    	
    }
    
    private void assignJobsToSubmit() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    
    public int[] getAssignedWorker() {
    	return assignedWorker;
    	
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

}
