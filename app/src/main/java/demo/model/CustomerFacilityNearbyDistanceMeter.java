package demo.model;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

public class CustomerFacilityNearbyDistanceMeter implements NearbyDistanceMeter<Customer, Facility> {
    @Override
    public double getNearbyDistance(Customer origin, Facility destination) {
        return Matrix.getDist(origin.getId(), destination.getId()).doubleValue();
    }
}
