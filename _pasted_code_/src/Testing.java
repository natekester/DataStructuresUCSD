import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Testing {
//    public static void main(String[] args) throws IOException {
//        InputStreamReader input_stream = new InputStreamReader(System.in);
//        BufferedReader reader = new BufferedReader(input_stream);
//        String text = reader.readLine();
//
//        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
//        //okay, so lets make a stack of all unmatched positions that open left
//        //the top bracket needs to match the next left facing one
//        
//        for (int position = 0; position < text.length(); ++position) {
//            char next = text.charAt(position);
//
//            if (next == '(' || next == '[' || next == '{') {
//                // Process opening bracket, write your code 
//            	//if it's one of these add that shit to our stack
//            	Bracket brack = new Bracket(next,position);
//            	
//            	opening_brackets_stack.push(brack);
//            }
//
//            if (next == ')' || next == ']' || next == '}') {
//                // Process closing bracket, write your code here
//            	if (opening_brackets_stack.peek().Match(next)) {
//            		opening_brackets_stack.pop();
//            	}
//            	else {
//            		System.out.println("position");
//            	}
//            }
//        }
//
//        // Printing answer, write your code here
//        //if the stack is not empty it failed - print 
//     // if it has no errors type out "Success"
//        if(opening_brackets_stack.empty()){
//            System.out.println("Success");
//        }
//    }
    
    
    static public void Test(String text) {
    	

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        //okay, so lets make a stack of all unmatched positions that open left
        //the top bracket needs to match the next left facing one
       
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code 
            	//if it's one of these add that shit to our stack
            	Bracket brack = new Bracket(next,position);
            	
            	opening_brackets_stack.push(brack);
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
            	if (opening_brackets_stack.empty()){
            		System.out.println(position+1);
            		return;
            	}
            	else if (opening_brackets_stack.peek().Match(next)) {
            		opening_brackets_stack.pop();
            	}            	
            	else{
            		System.out.println(position+1);
            		return;
            	}
            }
        }

        // Printing answer, write your code here
        //if the stack is not empty it failed - print 
     // if it has no errors type out "Success"
        if(opening_brackets_stack.empty()){
            System.out.println("Success");
        }
        else {
        	System.out.println(opening_brackets_stack.peek().position+1);
        }
    }
    }