package demo.model;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class CustomerDifficultyComparator implements Comparator<Customer> {

    public int compare(Customer a, Customer b) {
        return new CompareToBuilder()
                .append(a.getDemand(), b.getDemand())
                .append(a.getId(), b.getId())
                .toComparison();
    }

}
