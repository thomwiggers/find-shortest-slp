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
public class True extends Variable {

    /**
     * @param name
     */
    public True() {
        super("truth");
    }

    /* (non-Javadoc)
     * @see nl.thomwiggers.slpsat.constructs.Variable#addToGateTranslator(org.sat4j.tools.GateTranslator)
     */
    @Override
    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        translator.gateTrue(getIndex());
    }
}
