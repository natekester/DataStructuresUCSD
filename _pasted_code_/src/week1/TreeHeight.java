package week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TreeHeight {
	

	static int computeHeight(int n, int[] parent) {
		//System.out.println("starting");
	    int root = 0;
		Node[] nodes = new Node[n];
		int breath = 0; //breath of the tree
		List<Node> queue = new ArrayList<Node>();
		//List<Object> nodes = new ArrayList<Object>();
		//lets create the tree.
		//System.out.println("starting to make the tree  "+  parent[4]);
		for(int i = 0 ; i<n; i++)
	    {
			System.out.println("adding node");
	    	nodes[i] = new Node(parent[i]);
	    	System.out.println("added node" + nodes);

	    }
		
		//System.out.println("we have the following nodes"+ nodes);
		for(int i = 0 ; i<n; i++)
	    {
			if (parent[i]==-1) {
				root = i;
				
			}
			else {
			//System.out.println("we are adding child  "+ i + " to parent child "+ parent[i]);
	    	nodes[parent[i]].addChild(nodes[i]);
			}
	    }
		//now that the tree is created, let's create
		//a queue to go down.

		queue.add(nodes[root]);

		while(queue.isEmpty() != true)
		{

			//we start with root and get the children
			//every time we loop the breadth should get bigger
			int newLength = queue.size();
			for(int i=0; i<newLength;i++ ) {
				
				Node currentNode = queue.get(0);
				List<Node> children = currentNode.getChildren();
				
				for(int j=0; j<currentNode.getChildLength();j++) {

					queue.add(currentNode.getChildren().get(j));
					
				}
				
				queue.remove(0);
			}
			breath++;
			
		}
		
		return breath;
		
	} // end of the function we're copying
	
	
	
	
	static int computeHeightfailed(int n, int[] parent) {
	    //new strat - find all nodes that don't have anything pointing to them
		//then iterate through those to the root and find max.
		

		//we have a list 0 through n - if those numbers do not show up in the list, it is a leaf
		
		//find all leaves - but do not follow the way of them
		
		List<Integer> leafNodes = new ArrayList<Integer>();
		//leafNodes = new int[];
		int[] duplicateParent = Arrays.copyOf(parent, parent.length);
		Arrays.parallelSort(duplicateParent);
		for(int i = 0; i< n; i++)
		 {
			boolean isLeaf = true;
			int highEnd = parent.length - 1;
			int lowEnd = 0;
			while (i >= duplicateParent[lowEnd] && i <= duplicateParent[highEnd] && lowEnd <= highEnd) {
			    int probe = lowEnd + (highEnd - lowEnd) * (i- duplicateParent[lowEnd]) / (duplicateParent[highEnd] - duplicateParent[lowEnd]);
			
			    if(probe<0 ||probe> parent.length-1)
			    {
			    	isLeaf = false;
			    	break;
			    }
		        if (highEnd == lowEnd) {
		            if (duplicateParent[lowEnd] == i) {
		            	isLeaf = false;
					       break;
		            } else {
		                break;
		            }
		        }
		 
		        if (duplicateParent[probe] == i) {
		        	isLeaf = false;
		        	break;
		        }
		 
		        if (duplicateParent[probe] < i) {
		            lowEnd = probe + 1;
		        } else {
		            highEnd = probe - 1;
		        }
		        System.out.println(probe);
			
			}
			
		 

			
			
			if(isLeaf == true) {
				if(leafNodes.isEmpty()) {
				System.out.println("leafNodes is currently empty");
					leafNodes.add(i);
				}
				else {
					boolean duplicate = false;
					for (int each:leafNodes)
					{
					 if (parent[each] == parent[i])
					 {
						 duplicate = true;
						 System.out.println("we found a duplicate"+ i);
						 break;
					 }
					}
					if(duplicate==false) {
						leafNodes.add(i);
						}
					}
				}
			}
			
		int maxHeight = 0;

		for (int node:leafNodes) {
			int height = 0;
			//System.out.println("we are now on parent node: "+ node + "which has parent node: "+ parent[node]);
			boolean root = false;
			
			while ( root != true) {
				
				
				if ( parent[node] == -1)
				{
					root = true;
				}
				else
				{
					node = parent[node];
					height++;
				}
				
			}
			maxHeight = Math.max(maxHeight, height);
		}
		//System.out.println("leaf height is " + maxHeight);
		return maxHeight+1;
		
		
	} // end of the function we're copying
	
	
	
	

	

	
	
	 static int checkHeight(int n, int[] parent) {
		    System.out.println("starting check");

			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			System.out.println("maxHeight is  " + maxHeight);
			return maxHeight;
		}
	
	static int countDistinct(int arr[], int n)  
    { 
        // First sort the array so that all 
        // occurrences become consecutive 
        Arrays.sort(arr); 
  
        // Traverse the sorted array 
        int res = 0; 
        for (int i = 0; i < n; i++)  
        { 
  
            // Move the index ahead while 
            // there are duplicates 
            while (i < n - 1 &&  
                    arr[i] == arr[i + 1]) 
            { 
                i++; 
            } 
            res++; 
        } 
        return res; 
    } 
}

//here I store what had the failures.
/*import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

 	int countDistinct(int arr[], int n)  
	    { 
	        // First sort the array so that all 
	        // occurrences become consecutive 
	        Arrays.sort(arr); 
	  
	        // Traverse the sorted array 
	        int res = 0; 
	        for (int i = 0; i < n; i++)  
	        { 
	  
	            // Move the index ahead while 
	            // there are duplicates 
	            while (i < n - 1 &&  
	                    arr[i] == arr[i + 1]) 
	            { 
	                i++; 
	            } 
	            res++; 
	        } 
	        return res; 
	    } 
		
		int computeHeight() {
       
			
			// Replace this code with a faster implementation
			int height;
			//int[] nodeIn = new int[n];
			Arrays.parallelSort(parent);
			height = countDistinct(parent, n);
			
			
			return height;
			
			
//			int maxHeight = 0;
//			for (int vertex = 0; vertex < n; vertex++) {
//				int height = 0;
//				for (int i = vertex; i != -1; i = parent[i])
//					height++;
//				maxHeight = Math.max(maxHeight, height);
//			}
//			return maxHeight;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight(tree.n, tree.parent));
	}
}*/