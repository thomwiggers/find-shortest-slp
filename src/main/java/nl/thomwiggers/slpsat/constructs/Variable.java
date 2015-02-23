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
public class Variable {

    /**
     * Next available index
     */
    private static int nextFreeIndex = 1;

    static int getNextFreeIndex() {
        return Variable.nextFreeIndex++;
    }

    /**
     * Variable index in solver
     */
    private final int index;

    /**
     * Variable name
     */
    private final String name;

    /**
     * Get a new variable
     * 
     * @param name The name of this variable
     */
    public Variable(String name) {
        this.name = name;
        this.index = Variable.getNextFreeIndex();
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    protected void addToGateTranslator(GateTranslator translator) throws ContradictionException {
    }
    
}
