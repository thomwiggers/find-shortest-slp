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
public class AndN extends Variable {

    protected final Variable[] variables;
    
    /**
     * @param y
     */
    public AndN(int y, Variable[] vars) {
        super(y, "andN");
        this.variables = vars;
    }

    /**
     * 
     */
    public AndN(Variable[] vars) {
        super("andN");
        this.variables = vars;
    }

    /* (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        VecInt literals = new VecInt();
        for (Variable var : this.variables) {
            var.addToGateTranslator(translator);
            literals.push(var.getIndex());
        }
        translator.and(this.getIndex(), literals);
    }

}
