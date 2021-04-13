package demo.model;

import java.math.BigDecimal;

public class Facility {

    private int id;
    private Location location;
    private BigDecimal capasity;
    private BigDecimal cost;

    public Facility(){
    }

    public Facility(int id, BigDecimal cost, BigDecimal cap, Location loc){
        this.id = id;
        this.location = loc;
        this.capasity = cap;
        this.cost = cost;
        
    }

    public int getId() {
        return id;
    }

    public void setCapasity(BigDecimal capasity) {
        this.capasity = capasity;
    }

    public BigDecimal getCapasity() {
        return capasity;
    }

    public long getCapasityLong() {return capasity.longValue();}

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public long getCostLong() {
        return cost.longValue();
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append(this.id)
         .append(" ")
         .append(this.location.toString());
        return b.toString();
    }

}
