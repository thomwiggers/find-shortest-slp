/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * @author Thom Wiggers
 *
 */
public class Implies extends Variable {

    private Variable p;
    private Variable q;

    /**
     * @param y
     */
    public Implies(int y, Variable p, Variable q) {
        super(y, "Implies");
        this.p = p;
        this.q = q;
    }

    /**
     * 
     */
    public Implies(Variable p, Variable q) {
        super("Implies");
        this.p = p;
        this.q = q;
    }

    /* (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        Not operation = new Not(new And(this.p, new Not(this.q)));
        operation.addToGateTranslator(translator);

    }

}
