package demo.model;

public class Facility {

    private int id;
    private Location location;
    private double capasity;
    private double cost;

    public Facility(){}

    public Facility(int id, double cost, double cap,  Location loc){
        this.id = id;
        this.location = loc;
        this.capasity = cap;
        this.cost = cost;
        
    }

    public int getId() {
        return id;
    }

    public double getCapasity() {
        return capasity;
    }

    public double getCost() {
        return cost;
    }

    public Location getLocation() {
        return location;
    }


    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append(this.id)
         .append(" ")
         .append(this.location.toString());
        return b.toString();
    }
}
