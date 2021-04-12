package demo.model;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;

import java.math.BigDecimal;

@ConstraintConfiguration
public class FLPConstraintConfiguration {

    static final String FACILITY_CAPACITY = "facility capacity";
    static final String FACILITY_SETUP_COST = "facility setup cost";
    static final String DISTANCE_FROM_FACILITY = "distance from facility";

    @ConstraintWeight(FACILITY_CAPACITY)
    HardSoftBigDecimalScore facilityCapacity = HardSoftBigDecimalScore.ofHard(BigDecimal.ONE);
    @ConstraintWeight(FACILITY_SETUP_COST)
    HardSoftBigDecimalScore facilitySetupCost = HardSoftBigDecimalScore.ofSoft(BigDecimal.ONE);
    @ConstraintWeight(DISTANCE_FROM_FACILITY)
    HardSoftBigDecimalScore distanceFromFacility = HardSoftBigDecimalScore.ofSoft(BigDecimal.ONE);
}

