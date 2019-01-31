public class Car{
    double maxSpeed;
    float percentage;//distance from parent
    boolean orientation;//true if directed away from parent
    Node awayFrom;
    Place loc;
    double size=8;
    
    public Car(double speed, Road r, float percentage,boolean orientation) throws NoMoreSpaceException{
        if (percentage<0){
            throw new NoMoreSpaceException();
        }
        loc=(Place)(r);
        this.maxSpeed=speed;
        this.orientation=orientation;
        this.awayFrom=r.getNode(!orientation);
        this.percentage=percentage;
        if(r.addCar(this)){
            System.out.println("car added");
        } 
        else{
            throw new NoMoreSpaceException();
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
    public void display(double xi, double yi, double xf,double yf){
        double x=xi+(xf-xi)*this.percentage;
        double y=yi+(yf-yi)*this.percentage;
        //StdDraw.filledEllipse(x,y, 3*size,3*size); 
        unitVector forward;
        if (this.orientation){
            forward=new unitVector(xi-xf, yi-yf);
        }
        else{
            forward=new unitVector(xf-xi, yf-yi);
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
    public void inputNodeValue(){
        this.loc.inputNodeValue(0,awayFrom); 
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