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

    private Variable[] vars;
    private int n;

    /**
     * @param y
     */
    public AtLeastN(int y, int n, Variable[] vars) {
        super(y, "AtLeast" + n);
        this.n = n;
        this.vars = vars;
    }

    /**
     * 
     */
    public AtLeastN(int n, Variable[] vars) {
        super("AtLeast" + n);
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
        translator.addAtLeast(lits, this.n);
    }

    
    
}
