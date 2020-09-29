package week2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import week2.BuildHeap.Swap;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps = new ArrayList<Swap>();
    private int swapCount = 0;
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }
    
    public int getSwapCount() {
    	return swapCount;
    }
    
    public int[] getData() {
    	return data;
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    public void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }
    public void writeResponsej() {
        out.println(swaps.size());

    }
    
    
    public void generateSwaps(int[] data) {
    	long startTime = System.nanoTime();

    	int n = data.length;
    	
    	//System.out.println(n);
    	
    	for( int i = (n/2 + n%2 )-1; i >=0; i-- )//start at the bottom level
    	{
    		//starting at n/2-1 lets sift down each node i
    		//System.out.println("we are sifting node: "+i);
    		
    		siftDown(i,data); //then sift down each node
    		
    	}
    	long endTime   = System.nanoTime(); // due to the nature of the assignment, I wanted to track the time to sort
    	long totalTime = (endTime - startTime)/1000000;
    	System.out.println("our total runtime was:" + totalTime);
		for (int i=0; i < data.length; i++)
		{
			System.out.println("data value: "+ data[i] + " at position " +i);
			
		}

      }
    
    

	
    private void siftDown(int currentNode,int[] data){
    	

    	int left = 2*currentNode+1;
    	int right = 2*currentNode+ 2;
    	int smallest;
    	
    	System.out.println("length: " + data.length);

    	
		if( (left) <= data.length-1 && data[left] < data[currentNode]) {//checking the case where we only have the left node && it's less then current node
			smallest = left;
			
			
		}
		else
		{
			smallest = currentNode;
		}
		if( right <= data.length-1 && data[right] < data[smallest] )
		{
			smallest = right;
			
		}
		if( smallest != currentNode) { // if we have a child Node smaller than the current Node - swap and recursively call sift down on the node until it's as low as possible
			swaps.add(new Swap(smallest,currentNode));
    		
    		int temp = data[currentNode];
    		data[currentNode] = data[smallest];
    		data[smallest] = temp;
    		swapCount = swapCount+1;
    		siftDown(smallest,data);
    		
		
		}
		
    }//end code here for sift
    
    
    
    public void generateSwapsCheck(int[] data){
      swaps = new ArrayList<Swap>();
      // The following naive implementation just sorts 
      // the given sequence using selection sort algorithm
      // and saves the resulting sequence of swaps.
      // This turns the given array into a heap, 
      // but in the worst case gives a quadratic number of swaps.
      //
      // TODO: replace by a more efficient implementation
      int k=0;
      for (int i = 0; i < data.length; ++i) {
        for (int j = i + 1; j < data.length; ++j) {
          if (data[i] > data[j]) {
        	  k= k+1;
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
          }
        }
      }
      //System.out.println("our check swapped: " + k + " times.");
      
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
      //  generateSwaps();
        writeResponse();
        out.close();
    }
    

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
            
        }
        
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
    static class Scan {
        private String input;
        

        static public long[] scanj( String input) {
        	int[] array = Stream.of(input.split(" "))
                    .mapToInt(token -> Integer.parseInt(token))
                    .toArray();
        	long[] longArray = Arrays.stream(array).mapToLong(i -> i).toArray();
            return longArray;
        }


    }
}
