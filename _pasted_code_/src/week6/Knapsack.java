package week6;


import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
    	int[][] table = new int[W+1][w.length+1];
    	int value;
    	
    	List<Integer> availW = new ArrayList<Integer>(w.length);
    	int i = 0;
    	for( int weight: w) {
   
    		table[0][i] = weight;
    		i++;
    		availW.add(weight);
    	}
    	
    	
    	for( int j = 1; j <= W; j++) {// move through each Weight from 1 up to W
    		value = 0;
    		int recordValue = 0;
    		boolean isWeight = false;
    		
    		
    		for(int k = 0; k< w.length; k++) {//chech each available bar and add them based on old W/j
    			int weight = table[0][k];
    			if( j == weight) {
    				isWeight = true;
    				recordValue = weight;
    				table[j][k] = weight;
    				table[j][w.length] = 1;// value pos = 1
    				for(int p = 0; p< w.length; p++) {
    					if(p != k) {
    					table[j][p] = 0;
    					}
    				}
    				break;
    				

    			}
    			else if(j - weight> 0) {
    				//call the table and find out which weights are available and the current value
    				 				
    				//add all available weights to list
    				
    				
    				
    				if(table[j-weight][k] == 0 && j- weight + table[j-weight][w.length] >= 0) { // if the weight is available and fits
    					///get value of that weight
    					value = table[j-weight][w.length] + weight;
    				}
    				else {
    					//we set the new value to that old value and don't add anything
    					value =  table[j-weight][w.length];
    				}
    				
    		
    				if(value > recordValue) {
    					
    					recordValue = value;
    					for(int l= 0; l< w.length; l++) {
    					
    						if(l == k) {
    							
    							availW.set(l, weight);    							
    						}else {
    							
    							availW.set(l,table[j-weight][l]);   
    					
    						}
    					}
    					
    				}

       			}
    			else if( value == 0){
    				for(int p = 0; p< w.length; p++) {
    				
    					table[j][p] = 0;
    					availW.set(p,0);

    				}
    				
    			}

    		}
    		
    		table[j][w.length]= recordValue;
    		if( isWeight == false) {
	    		for(int n =0; n < w.length; n++) {
	    			//might have an issue here adding the availW to new position - initialized at the beginning
	    			table[j][n] = availW.get(n); 
	    		}
    		}
    		
    		
    		//record new value and weights used -- continue
    		
    		
    		
    	}
    	System.out.println();
    	for(int j = 0; j<= W; j++) {
			if(j>9) {
				System.out.print(j + " ");
			}
			else {
				System.out.print(j + "  ");
			}
    	}
    	System.out.println();
    	for(int i1 = 0; i1 <= w.length; i1++ )
    	{
    		for(int j = 0; j<= W; j++) {
    			if(table[j][i1]>9) {
    				System.out.print(table[j][i1] + " ");
    			}
    			else {
    				System.out.print(table[j][i1] + "  ");
    			}
    			
    		}
    		System.out.println();
    	}
    		
    	
    	return table[W][w.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner("10 3 1 4 8");
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

