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
public class XorN extends Variable {

    private Variable[] vars;

    /**
     * @param y
     */
    public XorN(int y, Variable[] vars) {
        super(y, "XorN");
        this.vars = vars;
    }

    /**
     *
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
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.added)
            return;
        this.added = true;
        VecInt literals = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            literals.push(var.getIndex());
        }
        translator.xor(this.getIndex(), literals);
        this.vars = null;
    }

}
