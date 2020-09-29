package week2;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps = new ArrayList<Swap>();
    private int swapCount = 0;
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }
    
    
    public void generateSwaps( ){
       
        // The following naive implementation just sorts 
        // the given sequence using selection sort algorithm
        // and saves the resulting sequence of swaps.
        // This turns the given array into a heap, 
        // but in the worst case gives a quadratic number of swaps.
        //
        
        // each time we swap we need to do this: 
        /*swaps.add(new Swap(i, j));
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;*/
        
        //so we're sorting through "data"
        //Step one - do we start at the top or at n/2 -1 and work our way down the array
        //
    	int n = data.length;
    	
    	//System.out.println(n);
    	
    	for( int i = (n/2 )-1; i >=0; i-- )
    	{
    		//starting at n/2-1 lets sift down each node i
		System.out.println("we are sifting node: "+i);
    		siftDown(i);
    		
    	}
//       System.out.println(swapCount);
//       System.out.println("length of swaps " + swaps.size());
       
//       for(Swap swap:swaps) {
//    	   System.out.println(swap.index1+ " " + swap.index2);
    	   
//       }
      

      }
    
    


	private void siftDown(int currentNode) {
    	
    	boolean noNeed = false;
    	boolean contin = false;
    	int left = 2*currentNode+1;
    	int right = 2*currentNode+ 2;
    	int largest;
    	
    	
		if(data.length >= (left+1) && data[left] < data[currentNode]) {//checking the case where we only have the left node
			largest = left;
			
			
		}
		else
		{
			largest = currentNode;
		}
		if( right <= data.length && data[right] < data[largest] )
		{
			largest = right;
			
		}
		if( largest != currentNode) {
			swaps.add(new Swap(currentNode, largest));
    		
    		int temp = data[currentNode];
    		data[currentNode] = data[largest];
    		data[largest] = temp;
    		swapCount = swapCount+1;
    		siftDown(largest);
    		
		
		}
		
    }
    
    
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
     // System.out.println("our check swapped: " + k + " times.");
      
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    public class Swap {
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
}
