package demo.model;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

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
           // DebugUtil.debug(event.getNewBestSolution(), solver.explainBestScore());
        }
    }

    private static final String SOLVER_CONFIG = "solverConfig.xml";

    public static FacilityLocationProblem solve(FacilityLocationProblem problem)
    {
        SolverFactory<FacilityLocationProblem> solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        Solver<FacilityLocationProblem> solver = solverFactory.buildSolver();

        solver.addEventListener(new ProgressWatcher(solver));

        return solver.solve(problem);
    }
}
