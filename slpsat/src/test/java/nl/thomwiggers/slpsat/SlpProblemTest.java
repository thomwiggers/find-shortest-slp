/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat;

import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import junit.framework.TestCase;

/**
 * @author Thom Wiggers
 *
 */
public class SlpProblemTest extends TestCase {

    private SlpProblem problem;

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        boolean[][] A = new boolean[][] { { true, true, true, true, true },
                { true, true, true, true, false },
                { true, true, true, false, true },
                { false, false, true, true, true },
                { true, false, false, false, true } };
        this.problem = new SlpProblem(6, A);
    }

    /**
     * Test method for
     * {@link nl.thomwiggers.slpsat.SlpProblem#getProblem()}.
     */
    public void testGetProblem() {
        assertNotNull(this.problem.getProblem());
    }

    public void testGetSolution() throws Exception {
        SlpProblem.Solution solution = this.problem.getSolution();
        assertNotNull(solution);
    }

    public void testGetSolvableProblem() throws ContradictionException {
        assertNotNull(this.problem.getSolvableProblem());
    }

    public void testGetSolvableProblemNoTunings() throws ContradictionException {
        assertNotNull(this.problem.getSolvableProblem(false));
    }

    /**
     * Test method for
     * {@link nl.thomwiggers.slpsat.SlpProblem#getTunings()}.
     */
    public void testGetTunings() {
        assertNotNull(this.problem.getTunings());
    }

    public void testIsSolvableTuned() throws ContradictionException,
            TimeoutException {
        assertTrue(this.problem.getSolvableProblem(true).isSatisfiable());
    }
    
    public void testIsSolvableUntuned() throws ContradictionException,
            TimeoutException {
        assertTrue(this.problem.getSolvableProblem(false).isSatisfiable());
    }

}
