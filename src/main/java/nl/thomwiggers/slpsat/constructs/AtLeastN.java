/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.core.VecInt;
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
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;
        VecInt lits = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            lits.push(var.getIndex());
        }
        this.vars = null;
        translator.addAtLeast(lits, this.n);
    }

}
