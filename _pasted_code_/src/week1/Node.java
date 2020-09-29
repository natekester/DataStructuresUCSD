package week1;
import java.util.ArrayList;
import java.util.List;

public class Node
{
    private List<Node> children = null;
    private int pointer;
    

    public Node(int pointer)
    {
        this.children = new ArrayList<>();
        this.pointer  = pointer;
        
    }

    public void addChild(Node child)
    {
        children.add(child);
    }
    
    public int getPointer()
    {
    	return pointer;
    }
    
    
    public List<Node> getChildren()
    {
    	return children;
    }
    
    public int getChildLength()
    {
    	return children.size();
    }
    
    

}