package week3;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import week3.PhoneBook.ChainLink;




public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    // for hash function
    private List<ChainLink> hash;// = Arrays.asList(new ChainLink[bucketCount]);
    private static int bucketCount;
    private static int prime = 1000000007;
    private static int multiplier = 263;
    
    int hashCode;

    public static void main(String[] args) throws IOException {
    	new HashChains().processQueries();
    	new HashChains().processQueriesCheck();
    	
    }

    private static int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;// where is the exponential power?
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
       
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
    	
    	if (query.s == null)
    	{}
    	else {
    	hashCode = hashFunc(query.s);
    	}
        switch (query.type) {
            case "add":
            	
                if (hashContains(query) == false)
                {
                	// if hash does not contain query
                	boolean posEmpty = true;
                	
	                try {
	                	
	                	if(hash.get(hashCode) != null)
	                	{
	                		posEmpty = false;
	                		
	                	}

	                }
        			catch(NullPointerException npe) {
        			
        				//this is fine
        			}
                    catch(ArrayIndexOutOfBoundsException obe) {
                    	//also fine
        				
        				
        			}
	                
	                
                    if(posEmpty == false)
                    {
                    	
                    	ChainLink currentLink = hash.get(hashCode);
                    	
                    	ChainLink newRoot = new ChainLink(query.s);
                    	
                    	newRoot.setChild(currentLink);
                    	hash.set(hashCode, newRoot);
                    }
                    else {
                   
                    	hash.set(hashCode, new ChainLink(query.s));
                    }
	                
                }
                else {
                	//do nothing i.e. don't add it.
                }
                break;
            case "del":
            	
                if (hashContains(query))
                {

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
                					end = true;
                				}
                				else {
                					hasChild = true;
                					
                				}
                			}
                			catch(NullPointerException npe) {
                				
                				hasChild = false;
                				end = true;
                			}
                            catch(ArrayIndexOutOfBoundsException obe) {

                				hasChild = false;
                				end = true;
                				
                			}
                			boolean stringsEqual;

                			 if (hasChild == false && chainIndex == 0 && query.s.equals(current.getHeldString()))
                			{
                				//no children - is the node - remove
                				
                				hash.set(hashCode, null);//only one link, remove it.
                				break;
                			}
                			else if( hasChild == true &&  query.s.equals(current.getHeldString()) && chainIndex ==0)
                			{
                				//has children - is node - point to child
                				hash.set(hashCode, current.getChild());
                				break;
                			}
                			else if( chainIndex != 0 &&  query.s.equals(current.getHeldString()))
                			{
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
	                			chainIndex++;
	                			}
                			catch(NullPointerException npe) {
                				end = true; //null pointer means break the while loop.
            				
            				}
                            catch(ArrayIndexOutOfBoundsException obe) {
                            	end = true;
                			}
                			
                		}while(end != true);
                		}
                	
                	}
                break;
            case "find":
            	writeSearchResult(hashContains(query));
            	
                
                break;
            case "check":
            	//System.out.println(query.ind);
            	
            	try {
            		//System.out.println(hash.get(query.ind).getHeldString());
            		if (hash.get(query.ind) != null) {
            			ChainLink link = hash.get(query.ind);
            			while(link.getHeldString() != null)
            			{
            				out.print(link.getHeldString() + " ");
            				
            				
            				link = link.getChild();
            				
            			}
            			out.println();
            		}
            		else {
            			out.println();
            		}
            	}
            	catch(NullPointerException npe) {
            		out.println();
      			}
                catch(ArrayIndexOutOfBoundsException obe) {
                	out.println();
    			}

                //not sure if I need the below line of print:
                //out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }
    
    private void processQueryCheck(Query query) {
        switch (query.type) {
            case "add":
                if (!elems.contains(query.s))// need to make this a hash search.
                    elems.add(0, query.s);
                break;
            case "del":
                if (elems.contains(query.s))
                    elems.remove(query.s);
                break;
            case "find":
                writeSearchResult(elems.contains(query.s));
                break;
            case "check":
                for (String cur : elems)
                    if (hashFunc(cur) == query.ind)
                        out.print(cur + " ");
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        hash = Arrays.asList(new ChainLink[bucketCount]);
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }
    
    public void processQueriesCheck() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQueryCheck(readQuery());
        }
        out.close();
    }
    
    boolean hashContains(Query query) {
    	boolean contains = false;
    	int hashCode = hashFunc(query.s);
    	 
         try {
        	 if(hash == null)
        	 {
        		 return contains= false;
        	 }
	         if(hash.get(hashCode) != null) {
	        	
	          	ChainLink link = hash.get(hashCode);
	          	boolean hasChild = false;
	          	boolean matches = false;
	          	
	          	 
	          	do
	          	{
	          		
	          		
	          		String str = link.getHeldString();
	          		if (str.contentEquals(query.s))
	          		{
	          			matches = true;
	          		}
	          		else
	          		{
	          			matches = false;
	          		}
	          		
	          		if(matches == true)
	          		{
	          			
	                      return (contains = true);
	                      
	          		}
	          		else {
	          			try {
	
	          				if(link.getChild() == null)
	          				{
	          					 return (contains = false);
	          					
	          				}
	          				else {
	          					hasChild = true;
	          				}
	          			}
	          			catch(NullPointerException npe) {
	          				contains = false;
	          				break;
	          			}
	          			
	          			if(hasChild = true)
	          			{
	          				link = link.getChild();
	
	          			}
	          			
	          		}
	          	}while(hasChild== true);
	          
	          }
	         else
	         {
	        	contains = false;
	         }
	        }
     	catch(NullPointerException npe) {
     			contains = false;
				//System.out.println(" ");
			}
         catch(ArrayIndexOutOfBoundsException obe) {
        	 	contains = false;
				//System.out.println(" ");
			}

    	
    	
    	
    	return contains;
    }

    static class ChainLink{
    	//holds a child and a contact
    	private ChainLink child;
    	private String str; 
    	
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
    	
    	public ChainLink( String str) {
    		setHeldString(str);
    	}

		public String getHeldString() {
			return str;
		}

		public void setHeldString(String str) {
			this.str = str;
		}
    	
    }
    	

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {//changed for testing -use original class
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
        	try {
        		
            reader = new BufferedReader(new FileReader("C:\\Users\\NateyBoi\\Desktop\\Java Stuff\\Workspace for Datastructures\\_pasted_code_\\src\\assignment3\\HashChainsTest.txt"));
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
