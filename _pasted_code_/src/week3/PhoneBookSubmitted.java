package week3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {


    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private List<Contact> contacts = new ArrayList<>();
    private List<ChainLink> hash = Arrays.asList(new ChainLink[10000000]);

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


 
  private void processQuery(Query query) {
    	
    	//let's make this hash.
    	//need an array of chains that hold Contacts
    	//need to make chain class
    	// make the array
    	//size 10000 to start - if we fail let's make it dynamic
    	int hashCode = hashFunction(query.number);
    	
        if (query.type.equals("add")) {
        	//System.out.println("add: "+ hashCode);
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            
           if(hash.get(hashCode) != null) {
            	ChainLink link = hash.get(hashCode);
            	boolean hasChild = false;
            	
            	do
            	{
            		Contact contact = link.getHeldContact();
            		if(contact.number == query.number)
            		{
            			contact.name = query.name;
                        wasFound = true;
                        break;
            		}
            		else {
            			try {

            				if(link.getChild() == null)
            				{
            					
            					break;
            				}
            				else {
            					hasChild = true;
            				}
            			}
            			catch(NullPointerException npe) {
            				System.out.println(" has no child");
            				break;
            			}
            			
            			if(hasChild = true)
            			{
            				link = link.getChild();

            			}
            			
            		}
            	}while(hasChild== true);
            
            }


            // otherwise, just add it -- we should create hash to add it.
            if (!wasFound) {
            	//this is weird and I don't like it. 
            	//check if we have a link on the hash position - if no link, new link
            	//if link, add to current link
            	if(hash.get(hashCode) != null) {
            		 ChainLink currentLink = hash.get(hashCode);//might throw error
            		 //find end of link
            		boolean hasChild;
                 	do
                 	{
                 		
                 			if(currentLink.getChild() != null)
                 			{
                 				currentLink = currentLink.getChild();
                 				hasChild = true;
                 			}
                 			else {
                 				
                 				currentLink.setChild( new ChainLink(new Contact(query.name, query.number) ));
                 				hasChild = false;
                 			}
                 			
                 		
                 	}while(hasChild);
            		 
                 }
            	else
                 {
                	 //if we can't "get" the hashcode position, and it throws error
                	 hash.set(hashCode, new ChainLink(new Contact(query.name, query.number) ));
                 	
                 }
            	
            	
         
            }
        } else if (query.type.equals("del")) {
        	//System.out.println("del: " + hashCode);
        	//find the number from the query and remove it. i.e. set hash position to null
        	//check if the position is null: do you only have one contact?
        	//if one chainlink: remove position from hash
        	//if more than one chainlink replace the pointers
        	
        	if( hash.get(hashCode) != null) {//not empty find chain
        		 boolean end = false;
        		 ChainLink current = hash.get(hashCode);
        		 ChainLink parent = current;
        		 boolean hasChild;
        		 int chainIndex = 0;
        		do {
        			try {

        				if(current.getChild() == null)
        				{
        					hasChild = false;
        				}
        				else {
        					hasChild = true;
        				}
        			}
        			catch(NullPointerException npe) {
        				System.out.println(" has no child");
        				hasChild = false;
        				
        			}
        			
        			if (hasChild == false && chainIndex ==0 && current.getHeldContact().number == query.number )
        			{
        				//no children - is the node - remove

        				hash.set(hashCode, null);//only one link, remove it.
        				break;
        			}
        			else if( hasChild == true && current.getHeldContact().number == query.number && chainIndex ==0) {
        				//has children - is node - point to child
        				hash.set(hashCode, current.getChild());
        				break;
        			}
        			else if( chainIndex != 0 && current.getHeldContact().number == query.number ) {
        				//not the root - just remove the pointer of the parent to the child
        				

        				
        				parent.setChild(current.getChild());
        				break;
        				
        			}
        			
        			
        			//index goes up, record the parent to enable removal of child
        			try {
        			parent = current;
        			if( current.getChild() != null ) {
        			current = current.getChild();
        			}
        			else{
        				end = true;// should be null if empty
        			}
        			chainIndex++;}
        			catch(NullPointerException npe) {
        				end = true; //null pointer means break the while loop.
    				
    				}
        			
        		}while(end);
        	}
        	
        	}

            else { 
            	//System.out.println("find: "+ hashCode);// this is the find part
            String response = "not found";
            boolean found = false;
            ChainLink current = hash.get(hashCode);
            while(found != true) {
            	if(current == null)
            	{
            		found = true;
            	
            		break;
            	}
            	
            	if(current.getHeldContact().number == query.number) {
            		found = true;
            	
            		response = current.getHeldContact().name;
            		break;
            	}
            	else {
            		current = current.getChild();
            	}
            	
            }
            
            writeResponse(response);
            
        }
    }

 
     /**
     * 
     * @param phoneNum - the phone number we're entering
     * @return the hash code position for hash size 1000
     */
    static int hashFunction( int phoneNum ){
    	int hashSize = 10000000; //so this will be our m i.e. x mod m - let's pick 10000
    	//we might need to make this and the prime dynamic later.
    	int hashCode;
	
    	int prime = 10000019; // we have 10^5 posibilities, we need a prime bigger - hence 10...-19
    	int a = 31;// a and b are between 1 and P-1 -- doesn't seem to matter much
    	int b= 2;
    	
    	hashCode = (a*phoneNum+b)%prime%hashSize; // linear hash key equation.
    	
    	return hashCode;
    }
 
    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

     
    static class ChainLink{
    	//holds a child and a contact
    	private ChainLink child;
    	private Contact heldContact; 
    	
    	public void setChild(ChainLink kid) {
    		child = kid;
    	}
    	
    	public ChainLink getChild() {
    		if (child == null) {
    			return null;
    		}
    		else {
    		return child;
    		}
    	}
    	
    	public ChainLink( Contact contact) {
    		setHeldContact(contact);
    	}

		public Contact getHeldContact() {
			return heldContact;
		}

		public void setHeldContact(Contact heldContact) {
			this.heldContact = heldContact;
		}
    	
    }
 
    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}



