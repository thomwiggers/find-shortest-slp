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
public class OrN extends Variable {

    protected Variable[] vars;

    /**
     * @param y
     */
    public OrN(int y, Variable[] vars) {
        super(y, "OrN");
        this.vars = vars;
    }

    /**
     *
     */
    public OrN(Variable[] vars) {
        super("OrN");
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
        if (this.added)
            return;
        this.added = true;

        VecInt lits = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            lits.push(var.getIndex());
        }
        translator.or(this.getIndex(), lits);
        this.vars = null;

    }

}
