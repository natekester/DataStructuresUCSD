package week4;

import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			try {
				in = new BufferedReader(new FileReader("C:\\Users\\NateyBoi\\Desktop\\Java Stuff\\Workspace for Datastructures\\_pasted_code_\\src\\assignment4\\treeOrderTest.txt"));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		ArrayList<Integer> inOrderResult = new ArrayList<Integer>();
		ArrayList<Integer> preOrderResult = new ArrayList<Integer>();
		ArrayList<Integer> postOrderResult = new ArrayList<Integer>();
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
				System.out.println(key[i]+" "+left[i]+" " + right[i]);
			}
		}

		List<Integer> inOrder() {
			
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
			
			int i = 0; // start with root
			
			//go left until null - print,
			//print parent, go right and repeat
			InOrderTraversal(i);
			
			return inOrderResult;
		}
		
		void InOrderTraversal(int i ){

			if (i == -1) {
					return;}
			InOrderTraversal(left[i]);
			inOrderResult.add(key[i]);//do the thing with current key
			InOrderTraversal(right[i]);
			
		}
		

		List<Integer> preOrder() {
          // Finish the implementation
                        // You may need to add a new recursive method to do that
			int i=0;
	        preOrderTraversal(i);
			return preOrderResult;
		}
		
		void preOrderTraversal(int i ){
			if (i == -1) {
					return;}
			preOrderResult.add(key[i]);
			preOrderTraversal(left[i]);
		
			preOrderTraversal(right[i]);
			
		}
		

		List<Integer> postOrder() {
           // Finish the implementation
                        // You may need to add a new recursive method to do that
            int i=0;
            postOrderTraversal(i);
			return postOrderResult;
		}
		
		void postOrderTraversal(int i ){
			if (i == -1) {
					return;}
			postOrderTraversal(left[i]);
			postOrderTraversal(right[i]);
			postOrderResult.add(key[i]);//do the thing with current key
			
			
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
            
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
