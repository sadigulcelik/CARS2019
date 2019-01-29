public class Car{
    double maxSpeed;
    float percentage;//distance from parent
    boolean orientation;//true if directed away from parent
    Place loc;
    double size=5;
    
    public Car(double speed, Road r, float percentage,boolean orientation) throws NoMoreSpaceException{
        if (percentage<0){
            throw new NoMoreSpaceException();
        }
        loc=(Place)(r);
        this.maxSpeed=speed;
        this.orientation=orientation;
        this.percentage=percentage;
        if(r.addCar(this)){
            
        } 
                
        
        
    }
    public boolean hasCollission(Car c, double roadLength){
        if (this.percentage>c.Percentage()){
            if (c.Orientation()==true&&this.orientation==false){
                return true;//we are bound to have a collision since c is moving away from the parent but "this" is blocking the way
            }
            if( ( (this.size+c.Size()) /2 ) > (roadLength*(this.percentage-c.Percentage()) ) ){
                return true;
            }
        }
        else{
            if (c.Orientation()==false&&this.orientation==true){
                return true;//we are bound to have a collision since this is moving away from the parent but c is blocking the way
            }
            if( ( (this.size+c.Size()) /2 ) > (roadLength*(c.Percentage()-this.percentage) ) ){
                return true;
            }
        }
            
        
        return false;
        
    }
    public double Size(){
        return this.size;
    }
    public boolean Orientation(){
        return this.orientation;
    }
    public double Percentage(){
        return this.percentage;
    }
}