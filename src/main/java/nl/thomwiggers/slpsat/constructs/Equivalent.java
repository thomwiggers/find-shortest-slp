/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * p <-> q
 *
 * @author Thom Wiggers
 *
 */
public class Equivalent extends Variable {

    /**
     * left-hand side
     */
    private Variable p;

    /**
     * right-hand side
     */
    private Variable q;

    /**
     * p <=> q
     *
     * @param p
     * @param q
     */
    public Equivalent(Variable p, Variable q) {
        super("equiv");
        this.p = p;
        this.q = q;
    }

    /*
     * (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#
     * addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;
        this.p.addToGateTranslator(translator);
        this.q.addToGateTranslator(translator);

        translator.iff(this.getIndex(),
                new VecInt(new int[] { this.p.getIndex(), this.q.getIndex() }));

        this.p = this.q = null;
    }

}
