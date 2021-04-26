
import java.lang.Math;
import java.lang.Thread;

/**
 *
 * @author Ast
 */
public class NeedlemanWunsch {

    // Scoring scheme for match, mismatch, and gap
    private final int MATCH;
    private final int MISMATCH;
    private final int GAP;

    // Strands to be analyzed
    private String string1;
    private String string2;

    // Validity of mismatching
    private boolean allowMismatch;

    // Solution matrix, score, and alignedStrings to be calculated
    private int[][] solution;
    private int score;
    private int threads;
    private String[] alignedStrands;

    /**
     * Constructor taking in two strands. Default values: MATCH = 1 MISMATCH =
     * -1 GAP = -1 allowMismatch = true
     *
     * @param string1 the first strand
     * @param string2 the second strand
     */
    public NeedlemanWunsch(String string1, String string2) {
        this(string1, string2, 1, -1, -1, true);
    }

    /**
     * Constructor taking in two strands and whether mismatching is allowed.
     * Default values: MATCH = 1 MISMATCH = -1 / -999 GAP = -1
     *
     * @param string1 the first strand
     * @param string2 the second strand
     * @param allowMismatch whether mismatching is allowed
     */
    public NeedlemanWunsch(String string1, String string2, boolean allowMismatch) {
        this(string1, string2, 1, allowMismatch ? -1 : -999, -1, allowMismatch);
    }

    /**
     * Constructor taking in two strands and the scoring system. Default values:
     * allowedMismatch = true
     *
     * @param string1 the first strand
     * @param string2 the second strand
     * @param match the value of a match
     * @param mismatch the value of a mismatch
     * @param gap the value of an gap
     */
    public NeedlemanWunsch(String string1, String string2, int match, int mismatch, int gap) {
        this(string1, string2, match, mismatch, gap, true);
    }

    /**
     * Constructor taking in two strands and the scoring system and whether
     * mismatching is allowed. Calculates the solution matrix, the end score,
     * and the aligned strands.
     *
     * @param string1 the first strand
     * @param string2 the second strand
     * @param match the value of a match
     * @param mismatch the value of a mismatch
     * @param gap the value of an gap
     * @param allowMismatch whether mismatching is allowed
     */
    public NeedlemanWunsch(String string1, String string2, int match, int mismatch, int gap, boolean allowMismatch) {
        this.string1 = string1;
        this.string2 = string2;

        this.MATCH = match;
        this.MISMATCH = mismatch;
        this.GAP = gap;

        this.allowMismatch = allowMismatch;

        // Calculate solution matrix
        this.solution = findSolution();
        // Calculate score
        this.score = solution[solution.length - 1][solution[0].length - 1];
    }

    /**
     * Constructor taking in two strands and the scoring system and whether
     * mismatching is allowed. Calculates the solution matrix, the end score,
     * and the aligned strands.
     *
     * @param string1 the first strand
     * @param string2 the second strand
     * @param match the value of a match
     * @param mismatch the value of a mismatch
     * @param gap the value of an gap
     * @param allowMismatch whether mismatching is allowed
     * @param threads number of threads
     */
    public NeedlemanWunsch(String string1, String string2, int match, int mismatch, int gap, boolean allowMismatch, int threads) {
        this.string1 = string1;
        this.string2 = string2;

        this.MATCH = match;
        this.MISMATCH = mismatch;
        this.GAP = gap;

        this.allowMismatch = allowMismatch;

        this.threads = threads;

        // Calculate solution matrix
        this.solution = findSolutionWithThreads();
        // Calculate score
        this.score = solution[solution.length - 1][solution[0].length - 1];
    }

    /**
     * Generates solution matrix given 2 RNA strands. Uses the Needleman-Wunsch
     * algorithm.
     *
     * @return the solution matrix
     */
    public int[][] findSolution() {
        // Generate solution matrix based on lengths of both strands
        // Let string1 be the side strand
        // Let string2 be the top strand
        int[][] solution = new int[string1.length() + 1][string2.length() + 1];

        // Set the starting point to value of 0
        solution[0][0] = 0;

        // Fill in the top row. Moving to the right always adds the value of GAP.
        for (int i = 1; i < string2.length() + 1; i++) {
            solution[0][i] = solution[0][i - 1] + GAP;
        }

        // Fill in the left column. Moving down always adds the value of GAP.
        for (int i = 1; i < string1.length() + 1; i++) {
            solution[i][0] = solution[i - 1][0] + GAP;
        }

        // Fill in the rest of the matrix based on a few rules.
        for (int i = 1; i < string1.length() + 1; i++) {
            for (int j = 1; j < string2.length() + 1; j++) {

                int matchValue;

                // If the characters that correspond to the grid position are equal for both strands
                // Set the matchValue to MATCH, else set the matchValue to MISMATCH
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    matchValue = MATCH;
                } else {
                    matchValue = MISMATCH;
                }

                // Set the value to the maximum of these three values
                // Position to the left + GAP
                // Position above + GAP
                // Position top-left + matchVALUE
                solution[i][j] = max(solution[i][j - 1] + GAP, solution[i - 1][j] + GAP, solution[i - 1][j - 1] + matchValue);
            }
        }

        // Return solution matrix
        return solution;
    }

    public int[][] findSolutionWithThreads() {
        int[] threadIndex = new int[this.threads];
        int valor = this.string1.length() / this.threads;
        if (valor == 0) {
            valor = 1;
        }
        for (int i = 0; i < this.threads; i++) {

        }

        for (int i = 0; i < this.threads; i++) {
            new Thread() {
                @Override
                public void run() {

                }
            }.start();
        }
        // Generate solution matrix based on lengths of both strands
        // Let string1 be the side strand
        // Let string2 be the top strand
        int[][] solution = new int[string1.length() + 1][string2.length() + 1];

        // Set the starting point to value of 0
        solution[0][0] = 0;

        // inicialização das linhas e colunas iniciais
        for (int i = 1; i < string2.length() + 1; i++) {
            solution[0][i] = solution[0][i - 1] + GAP;
        }
        for (int i = 1; i < string1.length() + 1; i++) {
            solution[i][0] = solution[i - 1][0] + GAP;
        }

        // Preencher a matriz com a utilização de threads
        for (int i = 1; i < string1.length() + 1; i++) {
            for (int j = 1; j < string2.length() + 1; j++) {

                int matchValue;

                // If the characters that correspond to the grid position are equal for both strands
                // Set the matchValue to MATCH, else set the matchValue to MISMATCH
                if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
                    matchValue = MATCH;
                } else {
                    matchValue = MISMATCH;
                }

                // Set the value to the maximum of these three values
                // Position to the left + GAP
                // Position above + GAP
                // Position top-left + matchVALUE
                solution[i][j] = max(solution[i][j - 1] + GAP, solution[i - 1][j] + GAP, solution[i - 1][j - 1] + matchValue);
            }
        }

        // Return solution matrix
        return solution;
    }

    /**
     * Helper method for calculating a maximum of three numbers.
     *
     * @return the maximum of the three given integers
     */
    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    /**
     * Method that prints out the alignment information. Prints out the aligned
     * strands and alignment score.
     */
    public void printStrandInfo() {
        // Print out side strand
//        System.out.println(alignedStrands[0]);
        // Print out top strand
//        System.out.println(alignedStrands[1]);
        // Print out strand score
        System.out.println("The score for this alignment is: " + score);
    }

    /**
     * Method to get the solution matrix.
     *
     * @return the solution matrix
     */
    public int[][] getSolution() {
        return solution;
    }

    /**
     * Method to get the final score.
     *
     * @return the final score
     */
    public int getScore() {
        return score;
    }

    /**
     * Method to return the aligned Strands.
     *
     * @return the aligned strands
     */
    public String[] getAlignedStrands() {
        return alignedStrands;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
}
