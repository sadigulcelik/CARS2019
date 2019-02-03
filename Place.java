import java.util.*;
import java.io.*;
public abstract class Place{
    private double length;
    private Place parent;
    protected ArrayList<Node>links=new ArrayList<Node>(); 
    private ArrayList<Place>children=new ArrayList<Place>();//useful for display
    private ArrayList<Occupation>occupations=new ArrayList<Occupation>();
    protected ArrayList<Car>cars=new ArrayList<Car>();
    
    
    
    /**
     * Creates a place from a parent place and a length.
     * @param  parent the parent node from which the place "begins"
     * @param  length the distance taken to traverse the newly created place
     */
    public ArrayList<Node> getNodes(){
        return this.links;
    }
    
    public ArrayList<Node> getAllNodes(){
        ArrayList<Node> out=new ArrayList<Node>();
        for (Node n: this.links){
            if (n.origin()==this){
                out.add(n);
            }
        }
        for(Place p: children){
            ArrayList<Node> toAdd=p.getAllNodes();
            for (Node n: toAdd){
                out.add(n);
            }
        }
        return out;
    }
    public Place(Place parent,double length) throws AdoptionException{
        if(parent.makeParent(this)){
            Node n=new Node(parent,this, parent.x(), parent.y());
            parent.addLink(n);
            links.add(n);
            this.length=length;
        }
        else{
            throw (new AdoptionException("The parent failed to adopt"));
        }
    }
    
    
    
    /**
     * To be used only once in the creation of the initial Times Square
     * @param  length the distance taken to traverse the Times Square
     */
    protected Place(double length){
        //System.out.println(this.length);
        this.length=length;       
    }
    
    
    /**
     * Creates a place that links a parent and target place. Nodes are created to link the new place to both parent and target. The place created will most likely be a road though an unconventional use to create an intersection linking two roads that coincide is possible. The target place will not however, be listed as a child since it is sufficient for every place to have only one parent.
     * @param  parent the parent node from which the place "begins"
     * @param  target the other place to which the new place will be connected to
     * @param  length the distance taken to traverse the newly created place
     */
    public Place(Place parent,Place target,double length)throws AdoptionException{
        Node n2=new Node(this,target,target.x(),target.y());
        if(parent.makeParent(this)&&target.addLink(n2)){
            Node n=new Node(parent,this, parent.x(),parent.y());
            parent.addLink(n);
            links.add(n);
            this.length=length;
            links.add(n2);
        }
        else{
            if (!parent.makeParent(this)){
                parent.disown(this);
                throw (new AdoptionException("Parent failed to adopt."));
                
            }
            else{
                throw(new AdoptionException("Target failed to take on a new node."));
            }
            
        }      
    }
    
    
    public void disown(Place p){
        this.children.remove(p);
    }
    /**
     * Attempts to make this place a parent to another place
     * @param  p the "child" to be adopted
     * @return  whether or not the child can be adopted or not 
     */
    public boolean makeParent(Place p){
        if (this instanceof Intersection){
            this.children.add(p);
        return true;
        }
        else if (this instanceof Road){
            if(this.links.size()==1){
                this.children.add(p);
                return true;
            }
            else{
                return false;
            }
        }
        else{return false;}
    }
    
    
    /**
     * Adds a node to this place thereby linking it to the other place the node
     * @param  n the node to be added
     */
    public boolean addLink(Node n){
        if (this instanceof Intersection){
            this.links.add(n);
            return true;
        }
        else if (this instanceof Road){
            if(this.links.size()==1){
                this.links.add(n);
            }
            else{
                return false;
            }
        }
        return false;
            
        
    }
    
    
    /**
     * Returns the length of the node
     * @return  the length of the node
     */
    public double length(){
        //System.out.println(this.length);
        return this.length;
    }
    
    public void removeCar(Car c){
        this.cars.remove(c);
    }
    
    
    abstract boolean addCar(Car c);
    
    /**
     * The display method for a place
     */
    abstract void display();
    
    
    /**
     * Calls the display method for this place and the the {@link #displayAll()} method for its children
     */
    public void displayAll(){
        this.display();
        for (Place p :children){
            p.displayAll();
        }
    }
    
    
    /**
     * Returns the X coordinate of the open part of a place. For an intersection this is always the intersection's location, and for roads this is the open end.
     * @return  the coordinate
     */
    abstract double x();
    
    /**
     * Returns the Y coordinate of the open part of a place. For an intersection this is always the intersection's location, and for roads this is the open end.
     * @return  the coordinate
     */
    abstract double y();
    
    public void inputNodeValue(double nodeValue,ArrayList<Node>path){
        for (Node n: this.links){
            n.pushNodeValue((nodeValue+this.length),this,path);
        }
    }
    public void inputNodeValue(double nodeValue, Node sourceNode, ArrayList<Node>path){
        for (Node n: this.links){
            if (n!=sourceNode){
                n.pushNodeValue((nodeValue+this.length),this,path);
            }
        }
    }
}