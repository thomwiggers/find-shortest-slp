/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import nl.thomwiggers.slpsat.SlpProblem;
import junit.framework.TestCase;

/**
 * @author Thom Wiggers
 *
 */
public class AESSubBytesTest extends TestCase {

    private SlpProblem problem;

    /*
     * (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

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
    public void testGetSolution() throws Exception {
        problem.getSolution();
    }
    
    public void testGetSolutionUntuned() throws Exception {
        problem.getSolution(false);
    }

}
