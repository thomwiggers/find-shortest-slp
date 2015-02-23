/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

/**
 * @author Thom Wiggers
 *
 */
public abstract class AbstractLogicConstruct extends Variable {

    protected final int y;

    /**
     * @param solver The solver
     * @param y index of the condition we're adding.
     */
    protected AbstractLogicConstruct(int y) {
        super(null);
        this.y = y;
    }
    
    protected AbstractLogicConstruct() {
        super(null);
        this.y = Variable.getNextFreeIndex();
    }
}
