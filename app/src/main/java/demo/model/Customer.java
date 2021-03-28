package demo.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Customer {
    private int id;
    private Location location;
    private double demand;

    @PlanningVariable(valueRangeProviderRefs = "facilityRange")
    private Facility facility;

    public Customer(){}
    
    public Customer(int id, double demand, Location location){
        this.id = id;
        this.demand = demand;
        this.location = location;
    }

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double distToFacility() {
        if (facility != null){
            return location.distTo(facility.getLocation());
        }
        return 0;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("customer id:").append(id);
        s.append(" location: ").append(location);
        return s.toString();
    }
}
