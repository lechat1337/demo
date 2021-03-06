/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo.exec;

import java.io.*;
import java.util.Locale;
import java.util.Scanner; // Import the Scanner class to read text files

import demo.model.Customer;
import demo.model.Facility;
import demo.model.FacilityLocationProblem;
import demo.model.Location;
import demo.model.ProblemSolver;
import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;

public class ConsoleRunner {
    public static FacilityLocationProblem getProblem(File file) {
        FacilityLocationProblem problem = new FacilityLocationProblem();
        Facility tmpFac = new Facility();
        Customer tmpCus = new Customer();
        int nClients = 0;
        int nWarehouses = 0;
        
        try {
            Scanner sc = new Scanner(file).useLocale(Locale.US);
            nWarehouses = sc.nextInt();
            nClients = sc.nextInt();
            for(int i=0; i < nWarehouses; i++){
                tmpFac = new Facility(i,
                sc.nextDouble(),
                sc.nextDouble(),
                new Location(sc.nextDouble(), sc.nextDouble()));
                //System.out.println(tmpFac.toStirng());
                problem.addFacility(tmpFac);
            }

            for(int i=0; i < nClients; i++){
                problem.addCustomer(new Customer(i,
                                                 sc.nextDouble(),
                                                 new Location(sc.nextDouble(), sc.nextDouble())));     
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return problem;
    }

    public static void main(String[] args) throws IOException {

        String inputs[] = {"/data/fl_25_2", "/data/fl_50_6", "/data/fl_100_7", "/data/fl_100_1", "/data/fl_200_7", "/data/fl_500_7", "/data/fl_1000_2", "/data/fl_2000_2"};
//        FacilityLocationProblem problem = ConsoleRunner.getProblem(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[3]));
////        problem = getFirstSolution(problem);
//        FacilityLocationProblem solution = ProblemSolver.solve(problem);
//        StringBuilder res = new StringBuilder();
//        res.append(FPLScoreCalculator.calculateScore(solution)).append(" 0\n");
//        for (Customer c : solution.getCustomers()){
//            res.append(c.getFacility().getId()).append(" ");
//        }
//        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\aleksejs\\coursera\\facility\\solution_file.txt", false));
//        writer.write(res.toString());
//        writer.close();

        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromSolverConfigXmlResource(
                "solverConfig.xml");

        FacilityLocationProblem problem1 = ConsoleRunner.getProblem(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[0]));
        FacilityLocationProblem problem2 = ConsoleRunner.getProblem(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[1]));
        FacilityLocationProblem problem3 = ConsoleRunner.getProblem(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[2]));
        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark(
                problem1, problem2, problem3);
        benchmark.benchmarkAndShowReportInBrowser();
    }
}
