package week3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }
    
    
    private static List<Integer> getOccurrences(Data input) {
    	//TODO: make this rabin karp algorithm.
    	//need a poly hash and to create a list of predone hashes.
    	//compare that list of hashes with our pattern hash
    	//if it is equal, compare the strings. if the strings are equal, add them to occrences
    	
        String pat = input.pattern;
        String txt = input.text;
        long q = 1000003;
        List<Integer> occurrences = new ArrayList<Integer>();
        
        	
       	 // d is the number of characters in the input alphabet 
           final long d = 128; 

       
           int patLen = pat.length(); 
           int txtLen = txt.length(); 
           int i, j; 
           long hashPat = 0; // hash value for pattern 
           long hashText = 0; // hash value for txt 
           long h = 1; //const for adjusting position
         
           // The value of h would be "pow(d, M-1)%q" 
           for (i = 0; i < patLen-1; i++) 
               h = (h*d)%q; 
         
           // Calculate hash

           for (i = 0; i < patLen; i++) 
           { 
               hashPat = (d*hashPat + pat.charAt(i))%q; 
               hashText = (d*hashText + txt.charAt(i))%q; 
           } 
           System.out.println("our first pat hash is: "+hashPat + " for "+pat);
           System.out.println("our first txt hash is:"+ hashText);
           
           for (i = 0; i <= txtLen - patLen; i++) 
           { 
         
              
               // If the hash values match check
               if ( hashPat == hashText ) 
               { 
                   /* Check for characters one by one */
                   for (j = 0; j < patLen; j++) 
                   { 
                       if (txt.charAt(i+j) != pat.charAt(j)) 
                           break; 
                   } 
         
                   // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1] 
                   if (j == patLen) 
                	   occurrences.add(i);
                       
               } 
         
               // Calculate hash value for next window of text: Remove 
               // leading digit, add trailing digit 
               if ( i < txtLen-patLen ) 
               { 
                   hashText = (d*(hashText - txt.charAt(i)*h) + txt.charAt(i+patLen))%q; 
                   
                   // We might get negative value of t, converting it 
                   // to positive 
                   if (hashText < 0) {
                   hashText = (hashText + q); 
                   }
                   System.out.println("our hash is: "+hashText+" for substring: "+ txt.substring(i, (i+patLen)));
               } 
           } 
       
       
                
	
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {//changed for testing -use original class
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
        	try {
        		
            reader = new BufferedReader(new FileReader("C:\\Users\\NateyBoi\\Desktop\\Java Stuff\\Workspace for Datastructures\\_pasted_code_\\src\\assignment3\\HashSubstring.txt"));
            tokenizer = null;}
            catch (IOException ioe) 
            {
     	   ioe.printStackTrace();
            } 
        	
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

