/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * False constant
 *
 * @author Thom Wiggers
 *
 */
public class False extends Variable {

    /**
     * Empty true variable
     */
    public False() {
        super(2, "falsehood");
    }

    /**
     * Named false constant
     *
     * @param name the name of this constant
     */
    public False(String name) {
        super(2, name);
    }

    /*
     * (non-Javadoc)
     * @see
     * nl.thomwiggers.slpsat.constructs.True#addToGateTranslator(org
     * .sat4j.tools.GateTranslator)
     */
    @Override
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        if (this.addedToGateTranslator)
            return;
        this.addedToGateTranslator = true;
        translator.gateFalse(this.getIndex());
    }

}
