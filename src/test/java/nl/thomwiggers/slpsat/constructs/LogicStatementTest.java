/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat.constructs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.GateTranslator;

/**
 * @author Thom Wiggers
 *
 */
public class LogicStatementTest {

    /**
     * The {@link GateTranslator} to use
     */
    private GateTranslator translator;

    /**
     * set up a {@Link GateTranslator}
     */
    @Before
    public void setUp() {
        this.translator = new GateTranslator(SolverFactory.newLight());
    }

    /**
     * Test A & B
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}.
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslator1() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new And(new Variable("a"), new Variable(
                "b")));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}.
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslator2() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Or(new Variable("a"), new Variable(
                "b")));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}.
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslator3() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Not(new Variable("b")));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test a <=> b solvable
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)} .
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslator4() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Equivalent(new Variable("a"),
                new Variable("b")));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test False <=> False
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}.
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslator5() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Equivalent(new False(), new False()));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test implies
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslator6() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;

        statement = new LogicStatement(new Implies(new False(), new False()));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());

        statement = new LogicStatement(new Implies(new False(), new True()));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test implies
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslatorImpliesFalse()
            throws ContradictionException, TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Not(new Implies(new True(),
                new False())));
        statement.addToGateTranslator(this.translator);
        Assert.assertTrue(this.translator.getSolvingEngine().isSatisfiable());
    }

    /**
     * Test implies
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}
     *
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslatorImpliesFalseRaises()
            throws TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Implies(new True(), new False()));
        boolean raised = false;
        try {
            statement.addToGateTranslator(this.translator);
            Assert.assertFalse(this.translator.getSolvingEngine()
                    .isSatisfiable());
        } catch (ContradictionException e) {
            raised = true;
        }
        if (!raised) {
            Assert.fail("No exception thrown!");
        }
    }

    /**
     * Test Not True
     *
     * Test method for
     * {@link LogicStatement#addToGateTranslator(GateTranslator)}.
     *
     * @throws ContradictionException
     * @throws TimeoutException
     */
    @Test
    public void testAddToGateTranslatorNotTrue() throws ContradictionException,
            TimeoutException {
        LogicStatement statement;
        statement = new LogicStatement(new Not(new Not(new True())));
        statement.addToGateTranslator(this.translator);
        boolean satisfiable = this.translator.getSolvingEngine()
                .isSatisfiable();
        Assert.assertTrue(satisfiable);
    }

}
