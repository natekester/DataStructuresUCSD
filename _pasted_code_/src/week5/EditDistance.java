package week5;

import java.util.*;



class EditDistance {
	
  public static int EditDistance(String s, String t) {
	
	int sLen = s.length();
	int tLen = t.length();
	int[][] table = new int[sLen +1][tLen +1];

	int diag = 0;
	int left = 0;
	int up = 0;
	int min = 1000;
	
	for (int x = 0; x<=sLen; x++) {
		table[x][0] = x;
	}
	for (int y = 0; y<=tLen; y++) {
		table[0][y] = y;
	}
	
	//build LCS table
	for( int i = 1; i<=s.length(); i++ )
	{
		for( int j =1 ; j<=t.length(); j++)
		{
			min = 1000;

			diag = table[i-1][j-1];
	
			up = table[i][j-1];
		
			left = table[i-1][j];
			

			

			if( diag <= left && diag <= up)
			{
				min = diag;
			}
			else if ( up <= left && up <= diag) {
				min = up;
			}
			else {
				min = left;
			}
			

			

			char sChar = s.toCharArray()[i-1];

			char tChar = t.toCharArray()[j-1];

			
			
			if(sChar == tChar)
			{
				System.out.println("we just matched on i: "+ i + " and j: "+ j+ " with char: "+ sChar+ " and "+ tChar);
				table[i][j] = diag;
			}
			else if(i==0 && j==0) {  
				table[i][j] = min;
			}
			else {
				table[i][j] = min + 1;
				
			}
		}
	}
	
	
	

	//printing grid
	for(int i = 0; i<=s.length(); i++ )
	{
		for(int j = 0; j<=t.length();j++) {
			System.out.print(table[i][j] + " ");
		}
		System.out.println();
	}
		

	return table[s.length()][t.length()];
		
	}
	

  
  public static void main(String args[]) {
 //   Scanner scan = new Scanner(System.in);

	  String s = "editing";
		String t = "distance";
    System.out.println(EditDistance(s, t));
  }

}
