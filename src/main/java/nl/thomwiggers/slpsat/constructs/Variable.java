/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * Generic variable without an assigned value
 *
 * @author Thom Wiggers
 *
 */
public class Variable {

    /**
     * Next available index
     */
    private static int nextFreeIndex = 10;

    /**
     * @return the next free index
     */
    static int getNextFreeIndex() {
        if ((Variable.nextFreeIndex - 10) % 1000 == 0) {
            System.out.println("I've now seen " + Variable.nextFreeIndex
                    + " elements");
        }
        return Variable.nextFreeIndex++;
    }

    /**
     * Have we added this variable to the gate translator yet?
     */
    protected boolean addedToGateTranslator = false;

    /**
     * Variable index in solver
     */
    private final int index;

    /**
     * Variable name
     */
    private final String name;

    /**
     * @param y index
     * @param name name of this var
     */
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

    /**
     * add constraints to translator
     *
     * @param translator
     * @throws ContradictionException
     */
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
    }

    /**
     * Find the valuation of this var in the model
     *
     * @param model to find the valuation in
     * @return the valuation
     */
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
     * @return the index of this object
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @return the name of this object
     */
    public String getName() {
        return this.name;
    }
}
