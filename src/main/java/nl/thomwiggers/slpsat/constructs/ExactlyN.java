/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * Exactly N must be true
 *
 * @author Thom Wiggers
 *
 */
public class ExactlyN extends Variable {

    /**
     * the degree
     */
    private int n;
    /**
     * the vars
     */
    private Variable[] vars;

    /**
     * @param y index
     * @param n the number of cases
     * @param vars the cases
     */
    public ExactlyN(int y, int n, Variable[] vars) {
        super(y, "exactly" + n);
        this.n = n;
        this.vars = vars;
    }

    /**
     * @param n the number of cases
     * @param vars the cases
     */
    public ExactlyN(int n, Variable[] vars) {
        super("exactly" + n);
        this.n = n;
        this.vars = vars;
    }

    /*
     * (non-Javadoc)
     * @see
     * nl.thomwiggers.slpsat.constructs.Variable#addToGateTranslator
     * (org.sat4j.tools.GateTranslator)
     */
    @Override
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;

        And and = new And(new AtLeastN(this.n, vars), new AtMostN(this.n, vars));
        and.addToGateTranslator(translator);
    }

}
