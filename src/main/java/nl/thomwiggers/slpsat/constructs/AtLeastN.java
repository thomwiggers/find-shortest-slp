/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import java.util.LinkedList;
import java.util.List;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * @author Thom Wiggers
 *
 */
public class AtLeastN extends Variable {

	private int n;
	private Variable[] vars;

	/**
	 * @param y
	 *            index
	 * @param n
	 *            at least N
	 * @param vars
	 *            of these variables
	 */
	public AtLeastN(int y, int n, Variable[] vars) {
		super(y, "AtLeast" + n);
		this.n = n;
		this.vars = vars;
	}

	/**
	 * @param n
	 *            at least N
	 * @param vars
	 *            of these vars
	 */
	public AtLeastN(int n, Variable[] vars) {
		super("AtLeast" + n);
		this.n = n;
		this.vars = vars;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see nl.thomwiggers.slpsat.constructs.Variable#addToGateTranslator
	 * (org.sat4j.tools.GateTranslator)
	 */
	@Override
	protected void addToGateTranslator(GateTranslator translator)
			throws ContradictionException {
		if (this.addedToGateTranslator)
			return;
		this.addedToGateTranslator = true;

		List<Variable> andn = new LinkedList<Variable>();
		Variable[][] s = new Variable[this.vars.length][this.n];
		for (int i = 0; i < this.vars.length; i++) {
			for (int j = 0; j < this.n; j++) {
				s[i][j] = new Variable("sums");
			}
		}
		andn.add(new Or(new Not(vars[0]), s[0][0]));
		for (int j = 0; j < this.n - 1; j++) {
			andn.add(new Not(s[0][j]));
		}

		for (int i = 1; i < this.vars.length; i++) {
			andn.add(new Or(new Not(vars[i]), s[i][0]));

			for (int j = 1; j < this.n - 1; j++) {
				andn.add(new OrN(new Variable[]{new Not(vars[i]), new Not(s[i-1][j-1]), s[i][j]}));
				andn.add(new Or(new Not(s[i-1][j]), s[i][j]));
			}

			andn.add(new Or(new Not(vars[i]), new Not(s[i - 1][0])));
		}
		andn.add(new Or(new Not(vars[this.vars.length - 1]), new Not(
				s[this.vars.length - 2][this.n - 1])));

		AndN op = new AndN(andn.toArray(new Variable[] {}));
		op.addToGateTranslator(translator);
		this.vars = null;
	}

}
