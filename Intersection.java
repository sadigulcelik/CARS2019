import java.util.*;
public class Intersection extends Place{
    double x;
    double y;
    /**
     * Returns the x coordinate of the intersection
     * @return  x the x coordinate
     */
    public double x(){
        return this.x;
    }
    
    /**
     * Returns the y coordinate of the intersection
     * @return  y the y coordinate
     */
    
    public double y(){
        return this.y;
    }
    
    public boolean addCar(Car car){
        if (this.cars.size()==0){
            this.cars.add(car);
            return true;
        }
        return false;
    }
    
    /**
     * Used only once for the creation of the central square which starts the entire grid. Since it's the first part of the grid being added, it will not be connected to anything upon initialization
     * @param  length the distance across the intersection (to be used in calculating how long it takes to cross the intersection)
     * @param  x the x coordinate of the intersection
     * @param  y the y coordinate of the intersection
     */
    protected Intersection(double length,double x,double y){
        super(length);
        this.x=x;
        this.y=y;
    }
    /**
     * All intersections except for the first one will be added on to the end of a road. This is the road-based constructor for Intersections.
     * @param  parent the parent road whose open end the intersection will be connected to
     * @param  length the distance across the intersection (to be used in calculating how long it takes to cross the intersection)
     * @throws  ParentCannotAdoptException
     */
    public Intersection(Road parent, double length) throws AdoptionException{
        super(parent,length);
        this.x=parent.x();
        this.y=parent.y();
    }
    
    /**
    * Displays the Intersection as a filled circle drawn around the location
    */
    public void display(){
        for(Node n: this.links){
            n.display();
        }
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(50,150,0);
        StdDraw.filledCircle(x, y,10);
        StdDraw.setPenColor(0,255,255);
        for(Car c: this.cars){
            c.display(x,y,x+1,y+1);
        }
    }
}
