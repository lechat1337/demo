package demo.model;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.hardmediumsoftbigdecimal.HardMediumSoftBigDecimalScore;
import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.optaplanner.core.impl.score.stream.common.ScoreImpactType;

public class ProblemSolver
{

    private static class ProgressWatcher implements SolverEventListener<FacilityLocationProblem>
    {
        private Solver<FacilityLocationProblem> solver;

        ProgressWatcher(Solver<FacilityLocationProblem> solver)
        {
            this.solver = solver;
        }

        @Override
        public void bestSolutionChanged(BestSolutionChangedEvent<FacilityLocationProblem> event)
        {

        }
    }

    private static final String SOLVER_CONFIG = "solverConfig.xml";

    public static FacilityLocationProblem solve(FacilityLocationProblem problem)
    {
        SolverFactory<FacilityLocationProblem> solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        Solver<FacilityLocationProblem> solver = solverFactory.buildSolver();

        solver.addEventListener(new ProgressWatcher(solver));
        FacilityLocationProblem solution = solver.solve(problem);
        ScoreManager<FacilityLocationProblem, HardMediumSoftBigDecimalScore> scoreManager = ScoreManager.create(solverFactory);
        System.out.println(scoreManager.explainScore(solution));
        return solution;
    }
}
