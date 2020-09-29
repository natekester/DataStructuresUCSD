package week4;



import java.util.*;

import week4.is_bst.IsBST;

import java.io.*;

public class is_bst_treeOrder{
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
		boolean isBinTree = true;
		boolean noLines = false; 
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			if (n == 0) {
				noLines = true;
			}
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			
			}
		}
		
		public boolean inOrder() {
			
			if(noLines == true) {
				
			}
			else {
			 isBST(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
			return isBinTree;
		}
		
		public void isBST(int i, int min, int max) {
			// Base case. An empty tree is a BST.
			if (i == -1)
			{	return;}
			// Checking if a key is outside the permitted range.
         	try{
         	if(key[i] == key[left[i]])
            {
             isBinTree = false;
             return;
            }
            }catch(NullPointerException npe)
            {
            }

			if (key[i] < min)
			{	isBinTree = false;
				return ;}
			
		  if (key[i] > max) {
			  isBinTree = false;
				return ;
		  }
			// Sending in updates ranges to the right and left subtree
		  isBST(right[i], key[i], max); 
		  isBST(left[i], min, key[i]);	
		  return;
		}
	}
	


	
	

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_treeOrder().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		tree.inOrder();
        if (tree.isBinTree) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
	}
}
