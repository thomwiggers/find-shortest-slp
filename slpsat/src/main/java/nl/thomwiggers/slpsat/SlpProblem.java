/**
 * @copyright 2015 Thom Wiggers
 * @license GPLv3
 */
package nl.thomwiggers.slpsat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import nl.thomwiggers.slpsat.constructs.And;
import nl.thomwiggers.slpsat.constructs.AndN;
import nl.thomwiggers.slpsat.constructs.AtLeastN;
import nl.thomwiggers.slpsat.constructs.AtMostN;
import nl.thomwiggers.slpsat.constructs.Equivalent;
import nl.thomwiggers.slpsat.constructs.ExactlyN;
import nl.thomwiggers.slpsat.constructs.False;
import nl.thomwiggers.slpsat.constructs.Implies;
import nl.thomwiggers.slpsat.constructs.LogicStatement;
import nl.thomwiggers.slpsat.constructs.OrN;
import nl.thomwiggers.slpsat.constructs.True;
import nl.thomwiggers.slpsat.constructs.Variable;
import nl.thomwiggers.slpsat.constructs.Xor;
import nl.thomwiggers.slpsat.constructs.XorN;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.tools.GateTranslator;

/**
 * The SLP problem as described by Fuhs and Schneider-Kamp in
 * Synthesizing Shortest Linear Straight-Line Programs over GF(2) using
 * SAT.
 *
 * @author Thom Wiggers
 *
 */
public class SlpProblem {

    public class Solution {

        public final boolean[][] B;
        public final boolean[][] C;
        public final boolean[][] f;
        public final int k;
        public final int m;
        public final int n;

        private Solution(boolean[][] B, boolean[][] C, boolean[][] f) {
            this.B = B;
            this.C = C;
            this.f = f;
            this.n = SlpProblem.this.n;
            this.k = SlpProblem.this.k;
            this.m = SlpProblem.this.m;
        }

        private void appendArrayToStringBuilder(boolean[][] a, StringBuilder sb) {
            sb.append(" = [\n");
            for (boolean[] row : a) {
                sb.append("\t[");
                for (boolean var : row) {
                    sb.append(var ? 1 : 0).append(", ");
                }
                sb.delete(sb.lastIndexOf(","), sb.length()).append("],\n");
            }
            sb.append("]\n");
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("B");
            appendArrayToStringBuilder(B, sb);
            sb.append("C");
            appendArrayToStringBuilder(C, sb);
            sb.append("f");
            appendArrayToStringBuilder(f, sb);
            return sb.toString();
        }
    }

    private Variable[][] A;
    private Variable[][] B;
    private Variable[][] C;
    private Variable[][] f;
    private int k;
    private int m;
    private int n;
    private ISolver solver;

    /**
     * Initializes the problem
     *
     * @param k the amount of lines expected
     * @param n the number of input variables
     * @param m the number of output variables
     * @param A the m×n matrix that represents the program
     */
    public SlpProblem(int k, boolean[][] A) {
        int m = A.length;
        int n = A[0].length;
        this.A = new Variable[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String name = "a_{" + i + ", " + j + "}";
                if (A[i][j]) {
                    this.A[i][j] = new True(name);
                } else {
                    this.A[i][j] = new False(name);
                }

            }
        }
        this.k = k;
        this.n = n;
        this.m = m;
        this.B = new Variable[k][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                this.B[i][j] = new Variable("b_{" + i + ", " + j + "}");
            }
        }
        this.C = new Variable[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                this.C[i][j] = new Variable("c_{" + i + ", " + j + "}");
            }
        }
        this.f = new Variable[m][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < k; j++) {
                this.f[i][j] = new Variable("f_{" + i + ", " + j + "}");
            }
        }
    }

    private Variable beta1() {
        Variable[] andn = new Variable[this.k];
        for (int i = 0; i < this.k; i++) {
            List<Variable> vars = new LinkedList<Variable>();
            vars.addAll(Arrays.asList(this.B[i]));
            vars.addAll(Arrays.asList(this.C[i]));
            andn[i] = new ExactlyN(2, vars.toArray(new Variable[] {}));
        }

        return new AndN(andn);
    }

    private Variable delta() {
        Variable[] andn = new Variable[this.m];
        for (int l = 0; l < this.m; l++) {
            andn[l] = this.delta3(l);
        }
        return new And(this.beta1(), new AndN(andn));
    }

    private Variable delta3(int l) {
        Variable[] andimps = new Variable[this.k];

        for (int i = 0; i < this.k; i++) {
            Variable[] andequivs = new Variable[this.n];
            for (int j = 0; j < this.n; j++) {
                andequivs[j] = new Equivalent(this.psi(j, i), this.A[l][j]);
            }
            andimps[i] = new Implies(this.f[l][i], new AndN(andequivs));
        }
        return new And(new AndN(andimps), new ExactlyN(1, this.f[l]));
    }

    public LogicStatement getProblem() {
        return new LogicStatement(this.delta());
    }

    public Solution getSolution() throws Exception {
        return this.getSolution(true);
    }

    public Solution getSolution(boolean tunings) throws Exception {
        IProblem problem = this.getSolvableProblem(tunings);
        if (!problem.isSatisfiable())
            return null;

        int[] model = problem.findModel();

        boolean[][] solB = new boolean[this.k][this.n];
        boolean[][] solC = new boolean[this.k][this.k];
        boolean[][] solF = new boolean[this.m][this.k];

        for (int i = 0; i < this.k; i++) {
            for (int j = 0; j < this.n; j++) {
                solB[i][j] = this.B[i][j].findValuation(model);
            }
        }

        for (int i = 0; i < this.k; i++) {
            for (int j = 0; j < this.k; j++) {
                solC[i][j] = this.C[i][j].findValuation(model);
            }
        }

        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.k; j++) {
                solF[i][j] = this.f[i][j].findValuation(model);
            }
        }

        Solution solution = new Solution(solB, solC, solF);
        return solution;
    }

    public ISolver getSolvableProblem() throws ContradictionException {
        return this.getSolvableProblem(true);
    }

    public ISolver getSolvableProblem(boolean tunings)
            throws ContradictionException {
        if (this.solver != null)
            return this.solver;
        this.solver = SolverFactory.newDefault();
        GateTranslator translator = new GateTranslator(this.solver);

        this.getProblem().addToGateTranslator(translator);

        if (tunings) {
            this.getTunings().addToGateTranslator(translator);
        }

        return this.solver;
    }

    /**
     * The first tuning described in the paper
     *
     * AND_{0 < i < k} AND_{0 < p < i} OR_{0 < j < n} ( psi(j,i) XOR
     * psi(j,p) )
     *
     * @return the first tuning
     */
    private Variable getTuning1() {
        Variable[] ands1 = new Variable[this.k];
        for (int i = 0; i < this.k; i++) {
            Variable[] ands2 = new Variable[i];
            for (int p = 0; p < i; p++) {
                Variable[] ors1 = new Variable[this.n];
                for (int j = 0; j < this.n; j++) {
                    ors1[j] = new Xor(this.psi(j, p), this.psi(j, i));
                }
                ands2[p] = new OrN(ors1);
            }
            ands1[i] = new AndN(ands2);
        }
        return new AndN(ands1);
    }

    /**
     * The second tuning in the paper
     *
     * AND_{0 < i < k} atLeast2(psi(0,i) ... psi(n, i))
     *
     * @return
     */
    private Variable getTuning2() {
        Variable[] ands = new Variable[this.k];
        for (int i = 0; i < this.k; i++) {
            Variable[] vars = new Variable[this.n];
            for (int j = 0; j < this.n; j++) {
                vars[j] = this.psi(j, i);
            }
            ands[i] = new AtLeastN(2, vars);
        }
        return new AndN(ands);
    }

    /* Need to figure out what i, j are
    private Variable getTuning3() {

    }*/

    private Variable getTuning4() {
        Variable[] andn = new Variable[k];
        for (int i = 0; i < k; i++) {
            Variable[] fs = new Variable[m];
            for (int j = 0; j < m; j++) {
                fs[j] = this.f[j][i];
            }
            andn[i] = new AtMostN(k, fs);
        }
        return new AndN(andn);
    }

    private Variable getTuning5() {
        List<Variable> vars = new LinkedList<Variable>();
        for (int i = 0; i < m; i++) {
            vars.addAll(Arrays.asList(f[i]));
        }
        return new AtMostN(k, vars.toArray(new Variable[]{}));
    }

    private Variable getTuning6() {
        List<Variable> vars = new LinkedList<Variable>();
        for (int i = 0; i < m; i++) {
            vars.addAll(Arrays.asList(f[i]));
        }
        return new AtLeastN(m, vars.toArray(new Variable[]{}));
    }

    public LogicStatement getTunings() {
        return new LogicStatement(new AndN(new Variable[] { this.getTuning1(),
                this.getTuning2(), getTuning4(), getTuning5(), getTuning6() }));
    }

    private Variable psi(int j, int i) {
        Variable[] xorn = new Variable[i];
        for (int p = 0; p < i; p++) {
            xorn[p] = new And(this.C[i][p], this.psi(j, p));
        }
        return new Xor(this.B[i][j], new XorN(xorn));

    }
}
