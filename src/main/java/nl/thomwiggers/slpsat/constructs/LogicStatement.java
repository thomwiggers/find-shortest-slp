/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.specs.ContradictionException;
import org.sat4j.tools.GateTranslator;

/**
 * A logic statement
 *
 * @author Thom Wiggers
 *
 */
public class LogicStatement {

    /**
     * The statement
     */
    private Variable statement;

    /**
     * Construct a new logic statement
     *
     * @param statement the logic statement
     */
    public LogicStatement(Variable statement) {
        this.statement = statement;
    }

    /**
     * Add the constraints to the solver
     *
     * @param translator converts the constraints to solver input
     * @throws ContradictionException if the problem is trivially
     *             unsatisfiable.
     */
    public void addToGateTranslator(GateTranslator translator)
            throws ContradictionException {
        this.statement.addToGateTranslator(translator);
        translator.gateTrue(this.statement.getIndex());
    }

}
