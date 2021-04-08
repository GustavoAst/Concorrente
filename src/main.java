
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ast
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create a NeedlemanWunsch object
        // Strand1 = CGUCC 
        // Strand2 = GCCC
        // Match = 5
        // Mismatch = -3
        // Indel = -5
        // Allow mismatching
        NeedlemanWunsch n1 = new NeedlemanWunsch("AATACT", "ATTCT", 1, -1, -1, true);
//        NeedlemanWunsch n1 = new NeedlemanWunsch("AATACT", "ATTCT", 1, -1, -1, true);
        // Print out the information
        n1.printStrandInfo();
        System.out.println(Arrays.deepToString(n1.getSolution()));

        // Strand1 = CGUCC
        // Strand2 = GCCC
        // Do notn allow mismatching
        NeedlemanWunsch n2 = new NeedlemanWunsch("CGUCC", "GCCC", false);
        n2.printStrandInfo();
    }

}
