package demo.model;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
//import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

@PlanningSolution
public class FacilityLocationProblem {
   
    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "facilityRange")
    private List<Facility> facilities;
    @PlanningEntityCollectionProperty
    private List<Customer> customers;

    @PlanningScore
    private HardSoftLongScore score;

    public FacilityLocationProblem(){
        this.facilities = new ArrayList<>();
        this.customers = new ArrayList<>();

    }

    public FacilityLocationProblem(
        List<Facility> facilities,
        List<Customer> customers){
        this.facilities = facilities;
        this.customers = customers;
    }

    public void addFacility(Facility facility){
        this.facilities.add(facility);
    }

    public void addCustomer(Customer customer){
        this.customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public HardSoftLongScore getScore() {
        return score;
    }

    public void setScore(HardSoftLongScore score) {
        this.score = score;
    }

}
