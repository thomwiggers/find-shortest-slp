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
public class Equivalent extends AbstractLogicConstruct {

    private Variable q;
    private Variable p;

    /**
     * @param y
     */
    public Equivalent(int y, Variable p, Variable q) {
        super(y);
        this.p = p;
        this.q = q;
    }

    /**
     * 
     */
    public Equivalent(Variable p, Variable q) {
        this.p = p;
        this.q = q;
    }

    /* (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        And operation = new And(new Implies(this.p, this.q), new Implies(this.q, this.p));
        operation.addToGateTranslator(translator);
    }

}
