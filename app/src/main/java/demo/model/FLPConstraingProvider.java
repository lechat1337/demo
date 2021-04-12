package demo.model;


import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sumBigDecimal;
import static org.optaplanner.core.api.score.stream.ConstraintCollectors.sumLong;

public class FLPConstraingProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
                penalizeDistancesToFacilities(factory),
                penalizeSetupCost(factory),
                penalizeCapasity(factory)
        };
    }

    Constraint penalizeCapasity(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Customer.class)
                .groupBy(Customer::getFacility, sumBigDecimal(Customer::getDemand))
                .filter((facility, demand) -> demand.compareTo(facility.getCapasity()) == 1)
                .penalizeBigDecimal(
                       "Faciliy capasisty",
                        HardSoftBigDecimalScore.ONE_HARD,
                        (facility, demand) -> (demand.subtract(facility.getCapasity())));
    }

    private Constraint penalizeSetupCost(ConstraintFactory factory) {
        return factory.from(Customer.class)
                .groupBy(Customer::getFacility)
                .penalizeBigDecimal(
                       "Setup Cost",
                        HardSoftBigDecimalScore.ONE_SOFT,
                        Facility::getCost);
    }

    private Constraint penalizeDistancesToFacilities(ConstraintFactory factory) {
        return factory.from(Customer.class)
                .filter(Customer::isAssigned)
                .penalizeBigDecimal(
                        "Distance",
                        HardSoftBigDecimalScore.ONE_SOFT,
                        Customer::distToFacility);
    }

}