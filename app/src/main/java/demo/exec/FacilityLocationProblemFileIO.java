package demo.exec;

import demo.model.*;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;

import java.io.*;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

public class FacilityLocationProblemFileIO implements SolutionFileIO<FacilityLocationProblem>  {

    public FacilityLocationProblemFileIO(){}
    @Override
    public String getInputFileExtension() {
        return "";
    }
    public FacilityLocationProblem read(File file) {
        FacilityLocationProblem problem = new FacilityLocationProblem();
        Facility tmpFac = new Facility();
        int nClients = 0;
        int nWarehouses = 0;
        Matrix.clear();
        try {
            Scanner sc = new Scanner(file).useLocale(Locale.US);
            nWarehouses = sc.nextInt();
            nClients = sc.nextInt();
            for(int i=0; i < nWarehouses; i++){
                tmpFac = new Facility(i,
                        BigDecimal.valueOf(sc.nextDouble()),
                        BigDecimal.valueOf(sc.nextDouble()),
                        new Location(sc.nextDouble(), sc.nextDouble()));
                //System.out.println(tmpFac.toStirng());
                problem.addFacility(tmpFac);
            }

            for(int i=0; i < nClients; i++){
                problem.addCustomer(new Customer(i,
                        BigDecimal.valueOf(sc.nextDouble()),
                        new Location(sc.nextDouble(), sc.nextDouble())));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Matrix.adjustMatrix();
        Matrix.calculate();
        return problem;
    }

    @Override
    public void write(FacilityLocationProblem facilityLocationProblem, File outputSolutionFile) {
        StringBuilder res = new StringBuilder();
        res.append(facilityLocationProblem.getScore().getSoftScore().abs()).append(" 0\n");
        for (Customer c : facilityLocationProblem.getCustomers()){
            res.append(c.getFacility().getId()).append(" ");
        }
        BufferedWriter writer = null;
        System.out.println(res.toString());
        try {
            writer = new BufferedWriter(new FileWriter(outputSolutionFile.getAbsolutePath(), false));
            writer.write(res.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
