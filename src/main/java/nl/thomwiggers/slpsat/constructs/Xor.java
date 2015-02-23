/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

/**
 * @author Thom Wiggers
 *
 */
public class Xor extends XorN {

    /**
     * @param y
     * @param vars
     */
    public Xor(int y, Variable p, Variable q) {
        super(y, new Variable[] {p, q});
    }

    /**
     * @param vars
     */
    public Xor(Variable p, Variable q) {
        super(new Variable[] {p, q});
    }

}
