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
public class LogicStatement {

    private AbstractLogicConstruct statement;

    /**
     * 
     */
    public LogicStatement(AbstractLogicConstruct statement) {
        this.statement = statement;
    }
    
    public void addToGateTranslator(GateTranslator translator) throws ContradictionException {
        statement.addToGateTranslator(translator);
        translator.gateTrue(statement.getIndex());
    }

}
