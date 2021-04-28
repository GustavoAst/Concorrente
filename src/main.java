
import java.util.Arrays;
import java.util.concurrent.Semaphore;

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
    public static void main(String[] args) throws InterruptedException {
        // Create a NeedlemanWunsch object
        // Strand1 = AATACT 
        // Strand2 = ATTCT
        // Match = 1
        // Mismatch = -1
        // Indel = -1
        // Allow mismatching
        // Quantidade de threads
        NeedlemanWunsch n1 = new NeedlemanWunsch("AATACT", "ATTCT", 1, -1, -1, true, 5);
//        NeedlemanWunsch n1 = new NeedlemanWunsch("AATACT", "ATTCT", 1, -1, -1, true);
//        Semaphore[][] mutex = new Semaphore[10][10]; // cria uma matriz nulla de semaphoros
//        mutex[0][0] = new Semaphore(0);              // cria um objeto do semaforo com 0 permissões
//        mutex[1][0] = new Semaphore(1);             // cria um objeto do semaforo com 1 permissão
//        mutex[1][0].acquire(); // diminui 1 no valor do mutex, deixando com 0 e bloqueando o semaforo para outras threads
//        mutex[1][0].release(); // aumenta 1 no valor do mutex, deixando com 1 e liberando o semaforo para outras threads
        
        // Print out the information
        n1.printStrandInfo();
        
        int[][] solution = n1.getSolution();
        
        System.out.println("teste2: " + solution[solution.length - 1][solution[0].length - 1]);
//        System.out.println(Arrays.deepToString(mutex));
        System.out.println(Arrays.deepToString(n1.getSolution()));
    }

}
