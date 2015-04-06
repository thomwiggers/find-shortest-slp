/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat;

import java.io.FileWriter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sat4j.specs.ISolver;

/**
 * Try to solve the AES problem from the paper
 *
 * @author Thom Wiggers
 *
 */
//@Ignore("Takes at least 32GB RAM")
public class AESSolverTestCase {

	/**
	 * The AES problem instance
	 */
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
	 * Test method for {@link SlpProblem#getSolution()}.
	 *
	 * @throws Exception
	 */
	@Ignore("RAAAM")
	@Test
	public void testGetSolution() throws Exception {
		this.problem.getSolution();
	}

	/**
	 * Try to solve the paper's AES problem without the tunings.
	 *
	 * Test method for {@link SlpProblem#getSolution()}
	 *
	 * @throws Exception
	 */
	@Ignore("Extra painful because not tuned")
	@Test
	public void testGetSolutionUntuned() throws Exception {
		this.problem.getSolution(false);
	}

	/**
	 * Try and write a dimacs version
	 *
	 * @throws Exception
	 */
	@Ignore("Still painful")
	@Test
	public void testGetDimacsAesTuned() throws Exception {
		ISolver solver = this.problem.getDimacsSolver(true);
		FileWriter f = new FileWriter("aesdimacs");
		f.write(solver.toString());
		f.close();
	}

}
