/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

/**
 * p XOR q
 *
 * @author Thom Wiggers
 *
 */
public class Xor extends XorN {

    /**
     * p XOR q
     *
     * @param y index
     * @param p
     * @param q
     */
    public Xor(int y, Variable p, Variable q) {
        super(y, new Variable[] { p, q });
    }

    /**
     * p XOR q
     *
     * @param p
     * @param q
     */
    public Xor(Variable p, Variable q) {
        super(new Variable[] { p, q });
    }

}
