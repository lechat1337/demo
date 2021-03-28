package demo.model;

import java.util.LinkedHashMap;

import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;


public class FPLScoreCalculator implements EasyScoreCalculator<FacilityLocationProblem, HardSoftLongScore> {
    @Override
    public HardSoftLongScore calculateScore(FacilityLocationProblem solution){
        long hard = 0;
        long soft = 0;
        int id = -1;
        LinkedHashMap<Integer, Double> assignedDemands = new LinkedHashMap<>();

        for(Customer c : solution.getCustomers()){
            if (c.getFacility() != null) {
                id = c.getFacility().getId();
                soft += c.distToFacility();
                if (assignedDemands.containsKey(id)) {
                    assignedDemands.put(id, assignedDemands.get(id) + c.getDemand());
                } else {
                    assignedDemands.put(id, (double) c.getDemand());
                }
            }else{
                hard += 100;
            }
        }

        for(Facility f : solution.getFacilities()) {
            if (assignedDemands.containsKey(f.getId())) {
                soft += f.getCost();
                hard += (f.getCapasity() - assignedDemands.get(f.getId()) < 0) ? (assignedDemands.get(f.getId()) - f.getCapasity()) : 0;
            }
        }

        hard = -hard;
        soft = -soft;

        return HardSoftLongScore.of(hard, soft);


    }
}
