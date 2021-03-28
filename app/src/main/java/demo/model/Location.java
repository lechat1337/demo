package demo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

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

    public double distTo(Location location) {
        return Math.sqrt(sqr(x - location.x) + sqr(y - location.y));
    }

    private double sqr(double a){ return a*a;}
}
