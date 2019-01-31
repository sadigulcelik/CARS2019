import java.util.*;
/** A subset of Place, a "road" Provides a way to describe any path from one point to another, with (within place) a length that can be adjusted to account for curves or other difficulties in traversing the road. By design, a road can have only at most two nodes: a beginning node defined upon creation, and an end node defined upon destruction
*/
public class Road extends Place{
    private double xInitial;
    private double yInitial;
    private double xFinal;
    private double yFinal;
    
    public boolean addCar(Car car){
        for (Car c: this.cars){
            if (car.hasCollission(c,length())){
                return false;
            }
        }
        this.cars.add(car);
        return true;
    }
    
    public Node getNode(boolean orientation){
        if (orientation){
            return this.links.get(0);
        }
        else{
            return this.links.get(1);
        }
    }
    
    public double length(){
        return Math.pow((xInitial-xFinal)*(xInitial-xFinal)+(yInitial-yFinal)*(yInitial=yFinal),0.5);
    }
    /**
     * Returns the X coordinate xFinal: the open part of a road.
     * @return  xFinal
     */
    public double x(){
        return this.xFinal;
    }
    
    /**
     * Returns the Y coordinate yFinal: the open part of a road.
     * @return  yFinal
     */
    public double y(){
        return this.yFinal;
    }
    
    
    /**
     * Returns the X coordinate xInitial: the x coordinate of the side of the road that connects the road to its parent.
     * @return  xInitial
     */
    public double xInitial(){
        return this.xInitial;
    }
    
    /**
     * Returns the Y coordinate yInitial: the y coordinate the side of the road that connects the road to its parent.
     * @return  yInitial
     */
    public double yInitial(){
        return this.yInitial;
    }
     /**
     * Creates a road from a parent place to a new end point
     * @param  parent the place whose endpoint defines one end of the new road
     * @param  xFinal the x coordinate of the other end
     * @param  yFinal the y coordinate of the other end
     * @param  length the amount of time it takes to traverse the road
     * @throws  ParentCannotAdoptException
     */
    public Road(Place parent,double xFinal,double yFinal,double length) throws AdoptionException{
        super(parent,length);
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=xFinal;
        this.yFinal=yFinal;
    }
    
    /**
     * Creates a road from a parent place to a new end point
     * @param  parent the place whose endpoint defines one end of the new road
     * @param  xFinal the x coordinate of the other end
     * @param  yFinal the y coordinate of the other end
     * @throws  ParentCannotAdoptException
     */
    public Road(Place parent,double xFinal,double yFinal) throws AdoptionException{
        super(parent,Math.pow(Math.pow((xFinal-parent.x()),2)+Math.pow((yFinal-parent.y()),2),0.5));
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=xFinal;
        this.yFinal=yFinal;
    }
    
    
    /**
    * Displays the road as a line drawn from the initial point to the final point
    */
    public void display(){
        for(Node n: links){
            n.display();
        }
        
            
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(0,0,0);
        StdDraw.setPenColor(120,0,0);
        StdDraw.line(xInitial,yInitial,xFinal,yFinal);
        StdDraw.setPenColor(0,255,255);
        for(Car c: this.cars){
            c.display(xInitial,yInitial,xFinal,yFinal);
        }
    }
    
    
    /**
     * Creates a road from a parent place to an existing place
     * @param  parent the place whose endpoint defines one end of the new road
     * @param  endPoint the place to which the end of the new road connects
     * @param  length the amount of time it takes to traverse the road
     * @throws  ParentCannotAdoptException
     */
    public Road(Place parent,Place endPoint,double length) throws AdoptionException{
        super(parent,endPoint,length);
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=endPoint.x();
        this.yFinal=endPoint.y();
    }
    
    
    /**
     * Creates a road from a parent place to an existing place
     * @param  parent the place whose endpoint defines one end of the new road
     * @param  endPoint the place to which the end of the new road connects
     * @throws  ParentCannotAdoptException
     */
    public Road(Place parent,Place endPoint) throws AdoptionException{
        super(parent,endPoint,Math.pow(Math.pow((endPoint.x()-parent.x()),2)+Math.pow((endPoint.y()-parent.y()),2),0.5));
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=endPoint.x();
        this.yFinal=endPoint.y();
    }
    
    
    /**
     * Creates a road from a parent Intersection to an existing Intersection
     * @param  parent the place whose endpoint defines one end of the new road
     * @param  endPoint the place to which the end of the new road connects
     * @throws  ParentCannotAdoptException
     */
    public Road(Intersection parent,Intersection endPoint) throws AdoptionException{
        super((Place)parent,(Place)endPoint,Math.pow(Math.pow((endPoint.x()-parent.x()),2)+Math.pow((endPoint.y()-parent.y()),2),0.5));
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=endPoint.x();
        this.yFinal=endPoint.y();
    }
    
    
    
    
    /**
     * Creates a road from a parent road to a new end point
     * @param  parent the road whose endpoint defines one end of the new road
     * @param  xFinal the x coordinate of the other end
     * @param  yFinal the y coordinate of the other end
     * @param  length the amount of time it takes to traverse the road
     * @throws  ParentCannotAdoptException
     * @deprecated replaced by {@link #Road(Place parent,double xFinal,double yFinal,double length)}
     */
    @Deprecated
    public Road(Road parent,double xFinal,double yFinal,double length) throws AdoptionException{
        super(parent,length);
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=xFinal;
        this.yFinal=yFinal;
    }
    
    
    
    /**
     * Creates a road from a parent road to an existing intersection
     * @param  parent the road whose endpoint defines one end of the new road
     * @param  endPoint the intersection to which the end of the new road connects
     * @param  length the amount of time it takes to traverse the road
     * @throws  ParentCannotAdoptException
     * @deprecated replaced by {@link #Road(Place parent,Intersection endPoint,double length)}
     */
    @Deprecated
    public Road(Road parent,Intersection endPoint,double length) throws AdoptionException{
        super(parent,endPoint,length);
        this.xInitial=parent.x();
        this.yInitial=parent.y();
        this.xFinal=endPoint.x();
        this.yFinal=endPoint.y();
    }
    
    
    /**
     * Creates a road from an intersection to a new end point
     * @param  startPoint the starting intersection that defines the initial end of the new road
     * @param  xFinal the x coordinate of the other end
     * @param  yFinal the y coordinate of the other end
     * @param  length the amount of time it takes to traverse the road
     * @throws  ParentCannotAdoptException
     * @deprecated replaced by {@link #Road(Place parent,double xFinal,double yFinal,double length)}
     */
    @Deprecated
    public Road(Intersection startPoint,double xFinal,double yFinal,double length) throws AdoptionException{
        super(startPoint,length);
        this.xInitial=startPoint.x();
        this.yInitial=startPoint.y();
        this.xFinal=xFinal;
        this.yFinal=yFinal;
    }
    
    
    /**
     * Creates a road from an intersection to an existing intersection
     * @param  startPoint the starting intersection that defines the initial end of the new road
     * @param  endPoint the intersection to which the end of the new road connects
     * @param  length the amount of time it takes to traverse the road
     * @throws  ParentCannotAdoptException
     * @deprecated replaced by {@link #Road(Place parent,Intersection endPoint,double length)}
     */
    @Deprecated
    public Road(Intersection startPoint,Intersection endPoint,double length) throws AdoptionException{
        super(startPoint,endPoint,length);
        this.xInitial=startPoint.x();
        this.yInitial=startPoint.y();
        this.xFinal=endPoint.x();
        this.yFinal=endPoint.y();
    }
    
    
}
