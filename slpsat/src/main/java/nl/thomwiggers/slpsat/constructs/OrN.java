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
public class OrN extends AbstractLogicConstruct {

    protected final Variable[] vars;

    /**
     * @param y
     */
    public OrN(int y, Variable[] vars) {
        super(y);
        this.vars = vars;
    }

    /**
     * 
     */
    public OrN(Variable[] vars) {
        this.vars = vars;
    }

    /*
     * (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#
     * addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        VecInt lits = new VecInt();
        for (Variable var : vars) {
            var.addToGateTranslator(translator);
            lits.push(var.getIndex());
        }
        translator.or(getIndex(), lits);

    }

}
