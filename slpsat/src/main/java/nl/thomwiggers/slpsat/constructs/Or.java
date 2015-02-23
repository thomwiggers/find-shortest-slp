/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

/**
 * @author Thom Wiggers
 *
 */
public class Or extends OrN {

    /**
     * @param p
     * @param q
     */
    public Or(Variable p, Variable q) {
        super(new Variable[] { p, q });
    }
}
