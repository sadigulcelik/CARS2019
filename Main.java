public class Main{
    public static void main(String args[]) throws AdoptionException{
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0,800);
        StdDraw.setYscale(0,800);
        
        //Grid g=new Grid();
        Intersection a=new TimesSquare(100,50,5.2);
        Road b=new Road((Place)a,20,300,17.3);
        Road c=new Road((Place)b,300,300,17.3);
        Road d=new Road((Place)c,a,17.3);
        Road e=new Road((Place)a,80.0,717.3,70);
        e.inputNodeValue(0);
        a.displayAll();
        e.inputNodeValue(0);
        a.displayAll();
    }
}