import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Sequences {
    private String[] sequences;
    private double[] probabilities = new double[4];
    private Map<String, Integer> patternCount;

    // Constructor
    public Sequences(int loops, int minSize, int maxSize, double[] probabilities, int motifSize) {
        this.patternCount = new HashMap<>();
        this.probabilities = probabilities;
        this.sequences = new String[loops];
        
        // Generar secuencias
        for (int i = 0; i < loops; i++) {
            int sequenceSize = (int) (Math.random() * (maxSize - minSize + 1)) + minSize;
            this.sequences[i] = generateSequence(sequenceSize);
        }
        
        // Mostrar secuencias generadas para depuración
        System.out.println("\nSecuencias generadas:");
        for (String seq : sequences) {
            System.out.println(seq);
        }

        detectMotif(motifSize);
    }

    // Generador de secuencias A, C, G, T
    private String generateSequence(int size) {
        StringBuilder sequence = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            double r = random.nextDouble();
            if (r < probabilities[0]) {
                sequence.append('A');
            } else if (r < probabilities[0] + probabilities[1]) {
                sequence.append('C');
            } else if (r < probabilities[0] + probabilities[1] + probabilities[2]) {
                sequence.append('G');
            } else {
                sequence.append('T');
            }
        }
        return sequence.toString();
    }

    // Detecta y cuenta los patrones en las secuencias generadas
    private void detectMotif(int motifSize) {
        for (String line : sequences) {
            for (int i = 0; i <= line.length() - motifSize; i++) {
                String pattern = line.substring(i, i + motifSize);
                patternCount.put(pattern, patternCount.getOrDefault(pattern, 0) + 1);
            }
        }
    }

    // Imprime los patrones encontrados y sus ocurrencias
    public void printPatternCount() {
        System.out.println("\nConteo de patrones encontrados:");
        patternCount.forEach((key, value) -> 
            System.out.println("Patrón: " + key + " - Ocurrencias: " + value)
        );
    }

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int WIDTH = 6;  // Ancho de las etiquetas para una presentación más ordenada

        try {
            System.out.println("Ingrese el número de secuencias a generar:");
            int loops = sc.nextInt();
            sc.nextLine();

            System.out.println("Digite el largo mínimo de dichas cadenas:");
            int min = sc.nextInt();
            sc.nextLine();

            int max;
            do {
                System.out.println("Ahora digite el largo máximo:");
                max = sc.nextInt();
                sc.nextLine();
                if (max <= min) {
                    System.out.println("El largo máximo debe ser mayor que el largo mínimo.");
                }
            } while (max <= min);

            System.out.println("Ingrese las probabilidades para cada base (deben sumar 1 o menos):");
            System.out.printf("%-" + WIDTH + "s", "A: ");
            double probA = sc.nextDouble();
            System.out.printf("%-" + WIDTH + "s", "C: ");
            double probC = sc.nextDouble();
            System.out.printf("%-" + WIDTH + "s", "G: ");
            double probG = sc.nextDouble();
            System.out.printf("%-" + WIDTH + "s", "T: ");
            double probT = sc.nextDouble();
            sc.nextLine();

            // Normalizar las probabilidades si es necesario
            double total = probA + probC + probG + probT;
            if (total > 1) {
                System.out.println("Las probabilidades no suman 1. Normalizando.");
                probA /= total;
                probC /= total;
                probG /= total;
                probT /= total;
            }

            double[] probabilities = {probA, probC, probG, probT};

            System.out.println("Ingrese el tamaño de los patrones a encontrar:");
            int patternSize = sc.nextInt();
            sc.close();

            Sequences bio = new Sequences(loops, min, max, probabilities, patternSize);
            bio.printPatternCount();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Asegúrate de ingresar números válidos.");
        }
    }
}

