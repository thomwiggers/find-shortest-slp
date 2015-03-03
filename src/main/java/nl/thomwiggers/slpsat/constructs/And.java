/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

/**
 * @author Thom Wiggers
 *
 */
public class And extends AndN {

    /**
     * @param p
     * @param q
     */
    public And(int y, Variable p, Variable q) {
        super(y, new Variable[] { p, q });
    }

    /**
     * @param p
     * @param q
     */
    public And(Variable p, Variable q) {
        super(new Variable[] { p, q });
    }

}
