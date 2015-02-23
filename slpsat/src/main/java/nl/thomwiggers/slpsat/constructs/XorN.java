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
public class XorN extends AbstractLogicConstruct {

    private Variable[] vars;

    /**
     * @param y
     */
    public XorN(int y, Variable[] vars) {
        super(y);
        this.vars = vars;
    }

    /**
     * 
     */
    public XorN(Variable[] vars) {
        super();
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
        VecInt literals = new VecInt();
        for (Variable var : this.vars) {
            var.addToGateTranslator(translator);
            literals.push(var.getIndex());
        }
        translator.xor(this.getIndex(), literals);

    }

}
