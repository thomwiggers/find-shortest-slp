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
public class AtMostN extends Variable {

    private Variable[] vars;
    private int n;

    /**
     * @param y
     */
    public AtMostN(int y, int n, Variable[] vars) {
        super(y, "AtMost" + n);
        this.n = n;
        this.vars = vars;
    }

    /**
     * 
     */
    public AtMostN(int n, Variable[] vars) {
        super("AtMost" + n);
        this.n = n;
        this.vars = vars;
    }

    /* (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.Variable#addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        VecInt lits = new VecInt();        
        for (Variable var : vars) {
            var.addToGateTranslator(translator);
            lits.push(var.getIndex());
        }
        translator.addAtMost(lits, this.n);
    }

    
    
}
