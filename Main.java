import java.util.*;
public class Main{
    public static void main(String args[]) throws AdoptionException{
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0,800);
        StdDraw.setYscale(0,800);
        
        Intersection bi1=new TimesSquare(50,50,42);
        Road br1=new Road((Place)bi1,250,50);
        Intersection bi2=new Intersection(br1,42);
        Road br2=new Road((Place)bi2,450,50);
        Intersection bi3=new Intersection(br2,42);
        
        Road bm=new Road((Place)bi1,50,250);
        
        Intersection mi1=new Intersection(bm,42);
        Road mr1=new Road((Place)mi1,250,250);
        Intersection mi2=new Intersection(mr1,42);
        Road mr2=new Road((Place)mi2,450,250);
        Intersection mi3=new Intersection(mr2,42);
        
        Road mt=new Road((Place)mi1,50,450);
        
        Intersection ti1=new Intersection(mt,42);
        Road tr1=new Road((Place)ti1,250,450);
        Intersection ti2=new Intersection(tr1,42);
        Road tr2=new Road((Place)ti2,450,450);
        Intersection ti3=new Intersection(tr2,42);
        
        Road tk=new Road((Place)ti1,50,650);
        
        Intersection ki1=new Intersection(tk,42);
        Road kr1=new Road((Place)ki1,250,650);
        Intersection ki2=new Intersection(kr1,42);
        Road kr2=new Road((Place)ki2,450,650);
        Intersection ki3=new Intersection(kr2,42);
        
        Road l00=new Road(bi2,mi2);
        Road l01=new Road(bi3,mi3);
        
        Road l10=new Road(ti2,mi2);
        Road l11=new Road(ti3,mi3);
        
        Road l20=new Road(ti2,ki2);
        Road l21=new Road(ti3,ki3);
        Car c;
        StdDraw.enableDoubleBuffering();
        ArrayList<Node>possibleDestinations=((Place)bi1).getAllNodes();
        try{
        c=new Car(0.4, br1, 60.0,true);
          c.setDestination(possibleDestinations.get((int)(Math.random()*possibleDestinations.size()))); 
            while(true){
            StdDraw.clear();
            if(!c.move()){
                for (Node n: possibleDestinations){
                    n.refresh();
                }
                c.setDestination(possibleDestinations.get((int)(Math.random()*possibleDestinations.size())));  
            }
            bi1.displayAll();
            StdDraw.show();
        }
        }
        catch(NoMoreSpaceException n){
            
        }
        /*try{
        new Car(10, br1, (float)0.5,true);
        }
        catch(NoMoreSpaceException n){
            System.out.println("failed to add");
        }*/
        
        
    }
}