package demo.model;

import org.optaplanner.core.impl.heuristic.selector.common.nearby.NearbyDistanceMeter;

public class CustomerCustomerNearbyDistanceMeter implements NearbyDistanceMeter<Customer, Customer> {
    @Override
    public double getNearbyDistance(Customer customer1, Customer customer2)
    {
        return Matrix.getDistBetweenCustomers(customer1.getId(), customer2.getId()).doubleValue();
    }

}
