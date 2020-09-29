package week6;

import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] A) {

    	int sum=0;
    	int target;


    	
    	for(int i: A) {
    		sum = i + sum;
    	}
    	if( sum %3 != 0) {
    		return 0;
    	}
    	else {
    		target = sum/3;
    	}
    	
    	

    	int[] A1 = CreateTable(A,target);
    	
    	//now repeat.
    	
    	for(int item: A1){
    		System.out.print(item+ " ");
    	}
    	System.out.println();
    	
    	int[] A2 = CreateTable(A1,target);
    	sum = 0;
    	
    	
    	for(int item: A2) {
    		sum = sum+ item;
    	}
    	if(sum == target) {
    		return 1;
    	}
    	
        return 0;
    }
    
    
    public static int[] CreateTable(int[] A, int target) {

    	List<Integer> solu = new ArrayList<Integer>();
    	List<Integer> avaNum = new ArrayList<Integer>(A.length+1);

    	
    	int[][] table = new int[target+1][A.length+1];//add one for the value position
 
    	for(int i=0; i< A.length; i++) {
    		// put in all available numbers to this table

    		if( A[i] != 0) {
    		avaNum.add(A[i]);
    		table[0][i] = A[i];
    		}
    	
    	}
    	avaNum.add(0);

    	boolean solution = false;
    	boolean toBreak = false;
    	int value;
    	for(int i = 1; i<= target; i++) {
    		value =  Integer.MAX_VALUE;
    		int recordValue = Integer.MAX_VALUE;
    		boolean isNum = false;
    		
    		for(int j=0; j< A.length; j++) {
    			int num = table[0][j];
    			if(num == i) {
    				table[i][j] = num ;
    				recordValue = num;
    				isNum = true;
    				if(num== target) {
    					solu.add(num);
    					solution = true;
    					toBreak = true;
    					break;
    				}
    				break;
    				
    				
    			}
    			else if( i - num > 0) {
    				//get the available numbers at that table position and the value
    				//make sure the number we just minused is available and then see if we equal target. if we don't, add the largest number that fits
    				
    				if(table[i-num][A.length] + num == target && table[i-num][j] == 0) {
    					//if past setup plus our number equals target - get all the numbers used and add them to the solution
    					solution = true;
    					recordValue = i;
    					value = i;
    					
    					for(int l= 0; l< A.length; l++) {
        					
    						if(l == j) {
    							
    							avaNum.set(l, num);    		
    						
    						}else {
    							
    							avaNum.set(l,table[i-num][l]);   
    						
    					
    						}
    					}
    					table[i][A.length] = i;
    					
    					toBreak = true;
    					break;
    					
    				}
    				else if( table[i-num][A.length] + num == i && table[i-num][j] == 0) {
    					
    					recordValue = i;
    					value = i;
    					
    					for(int l= 0; l< A.length; l++) {
        					
    						if(l == j) {
    							
    							avaNum.set(l, num);    							
    						}else {
    							
    							avaNum.set(l,table[i-num][l]);   
    					
    						}
    					}
    					table[i][A.length] = i;
    				}
    				else {
    					//add the largest value that doesn't go over i?
    					if(table[i-num][j] == 0 && num+ table[i-num][A.length] <=i )
    					{
    						value = num+ table[i-num][A.length];
    					}
    		    		
        				if(value < recordValue && value !=0) {
        					
        					recordValue = value;
        					for(int l= 0; l< A.length; l++) {
        					
        						if(l == i) {
        							
        							avaNum.set(l, num);    							
        						}else {
        							
        							avaNum.set(l,table[i-num][l]);   
        					
        						}
        					}
        					
        				}
    					
    					
    					
    				}
    				
    				
    			}
    			else if( value == Integer.MAX_VALUE ){
    				for(int p = 0; p< A.length; p++) {
    				
    					table[i][p] = 0;
    					avaNum.set(p,0);

    				}
    				
    			
    				//record zeros?
    				
    			}
    			
    			
    		}
    		
    		
    		table[i][A.length]= recordValue;
    		if( isNum == false) {
	    		for(int n =0; n < A.length; n++) {
	    			//might have an issue here adding the availW to new position - initialized at the beginning
	    			table[i][n] = avaNum.get(n); 
	    			if(avaNum.get(n) != 0 && solution == true) {
	    				solu.add(avaNum.get(n));
	    			}
	    		}
    		}
    		
    		if(toBreak == true) {
    			System.out.println("we found the first solution!");
    			break;
    		}
    		

    		
    	}
    	
    	for(int i1 = 0; i1 <= A.length; i1++ )
    	{
    		for(int j = 0; j<= target; j++) {
    			if(table[j][i1]>9) {
    				System.out.print(table[j][i1] + " ");
    			}
    			else {
    				System.out.print(table[j][i1] + "  ");
    			}
    			
    		}
    		System.out.println();
    	}
    	
    	
    	System.out.println("our solution was ");
    	for(int item: solu) {
    		System.out.print(item+ " ");
    	}
    	
    	A = AppendList(A, solu);
    	
    	return A;
    }
    
    public static int[] AppendList(int[] A, List<Integer> solu) {
    	
    	int[] A2 = new int[A.length - solu.size()];
    	int i = 0;
    	

    	
    	for(int a: A) {
    		if(solu.contains(a)) {
    			System.out.println("removing: "+ a + " from the initial list" );
    			int toRemove = solu.indexOf(a);
    			solu.remove(toRemove);
    		}
    		else {
    			if( a !=0) {

    			A2[i] = a;
    			i++;
    			}
    		}
    	}
    	

    	return A2;
    	
    	
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner("5 2 3 4 1 5");
        //1 5 5 3 1 1
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        
        System.out.println(partition3(A));
    }
}

