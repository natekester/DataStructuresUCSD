package week5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeDP {
	static List<Integer> minNumCoinsArr = new ArrayList<>();
	
    private static int getChange(int m) {
        //write your code here
    	//our coins are 1,3 and 4
    	minNumCoins(m);
    	return minNumCoinsArr.get(m);
    	
    	
    }
    
    private static void minNumCoins(int money) {
    	int[] coins = {1,3,4};
    	minNumCoinsArr.add(0);
    	minNumCoinsArr.add(1);
    	minNumCoinsArr.add(2);
    	minNumCoinsArr.add(1);
    	minNumCoinsArr.add(1);
    	//ADDING THE FIRST 4 TO REFERENCE
    	int count;
    	int subCount;
    	for( int i =5 ; i<= money; i++) {
    		count = Integer.MAX_VALUE;
    		for(int coin: coins) {
    			
    			int num =minNumCoinsArr.get(i - coin);
    			subCount = num + 1;
    			if(subCount < count ) {
    				count = subCount;
    				
    			}
    			
    		
    		
    		}
    		minNumCoinsArr.add(count);
    	}
    	
    	
    }

    public static void main(String[] args) {
       // Scanner scanner = new Scanner(System.in);
        int m = 99;
        //= scanner.nextInt();
        System.out.println(getChange(m));

    }
}

