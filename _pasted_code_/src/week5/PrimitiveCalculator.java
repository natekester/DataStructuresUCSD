package week5;

import java.util.*;


//1000000 is the max number input
public class PrimitiveCalculator {
	static List<List<Integer>> outList = new ArrayList<>();
	
    private static List<Integer> optimal_sequence(int n) {
    	List<Integer> output = new ArrayList<>();
    	List<Integer> a0= new ArrayList<>();
    	List<Integer> a1= new ArrayList<>();
    	List<Integer> a2 = new ArrayList<>();
    	List<Integer> a3 = new ArrayList<>();


    	//we can add 1 or times by 2 or 3
    	outList.add(a0);
    	a1.add(1);
    	outList.add(a1);
    	a2.add(1);
    	a2.add(2);
    	outList.add(a2);
    	a3.add(1);
    	a3.add(3);
    	outList.add(a3);
    	int count;


    	for( int i =4; i<=n; i++) {
    		List<Integer> placeHold = new ArrayList<>();
    		count = Integer.MAX_VALUE;
    		int replace = 0;
    		// divide by 2, get that number, divide by 3 get that, and minus one
    		if((i%3)==0 && (outList.get(i/3).size() ) < count) {
    			count = outList.get(i/3).size();
    			replace = 1;
    		}
    		if((i%2)==0 && (outList.get(i/2).size() ) < count) {
    			count = outList.get(i/2).size();
    			replace = 2;
    			
    			
    		}
    		if((outList.get(i-1).size() ) < count) {
    			replace = 3;
    		}
    		
    		if( replace == 1) {

    	        for (Integer x : outList.get(i/3)) {
    	        	
    	            placeHold.add(x);
    	        }


    	        placeHold.add(i);
    			outList.add(placeHold);
    			
    		}
    		else if (replace == 2) {


    	        for (Integer x : outList.get(i/2)) {
    	        	
    	            placeHold.add(x);
    	        }

    	        placeHold.add(i);
    	        

    			outList.add(placeHold);
    			
    		}
    		else if ( replace == 3){

    	        for (Integer x : outList.get(i-1)) {
    	        	
    	            placeHold.add(x);
    	        }
    			placeHold.add(i);

    			outList.add(placeHold);
    		}
    		
    	}
    	int k=0;

    	
    	return outList.get(n);
    }

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        int n = 96234;

        //int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

