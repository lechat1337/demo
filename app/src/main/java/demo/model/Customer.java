package demo.model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.math.BigDecimal;

@PlanningEntity(difficultyComparatorClass = CustomerDifficultyComparator.class)
public class Customer {
    private int id;
    private Location location;
    private BigDecimal demand;

    @PlanningVariable(valueRangeProviderRefs = "facilityRange")
    private Facility facility;

    public Customer(){}
    
    public Customer(int id, BigDecimal demand, Location location){
        this.id = id;
        this.demand = demand;
        this.location = location;
    }

    public boolean isAssigned(){return facility != null;}

    public BigDecimal getDemand() {
        return demand;
    }

    public long getDemandLong() {
        return demand.longValue();
    }

    public void setDemand(BigDecimal demand) {
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

    public BigDecimal distToFacility() {
        if (facility != null){
            return Matrix.getDist(this.id, facility.getId());
        }
        return BigDecimal.valueOf(0);
    }

    public long distToFacilityLong(){
        if (facility != null){
            return Matrix.getDist(this.id, facility.getId()).longValue();
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
