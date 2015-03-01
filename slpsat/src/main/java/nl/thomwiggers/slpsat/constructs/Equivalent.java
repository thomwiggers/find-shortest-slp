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
public class Equivalent extends Variable {

    private Variable q;
    private Variable p;

    /**
     * 
     */
    public Equivalent(Variable p, Variable q) {
        super("equiv");
        this.p = p;
        this.q = q;
    }

    /* (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.AbstractLogicConstruct#addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        p.addToGateTranslator(translator);
        q.addToGateTranslator(translator);
        translator.iff(getIndex(), new VecInt(new int[] {p.getIndex(), q.getIndex()}));
    }

}
