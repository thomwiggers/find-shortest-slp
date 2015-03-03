/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Thom Wiggers
 *
 */
@Ignore("Usually too little RAM")
public class AESSolverTestCase {

    private SlpProblem problem;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        boolean[][] a = new boolean[][] {
                { false, true, true, false, false, false, false, true },
                { true, true, true, false, false, false, false, true },
                { true, true, true, false, false, true, true, true },
                { false, true, true, true, false, false, false, true },
                { false, true, true, false, false, false, true, true },
                { true, false, false, true, true, false, true, true },
                { false, true, false, false, true, true, true, true },
                { true, false, false, false, false, true, false, false },
                { true, false, false, true, false, false, false, false },
                { true, true, true, true, true, false, true, false },
                { false, true, false, false, true, true, true, false },
                { true, false, false, true, false, true, true, false },
                { true, false, false, false, false, false, true, false },
                { false, false, false, true, false, true, false, false },
                { true, false, false, true, true, false, true, false },
                { false, false, true, false, true, true, true, false },
                { true, false, true, true, false, true, false, false },
                { true, false, true, false, true, true, true, false },
                { false, true, true, true, true, true, true, false },
                { true, true, false, true, true, true, true, false },
                { true, false, true, false, true, true, false, false } };
        this.problem = new SlpProblem(23, a);
    }
    
    /**
     * Test if we can solve this for the AES problem in the paper
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.SlpProblem#getSolution()}.
     */
    @Test
    public void testGetSolution() throws Exception {
        problem.getSolution();
    }
    
    @Test
    public void testGetSolutionUntuned() throws Exception {
        problem.getSolution(false);
    }

}
