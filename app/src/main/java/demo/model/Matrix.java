package demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Matrix {


    private static BigDecimal[][] matrix; //[customer][facility]
    private static List<Location> customers = new ArrayList<>();
    private static List<Location> facilities = new ArrayList<>();
    private static BigDecimal[][] customerMatrix;
    public Matrix(){}

    public Matrix(BigDecimal[][] matrix, List<Location>  customers, List<Location>  facilities){
        Matrix.matrix = matrix;
        Matrix.customers = customers;
        Matrix.facilities = facilities;
    }

    public static void addFacility(Location location) {
        facilities.add(location);
    }

    public static void addCustomer(Location location) {
        customers.add(location);
    }

    public static BigDecimal getDist(int i, int j){
        return matrix[i][j];
    }

    public static BigDecimal getDistBetweenCustomers(int i, int j){
        return customerMatrix[i][j];
    }

    public static void calculate() {
        for(int i = 0; i < customers.size(); i++){
            for(int j = 0; j < facilities.size(); j++){
                matrix[i][j] = customers.get(i).distTo(facilities.get(j));
            }
            for(int j = 0; j < customers.size(); j++){
                customerMatrix[i][j] = customers.get(i).distTo(customers.get(j));
            }
        }

    }

    public static void adjustMatrix() {
        matrix = new BigDecimal[customers.size()][facilities.size()];
        customerMatrix = new BigDecimal[customers.size()][customers.size()];
    }

    public static BigDecimal[][] getCustomerMatrix() {
        return customerMatrix;
    }

    public static void setCustomerMatrix(BigDecimal[][] customerMatrix) {
        Matrix.customerMatrix = customerMatrix;
    }

    public static void clear() {
        customerMatrix = new BigDecimal[1][1];
        matrix = new BigDecimal[1][1];
        customers = new ArrayList<>();
        facilities = new ArrayList<>();
    }

    public List<Location> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Location> facilities) {
        this.facilities = facilities;
    }

    public List<Location> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Location> customers) {
        this.customers = customers;
    }
}
