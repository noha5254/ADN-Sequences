import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Sequences {
    private String[] seqs;
    private double[] probs = new double[4];
    private Map<String, Integer> patCount;

    public Sequences(int nSeqs, int minLen, int maxLen, double[] probs, int patLen) {
        this.patCount = new HashMap<>();
        this.probs = probs;
        this.seqs = new String[nSeqs];
        
        for (int i = 0; i < nSeqs; i++) {
            int seqLen = (int) (Math.random() * (maxLen - minLen + 1)) + minLen;
            this.seqs[i] = genSeq(seqLen);
        }
        
        detectPat(patLen);
    }

    private String genSeq(int len) {
        StringBuilder seq = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < len; i++) {
            double r = rnd.nextDouble();
            if (r < probs[0]) {
                seq.append('A');
            } else if (r < probs[0] + probs[1]) {
                seq.append('C');
            } else if (r < probs[0] + probs[1] + probs[2]) {
                seq.append('G');
            } else {
                seq.append('T');
            }
        }
        return seq.toString();
    }

    private void detectPat(int patLen) {
        for (String seq : seqs) {
            for (int i = 0; i <= seq.length() - patLen; i++) {
                String pat = seq.substring(i, i + patLen);
                patCount.put(pat, patCount.getOrDefault(pat, 0) + 1);
            }
        }
    }

    public void printPatCount() {
        System.out.println("\n=== Pattern Count ===");
        if (patCount.isEmpty()) {
            System.out.println("No patterns found.");
        } else {
            patCount.forEach((k, v) -> 
                System.out.printf("Pattern: %-10s | Occurrences: %4d%n", k, v)
            );
        }
        System.out.println("===========================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int WIDTH = 6;

        try {
            System.out.println("\n=== Sequence Setup ===");

            System.out.print("Enter the number of sequences to generate: ");
            int nSeqs = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter the minimum length of these sequences: ");
            int minLen = sc.nextInt();
            sc.nextLine();

            int maxLen;
            do {
                System.out.print("Now enter the maximum length: ");
                maxLen = sc.nextInt();
                sc.nextLine();
                if (maxLen <= minLen) {
                    System.out.println("\nThe maximum length must be greater than the minimum length.");
                }
            } while (maxLen <= minLen);
            
            System.out.println("\nEnter the probabilities for each base (should sum to 1 or less):");
            System.out.printf("%-" + WIDTH + "s", "A: ");
            double pA = sc.nextDouble();
            System.out.printf("%-" + WIDTH + "s", "C: ");
            double pC = sc.nextDouble();
            System.out.printf("%-" + WIDTH + "s", "G: ");
            double pG = sc.nextDouble();
            System.out.printf("%-" + WIDTH + "s", "T: ");
            double pT = sc.nextDouble();
            sc.nextLine();

            double total = pA + pC + pG + pT;
            if (total > 1) {
                System.out.println("\nWarning! Probabilities do not sum to 1. Normalizing.");
                pA /= total;
                pC /= total;
                pG /= total;
                pT /= total;
            }

            double[] probs = {pA, pC, pG, pT};
            
            System.out.print("Enter the pattern length to find: ");
            int patLen = sc.nextInt();
            sc.close();

            Sequences seqsBio = new Sequences(nSeqs, minLen, maxLen, probs, patLen);
            seqsBio.printPatCount();
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Make sure to enter valid numbers.");
        }
    }
}
