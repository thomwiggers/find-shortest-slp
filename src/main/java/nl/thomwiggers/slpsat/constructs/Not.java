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
public class Not extends AbstractLogicConstruct {

    private final Variable p;

    /**
     * @param y
     */
    public Not(int y, Variable p) {
        super(y);
        this.p = p;
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     */
    public Not(Variable p) {
        super();
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
        translator.not(getIndex(), this.p.getIndex());
    }

}
