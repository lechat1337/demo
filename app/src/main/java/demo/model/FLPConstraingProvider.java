package demo.model;


import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

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
                .groupBy(Customer::getFacility, sumLong(Customer::getDemandLong))
                .filter((facility, demand) -> demand > facility.getCapasityLong())
                .penalizeLong(
                       "Faciliy capasisty",
                        HardSoftLongScore.ONE_HARD,
                        (facility, demand) -> (demand - facility.getCapasityLong()));
    }

    private Constraint penalizeSetupCost(ConstraintFactory factory) {
        return factory.from(Customer.class)
                .groupBy(Customer::getFacility)
                .penalizeLong(
                       "Setup Cost",
                        HardSoftLongScore.ONE_SOFT,
                        Facility::getCostLong);
    }

    private Constraint penalizeDistancesToFacilities(ConstraintFactory factory) {
        return factory.from(Customer.class)
                .filter(Customer::isAssigned)
                .penalizeLong(
                        "Distance",
                        HardSoftLongScore.ONE_SOFT,
                        Customer::distToFacilityLong);
    }

}