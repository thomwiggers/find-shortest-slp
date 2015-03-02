/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.GateTranslator;

import junit.framework.TestCase;

/**
 * @author Thom Wiggers
 *
 */
public class LogicStatementTest extends TestCase {

    private GateTranslator translator;

    @Override
    public void setUp() {
        this.translator = new GateTranslator(SolverFactory.newLight());
    }

    /**
     * Test A & B
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslator1() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new And(new Variable("a"), new Variable(
                "b")));
        statement.addToGateTranslator(this.translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslator2() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Or(new Variable("a"), new Variable(
                "b")));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslator3() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Not(new Variable("b")));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test a <=> b solvable
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslator4() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Equivalent(new Variable("a"),
                new Variable("b")));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test False <=> False
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslator5() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Equivalent(new False(), new False()));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test implies
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslator6() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Implies(new False(), new False()));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
        statement = new LogicStatement(new Implies(new False(), new True()));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test implies
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslatorImpliesFalse()
            throws ContradictionException, TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Not(new Implies(new True(),
                new False())));
        statement.addToGateTranslator(translator);
        assertTrue(translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test implies
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslatorImpliesFalseRaises()
            throws TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Implies(new True(), new False()));
        boolean raised = false;
        try {
            statement.addToGateTranslator(translator);
            assertFalse(translator.getSolvingEngine().isSatisfiable());
        } catch (ContradictionException e) {
            raised = true;
        }
        if (!raised)
            fail("No exception thrown!");
    }

    /**
     * Test Not True
     * 
     * Test method for
     * {@link nl.thomwiggers.slpsat.constructs.LogicStatement#addToGateTranslator(org.sat4j.tools.GateTranslator)}
     * .
     */
    public void testAddToGateTranslatorNotTrue() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Not(new Not(new True())));
        statement.addToGateTranslator(translator);
        boolean satisfiable = translator.getSolvingEngine().isSatisfiable();
        assertTrue(satisfiable);
    }
}
