/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * Logical implication (p -> q)
 *
 * @author Thom Wiggers
 *
 */
public class Implies extends Variable {

    private Variable p;
    private Variable q;

    /**
     * Construct a new implication (p -> q)
     *
     * @param y index
     * @param p
     * @param q
     */
    public Implies(int y, Variable p, Variable q) {
        super(y, "Implies");
        this.p = p;
        this.q = q;
    }

    /**
     * p -> q
     *
     * @param p
     * @param q
     */
    public Implies(Variable p, Variable q) {
        super("Implies");
        this.p = p;
        this.q = q;
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
        this.p.addToGateTranslator(translator);
        this.q.addToGateTranslator(translator);
        True z = new True();
        z.addToGateTranslator(translator);
        translator.ite(this.getIndex(), this.p.getIndex(), this.q.getIndex(),
                z.getIndex());
        // Not operation = new Not(new And(this.p, new Not(this.q)));
        // operation.addToGateTranslator(translator);
        this.p = this.q = null;

    }

}
