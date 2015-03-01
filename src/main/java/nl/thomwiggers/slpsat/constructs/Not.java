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
public class Not extends Variable {

    private final Variable p;

    /**
     * @param y
     */
    public Not(int y, Variable p) {
        super(y, "not");
        this.p = p;
        // TODO Auto-generated constructor stub
    }

    /**
     * 
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
        p.addToGateTranslator(translator);
        translator.not(getIndex(), p.getIndex());
    }

}
