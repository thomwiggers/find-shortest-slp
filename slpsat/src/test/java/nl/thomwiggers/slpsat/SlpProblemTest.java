/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

/**
 * @author Thom Wiggers
 *
 */
public class SlpProblemTest{

    private SlpProblem problem;

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Before
    public void setUp() throws Exception {
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
    @Test
    public void testGetProblem() {
        assertNotNull(this.problem.getProblem());
    }

    @Test
    public void testGetSolution() throws Exception {
        SlpProblem.Solution solution = this.problem.getSolution();
        assertNotNull(solution);
    }

    @Test
    public void testGetSolvableProblem() throws ContradictionException {
        assertNotNull(this.problem.getSolvableProblem());
    }

    @Test
    public void testGetSolvableProblemNoTunings() throws ContradictionException {
        assertNotNull(this.problem.getSolvableProblem(false));
    }

    /**
     * Test method for
     * {@link nl.thomwiggers.slpsat.SlpProblem#getTunings()}.
     */
    @Test
    public void testGetTunings() {
        assertNotNull(this.problem.getTunings());
    }

    @Test
    public void testIsSolvableTuned() throws ContradictionException,
    TimeoutException {
        assertTrue(this.problem.getSolvableProblem(true)
                .isSatisfiable());
    }

    @Test
    public void testIsSolvableUntuned() throws ContradictionException,
    TimeoutException {
        assertTrue(this.problem.getSolvableProblem(false)
                .isSatisfiable());
    }

    @Ignore("Doesn't work")
    public void testToDimacsTuned() throws ContradictionException {
        assertNotNull(this.problem.getDimacsSolver(true));
    }

    @Ignore("Doesn't work")
    public void testToDimacsUntuned() throws ContradictionException {
        assertNotNull(this.problem.getDimacsSolver(false));
    }

}
