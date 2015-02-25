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

/**
 * The SLP problem as described by Fuhs and Schneider-Kamp in
 * Synthesizing Shortest Linear Straight-Line Programs over GF(2) using
 * SAT.
 *
 * @author Thom Wiggers
 *
 */
public class SlpProblem {

    private Variable[][] A;
    private int k;
    private int n;
    private int m;
    private Variable[][] B;
    private Variable[][] f;
    private Variable[][] C;

    /**
     * Initializes the problem
     *
     * @param k the amount of lines expected
     * @param n the number of input variables
     * @param m the number of output variables
     * @param A the m×n matrix that represents the program
     */
    public SlpProblem(int k, int n, int m, boolean[][] A) {
        this.A = new Variable[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
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

    private Variable psi(int j, int i) {
        Variable[] xorn = new Variable[i];
        for (int p = 0; p < i; p++) {
            xorn[p] = new And(this.C[i][p], psi(j, p));
        }
        return new Xor(this.B[i][j], new XorN(xorn));

    }

    private Variable delta3(int l) {
        Variable[] andimps = new Variable[k];

        for (int i = 0; i < k; i++) {
            Variable[] andequivs = new Variable[n];
            for (int j = 0; j < n; j++) {
                andequivs[j] = new Equivalent(psi(j, i), this.A[l][i]);
            }
            andimps[i] = new Implies(this.f[l][i], new AndN(andequivs));
        }
        return new And(new AndN(andimps), new ExactlyN(1, this.f[l]));
    }

    private Variable beta1() {
        Variable[] andn = new Variable[k];
        for (int i = 0; i < k; i++) {
            List<Variable> vars = new LinkedList<Variable>();
            vars.addAll(Arrays.asList(B[i]));
            vars.addAll(Arrays.asList(C[i]));
            andn[i] = new ExactlyN(2, vars.toArray(new Variable[] {}));
        }

        return new AndN(andn);
    }

    private Variable delta() {
        Variable[] andn = new Variable[m];
        for (int l = 0; l < m; l++) {
            andn[l] = delta3(l);
        }
        return new And(beta1(), new AndN(andn));
    }

    public LogicStatement getProblem() {
        return new LogicStatement(delta());
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
        Variable[] ands1 = new Variable[k];
        for (int i = 0; i < k; i++) {
            Variable[] ands2 = new Variable[i];
            for (int p = 0; p < i; p++) {
                Variable[] ors1 = new Variable[n];
                for (int j = 0; j < n; j++) {
                    ors1[j] = new Xor(psi(j, p), psi(j, i));
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
        Variable[] ands = new Variable[k];
        for (int i = 0; i < k; i++) {
            Variable[] vars = new Variable[n];
            for (int j = 0; j < n; j++) {
                vars[j] = psi(j, i);
            }
            ands[i] = new AtLeastN(2, vars);
        }
        return new AndN(ands);
    }
}
