package demo.model;

import java.math.BigDecimal;

public class Location {
    private double x;
    private double y;

    public Location(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }

    public String toString(){
        StringBuilder a = new StringBuilder();
        a.append("x: ").append(x).append(", y:").append(y);
        return a.toString();
    }

    public BigDecimal distTo(Location location) {
        return BigDecimal.valueOf(Math.sqrt(sqr(x - location.x) + sqr(y - location.y)));
    }

    private double sqr(double a){ return a*a;}
}
