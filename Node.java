import java.util.*;
import java.io.*;
public class Node{
    private Place origin;
    private Place target;
    private double x;
    private double y;
    private double nodeValue=Double.MAX_VALUE;
    private ArrayList<Node>path;
    private boolean special=false;
    public double nodeValue(){
        return this.nodeValue;
    }
    
    public Place target(){
        return this.target;
    }
    
    public Place origin(){
        return this.origin;
    }
    public void refresh(){
        this.nodeValue=Double.MAX_VALUE;
        this.special=false;
    }
    public Node(Place origin, Place target,double x, double y){
        this.x=x;
        this.y=y;
        this.origin=origin;
        this.target=target;
    }
    public void display(){
        StdDraw.setPenColor(0,0,0);
        StdDraw.textLeft(x+20+20*(this.nodeValue%5),y+20+20*(this.nodeValue%5),String.valueOf(this.nodeValue));
        //System.out.println(String.valueOf(this.nodeValue));
        if(special){
            StdDraw.setPenColor(50,150,0);
        StdDraw.filledCircle(x, y,10);
        StdDraw.setPenColor(0,255,255);
            StdDraw.setPenColor(100,250,100);
        StdDraw.filledCircle(x, y,15);
        StdDraw.setPenColor(0,255,255);
        }
    }
    public ArrayList<Node> getPath(){
        special=true;
        return this.path;
    }
    public Place getOther(Place p){
        if(p==origin){
            return this.target;
        }
        else if(p==target){
            return this.origin;
        }
        else{
            System.out.println("what madness is this");
            return null;
            
        }
    }
    public void pushNodeValue(double incomingValue,Place source,ArrayList<Node>inpath,double speed){
        if (incomingValue<nodeValue){
            this.nodeValue=incomingValue;
            
            
            ArrayList<Node>myPath=new ArrayList<Node>();
            for(Node n: inpath){
                myPath.add(n);
            }
            myPath.add(this);
            this.path=myPath;
        if (origin==source){
            target.inputNodeValue(incomingValue,this,myPath,speed);
        }
        else{
            origin.inputNodeValue(incomingValue,this,myPath,speed);
        }
        }
    }
}