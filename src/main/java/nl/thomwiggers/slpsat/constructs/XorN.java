/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * Xor of N vars
 *
 * @author Thom Wiggers
 *
 */
public class XorN extends Variable {

    /**
     * The variables
     */
    private Variable[] vars;

    /**
     * XOR between all vars
     *
     * @param y index
     * @param vars
     */
    public XorN(int y, Variable[] vars) {
        super(y, "XorN");
        this.vars = vars;
    }

    /**
     * XOR between all vars
     *
     * @param vars
     */
    public XorN(Variable[] vars) {
        super("XorN");
        this.vars = vars;
    }

    /*
     * (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#
     * addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;
        VecInt literals = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            literals.push(var.getIndex());
        }

        this.vars = null;
        translator.xor(this.getIndex(), literals);

    }

}
