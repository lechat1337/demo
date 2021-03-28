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

    public boolean isAssigned(){return facility != null;}

    public long getDemand() {
        return (long) Math.ceil(demand);
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

    public long distToFacility() {
        if (facility != null){
            return (long) Math.ceil(location.distTo(facility.getLocation()));
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
