/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * True constant
 *
 * @author Thom Wiggers
 *
 */
public class True extends Variable {

    /**
     * Empty true constant
     */
    public True() {
        super(1, "truth");
    }

    /**
     * @param name The name of this constant
     */
    public True(String name) {
        super(1, name);
    }

    /*
     * (non-Javadoc)
     * @see
     * nl.thomwiggers.slpsat.constructs.Variable#addToGateTranslator
     * (org.sat4j.tools.GateTranslator)
     */
    @Override
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;
        translator.gateTrue(this.getIndex());
    }
}
