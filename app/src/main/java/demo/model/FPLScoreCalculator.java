package demo.model;

import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

import java.math.BigDecimal;
import java.util.LinkedHashMap;


public class FPLScoreCalculator implements EasyScoreCalculator<FacilityLocationProblem, HardSoftBigDecimalScore> {
    @Override
    public HardSoftBigDecimalScore calculateScore(FacilityLocationProblem solution){
        BigDecimal hard = BigDecimal.ZERO;
        BigDecimal soft = BigDecimal.ZERO;
        int id = -1;
        LinkedHashMap<Integer, BigDecimal> assignedDemands = new LinkedHashMap<>();

        for(Customer c : solution.getCustomers()){
            if (c.getFacility() != null) {
                id = c.getFacility().getId();
                soft = soft.add(c.distToFacility());
                if (assignedDemands.containsKey(id)) {
                    assignedDemands.put(id, assignedDemands.get(id).add(c.getDemand()));
                } else {
                    assignedDemands.put(id, c.getDemand());
                }
            }else{
                //hard = hard.add(BigDecimal.valueOf(100));
            }
        }

        for(Facility f : solution.getFacilities()) {
            if (assignedDemands.containsKey(f.getId())) {
                soft = soft.add(f.getCost());
                hard = hard.add((f.getCapasity().subtract(assignedDemands.get(f.getId())).compareTo(BigDecimal.ZERO) == -1) ?
                        (assignedDemands.get(f.getId()).subtract(f.getCapasity())) : BigDecimal.ZERO);
            }
        }

        hard = hard.negate();
        soft = soft.negate();

        return HardSoftBigDecimalScore.of(hard, soft);


    }
}
