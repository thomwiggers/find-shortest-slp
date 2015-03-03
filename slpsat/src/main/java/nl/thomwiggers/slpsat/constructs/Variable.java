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
    private static int nextFreeIndex = 10;

    static int getNextFreeIndex() {
        if ((Variable.nextFreeIndex-10) % 1000 == 0) {
            System.out.println("I've now seen " + Variable.nextFreeIndex + " elements");
        }
        return Variable.nextFreeIndex++;
    }

    protected boolean added = false;

    /**
     * Variable index in solver
     */
    private final int index;

    /**
     * Variable name
     */
    private final String name;

    public Variable(int y, String name) {
        this.name = name;
        this.index = y;
    }

    /**
     * Get a new variable
     *
     * @param name The name of this variable
     */
    public Variable(String name) {
        this.name = name;
        this.index = Variable.getNextFreeIndex();
    }

    protected void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
    }

    public boolean findValuation(int[] model) {
        for (int val : model) {
            if (val == this.index)
                return true;
            else if (val == -this.index)
                return false;
        }
        throw new RuntimeException("No value for this object!");
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
}
