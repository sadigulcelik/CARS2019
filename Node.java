public class Node{
    private Place origin;
    private Place target;
    private double x;
    private double y;
    private double nodeValue=Double.MAX_VALUE;
    public double nodeValue(){
        return this.nodeValue;
    }
    
    public Node(Place origin, Place target,double x, double y){
        this.x=x;
        this.y=y;
        this.origin=origin;
        this.target=target;
    }
    public void display(){
        StdDraw.setPenColor(0,0,0);
        StdDraw.textLeft(x+20*(this.nodeValue%5),y+20*(this.nodeValue%5),String.valueOf(this.nodeValue));
        System.out.println(String.valueOf(this.nodeValue));
    }
    public void pushNodeValue(double incomingValue,Place source){
        if (incomingValue<nodeValue){
            this.nodeValue=incomingValue;
        if (origin==source){
            target.inputNodeValue(incomingValue);
        }
        else{
            origin.inputNodeValue(incomingValue);
        }
        }
    }
}