/**
 * Minimizes SLP programs using SAT solving.
 *
 * @author Thom Wiggers
 * @license GPLv3
 * @copyright 2015 Thom Wiggers
 */

package nl.thomwiggers.slpsat;

import java.util.Arrays;

import nl.thomwiggers.slpsat.constructs.LogicStatement;
import nl.thomwiggers.slpsat.constructs.Not;
import nl.thomwiggers.slpsat.constructs.Or;
import nl.thomwiggers.slpsat.constructs.Variable;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.GateTranslator;

/**
 * @author Thom Wiggers
 *
 */
public class App {

    /**
     * Main method
     *
     * @param args command line arguments
     */
    public static void main(String[] args) throws ContradictionException {
        System.out.println("SLP minimizer using SAT");
        System.out.println("Author: Thom Wiggers");
        System.out.println();

        ISolver solver = SolverFactory.newDefault();
        GateTranslator translator = new GateTranslator(solver);

        Variable a = new Variable("a");
        Variable b = new Variable("b");
        
        LogicStatement statement = new LogicStatement(new Not(new Or(a, b)));
        statement.addToGateTranslator(translator);

        try {
            if (solver.isSatisfiable()) {
                System.out.println("A = " + a.getIndex());
                System.out.println("B = " + b.getIndex());
                System.out.println(Arrays.toString(solver.model()));
            }
            
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
