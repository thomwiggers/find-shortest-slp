/**
 * Minimizes SLP programs using SAT solving.
 *
 * @author Thom Wiggers
 * @license GPLv3
 * @copyright 2015 Thom Wiggers
 */

package nl.thomwiggers.slpsat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

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
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("SLP minimizer using SAT");
        System.out.println("Author: Thom Wiggers");
        System.out.println();

        // http://stackoverflow.com/a/6849255
        String path = App.class.getProtectionDomain().getCodeSource()
                .getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8");
        decodedPath = decodedPath.substring(decodedPath.lastIndexOf('/')+1, decodedPath.length());

        int minK = 0, k = 100;

        if (args.length == 0 || args.length > 2) {
            System.err.println("\nProvide the number of lines to solve the SLP "
                    + "problem for on the command line");
            System.err.println("Like:\n\t" + decodedPath + " 6\n");
            System.err.println("Alternatively, state the range of k for which "
                    + "to try and solve the problem. (From high to low)");
            System.err.println("Like:\n\t" + decodedPath + " 10 6\n");
            System.exit(1);
        } else if (args.length == 1) {
            minK = k = Integer.parseInt(args[0]);
        } else {
            k = Integer.parseInt(args[0]);
            minK = Integer.parseInt(args[1]);
        }

        boolean[][] problem = readProblem(new Scanner(System.in));

        if (problem == null) {
            System.err.println("No input problem provided on standard input.");
            System.err.println("Input the problem line by line, as a matrix");
            System.err.println("For example:");
            System.err.println("1 1 1 1 1");
            System.err.println("1 1 1 1 0");
            System.err.println("1 1 1 0 1");
            System.err.println("0 0 1 1 1");
            System.err.println("1 0 0 0 1\n");
            System.err.println("End with an empty line.");
            System.exit(1);
        }

        System.out.println("Read problem");

        for (; k >= minK; k--) {
            System.out.println("Solving for K = " + k + " lines…");

            SlpProblem p = new SlpProblem(k, problem);
            SlpProblem.Solution sol = null;
            try {
                sol = p.getSolution();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (sol == null) {
                System.out.println("Unfortunately, there exists no "
                        + "solution for your problem at k = " + k);
            } else {
                System.out.println(sol.toString());
            }
        }
    }

    private static boolean[][] readProblem(Scanner reader) {
        List<List<Boolean>> list = new LinkedList<>();

        int n = -1;

        try {
            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) {
                    break;
                }
                String[] parts = line.split("\\s");
                LinkedList<Boolean> linelist = new LinkedList<Boolean>();
                for (String part : parts) {
                    if (part.equals("1") && part.equals("0")) {
                        throw new IOException("Invalid input format!");
                    }
                    linelist.add(part.equals("1"));
                }
                if (n == -1) {
                    n = linelist.size();
                } else {
                    if (n != linelist.size()) {
                        throw new IOException(
                                "Invalid number of input variables in this line!");
                    }
                }
                list.add(linelist);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Error while reading, aborting.");
            System.exit(2);
        }

        if (n == -1) {
            return null;
        }

        boolean[][] returnList = new boolean[list.size()][n];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < n; j++)
                returnList[i][j] = list.get(i).get(j);
        }

        return returnList;
    }
}
