/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

/**
 * Test various SLP problems
 *
 * @author Thom Wiggers
 *
 */
public class SlpProblemTest {

    /**
     * The problem we test against
     */
    private SlpProblem problem;

    /**
     * Set up before each test case
     *
     * @throws Exception
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
     * Test method for {@link SlpProblem#getProblem()}.
     */
    @Test
    public void testGetProblem() {
        Assert.assertNotNull(this.problem.getProblem());
    }

    /**
     * Confirm getSolution doesn't error
     *
     * @throws Exception
     */
    @Test
    public void testGetSolution() throws Exception {
        SlpProblem.Solution solution = this.problem.getSolution();
        Assert.assertNotNull(solution);
    }

    /**
     * Confirm getSolvableProblem() doesn't error
     *
     * @throws ContradictionException
     */
    @Test
    public void testGetSolvableProblem() throws ContradictionException {
        Assert.assertNotNull(this.problem.getSolvableProblem());
    }

    /**
     * Test getSolvableProblem works without tunings
     *
     * @throws ContradictionException
     */
    @Test
    public void testGetSolvableProblemNoTunings() throws ContradictionException {
        Assert.assertNotNull(this.problem.getSolvableProblem(false));
    }

    /**
     * Test method for
     * {@link nl.thomwiggers.slpsat.SlpProblem#getTunings()}.
     */
    @Test
    public void testGetTunings() {
        Assert.assertNotNull(this.problem.getTunings());
    }

    /**
     * Test if we can solve the problem with tunings
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testIsSolvableTuned() throws ContradictionException,
            TimeoutException {
        Assert.assertTrue(this.problem.getSolvableProblem(true).isSatisfiable());
    }

    /**
     * solve the problem without tunings
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testIsSolvableUntuned() throws ContradictionException,
            TimeoutException {
        Assert.assertTrue(this.problem.getSolvableProblem(false)
                .isSatisfiable());
    }

    /**
     * Try to export the problem.
     *
     * @throws ContradictionException
     */
    @Test
    public void testToDimacsTuned() throws ContradictionException {
        Assert.assertNotNull(this.problem.getDimacsSolver(true));
    }

    /**
     * Try to export the problem without tunings
     *
     * @throws ContradictionException
     */
    @Test
    public void testToDimacsUntuned() throws ContradictionException {
        Assert.assertNotNull(this.problem.getDimacsSolver(false));
    }

}
