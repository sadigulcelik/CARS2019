public class Node{
    private Place origin;
    private Place target;
    
    private double nodeValue=Double.MAX_VALUE;
    public double nodeValue(){
        return this.nodeValue;
    }
    
    public Node(Place origin, Place target){
        this.origin=origin;
        this.target=target;
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