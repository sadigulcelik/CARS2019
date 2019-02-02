import java.util.*;
import java.io.*;
public class Car{
    double maxSpeed;
    double distFromParent;
    boolean orientation;//true if directed away from parent
    Node awayFrom;
    Place loc;
    double size=8;
    ArrayList<Node>route;
    Node currentDest;
    
    public Car(double speed, Road r, double distFromParent,boolean orientation) throws NoMoreSpaceException{
        if (distFromParent<0){
            throw new NoMoreSpaceException();
        }
        loc=(Place)(r);
        this.maxSpeed=speed;
        this.orientation=orientation;
        this.awayFrom=r.getNode(orientation);
        this.distFromParent=distFromParent;
        if(r.addCar(this)){
            System.out.println("car added");
        } 
        else{
            throw new NoMoreSpaceException();
        }
                
        
        
    }
    public boolean move(){
        //System.out.println("pre: "+distFromParent);
        double p=distFromParent;
        if (orientation){
            p+=this.maxSpeed;
            if(p>loc.length()){
                if(route.indexOf(currentDest)==route.size()-1){ 
                    return false;
                }
                this.loc.removeCar(this);
                //System.out.println("here: "+currentDest.getOther(this.loc));
                this.loc=(Place)(currentDest.getOther(this.loc));
                System.out.println(this.loc);
                System.out.println(this.loc.length());
                this.loc.addCar(this);
                awayFrom=currentDest;
                //System.out.println(route.indexOf(currentDest));
                currentDest=route.get(route.indexOf(currentDest)+1);
                //System.out.println("node val: "+ currentDest.nodeValue());
                if(loc instanceof Intersection){
                    orientation=true;
                    p=0;
                }
        else if(loc instanceof Road){
                if(((Road)this.loc).isParentalSide(awayFrom)){
                    orientation=true;
                    p=0;
                }
                else{
                    orientation=false;
                    p=this.loc.length();
                }
            }
            }
        }
        else{
            p-=this.maxSpeed;
            if(p<0){
                if(route.indexOf(currentDest)==route.size()-1){
                    return false;
                }
                this.loc.removeCar(this);
                this.loc=(Place)(currentDest.getOther(this.loc));
                this.loc.addCar(this);
                awayFrom=currentDest;
                currentDest=route.get(route.indexOf(currentDest)+1);
                //System.out.println("node val: "+currentDest.nodeValue());
                if(loc instanceof Intersection){
                    orientation=true;
                    p=0;
                }
        else if(loc instanceof Road){
                if(((Road)this.loc).isParentalSide(awayFrom)){
                    orientation=true;
                    p=0;
                }
                else{
                    orientation=false;
                    p=this.loc.length();
                }
            }
            }
        }
        
        distFromParent=p;
        return true;
        //System.out.println("after: "+distFromParent);
        
    }
    
    public void setDestination(Node n){
        this.inputNodeValue();
        route=n.getPath();
        System.out.println("start");
        for(int i=0; i<route.size();i++){
            
            System.out.print(route.get(i).nodeValue()+"   ");
            
        }
        System.out.println("end");
        currentDest=route.get(0);
    }
    
    public void inputNodeValue(){
        ArrayList<Node>myPath=new ArrayList<Node>(); 
        double length;
        if (orientation){length=distFromParent;}
        else{length=loc.length()-distFromParent;}
        this.loc.inputNodeValue(-length,awayFrom,myPath); 
    }
    public boolean hasCollission(Car c, double roadLength){
        if (this.distFromParent>c.distFromParent()){
            if (c.Orientation()==true&&this.orientation==false){
                return true;//we are bound to have a collision since c is moving away from the parent but "this" is blocking the way
            }
            if( ( (this.size+c.Size()) /2 ) > (this.distFromParent-c.distFromParent() ) ){
                return true;
            }
        }
        else{
            if (c.Orientation()==false&&this.orientation==true){
                return true;//we are bound to have a collision since this is moving away from the parent but c is blocking the way
            }
            if( ( (this.size+c.Size()) /2 ) > (c.distFromParent()-this.distFromParent ) ){
                return true;
            }
        }
            
        
        return false;
        
    }
    public void display(double xi, double yi, double xf,double yf){
        double x=xi+(xf-xi)*this.distFromParent/this.loc.length();
        double y=yi+(yf-yi)*this.distFromParent/this.loc.length();
        //StdDraw.filledEllipse(x,y, 3*size,3*size); 
        unitVector forward;
        if (this.orientation){
            forward=new unitVector(xf-xi, yf-yi);
        }
        else{
            
            forward=new unitVector(xi-xf, yi-yf);
        }
        unitVector backward=forward.opp();
        unitVector perp=forward.perp();
        double width =5;
        double arrowWidth=12;
        double backl=2.6;
        double forwardl=1.5;
        double arrowTip=5;
        double[] a = {x+backl*size*backward.x()+width*perp.x(),
                      x+backl*size*backward.x()-width*perp.x(),
                      x+forwardl*size*forward.x()-width*perp.x(),
                      x+forwardl*size*forward.x()-arrowWidth*perp.x(),
                      x+arrowTip*size*forward.x(),
                      x+forwardl*size*forward.x()+arrowWidth*perp.x(),
                      x+forwardl*size*forward.x()+width*perp.x()
                     };
        
        double[] b = {y+backl*size*backward.y()+width*perp.y(),
                      y+backl*size*backward.y()-width*perp.y(),
                      y+forwardl*size*forward.y()-width*perp.y(),
                      y+forwardl*size*forward.y()-arrowWidth*perp.y(),
                      y+arrowTip*size*forward.y(),
                      y+forwardl*size*forward.y()+arrowWidth*perp.y(),
                      y+forwardl*size*forward.y()+width*perp.y()
                     };
        StdDraw.filledPolygon(a, b);
        
    }
    
    public double Size(){
        return this.size;
    }
    public boolean Orientation(){
        return this.orientation;
    }
    public double distFromParent(){
        return this.distFromParent;
    }
    public class unitVector{
        private double x;
        private double y;
        public unitVector(double dx, double dy){
            double c=Math.pow(dx*dx+dy*dy,0.5);
            this.x=dx/c;
            this.y=dy/c;
        }
        public unitVector perp(){
            /*unitVector[] a=new unitVector[2];
            a[0]=;
            a[1]=new unitVector(y,-x);*/
            return new unitVector(-y,x);
        }
        public unitVector opp(){
            return new unitVector(-x,-y);
        }
        public double x(){
            return x;
        }
        public double y(){
            return y;
        }
    }
}