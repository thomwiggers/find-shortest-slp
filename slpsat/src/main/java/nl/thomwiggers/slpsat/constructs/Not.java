/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * ~p
 *
 * @author Thom Wiggers
 *
 */
public class Not extends Variable {

    /**
     * var to negate
     */
    private Variable p;

    /**
     * ~p
     *
     * @param y index
     * @param p
     */
    public Not(int y, Variable p) {
        super(y, "not");
        this.p = p;
        // TODO Auto-generated constructor stub
    }

    /**
     * ~p
     *
     * @param p
     */
    public Not(Variable p) {
        super("not");
        this.p = p;
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
        translator.not(this.getIndex(), this.p.getIndex());
        this.p = null;
    }

}
