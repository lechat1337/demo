/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package demo.exec;

import java.io.*;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner; // Import the Scanner class to read text files

import demo.model.*;
import org.optaplanner.benchmark.api.PlannerBenchmark;
import org.optaplanner.benchmark.api.PlannerBenchmarkFactory;

public class ConsoleRunner {

    public static void main(String[] args) throws IOException {

//solver configuration, coursera data
        FacilityLocationProblemFileIO io = new FacilityLocationProblemFileIO();
        String inputs[] = {"/data/fl_25_2", "/data/fl_50_6", "/data/fl_100_7", "/data/fl_100_1", "/data/fl_200_7", "/data/fl_500_7", "/data/fl_1000_2", "/data/fl_2000_2"};
//        FacilityLocationProblem problem = io.read(new File("C:\\Users\\aleksejs\\coursera\\facility\\input_file.txt"));//+inputs[3]));
//
//        FacilityLocationProblem solution = ProblemSolver.solve(problem);
//        io.write(solution, new File("C:\\Users\\aleksejs\\coursera\\facility\\solution_file.txt"));
//benchmark config
//        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromXmlResource(
//                "benchmarkConfig.xml");
//        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark();
//        benchmark.benchmarkAndShowReportInBrowser();
// bad benchmark
        PlannerBenchmarkFactory benchmarkFactory = PlannerBenchmarkFactory.createFromSolverConfigXmlResource(
                "solverConfig.xml");
        FacilityLocationProblem problem1 = io.read(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[0]));
        FacilityLocationProblem problem2 = io.read(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[5]));
        FacilityLocationProblem problem3 = io.read(new File("C:\\Users\\aleksejs\\coursera\\facility\\" + inputs[6]));
        PlannerBenchmark benchmark = benchmarkFactory.buildPlannerBenchmark(
                problem1, problem2, problem3);
        benchmark.benchmarkAndShowReportInBrowser();
    }
////
////    private static FacilityLocationProblem getFirstSolution(FacilityLocationProblem problem) {
//
//    }
}
