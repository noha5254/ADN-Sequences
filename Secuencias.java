import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Secuencias {
    private String[] secuencias;
    private double[] probabilidades = new double[4];
    private Map<String, Integer> conteoPatrones;

    // Constructor
    public Secuencias(int numeroSecuencias, int tamañoMinimo, int tamañoMaximo, double[] probabilidades, int tamañoMotivo) {
        this.conteoPatrones = new HashMap<>();
        this.probabilidades = probabilidades;
        this.secuencias = new String[numeroSecuencias];
        
        // Generar secuencias
        for (int i = 0; i < numeroSecuencias; i++) {
            int tamañoSecuencia = (int) (Math.random() * (tamañoMaximo - tamañoMinimo + 1)) + tamañoMinimo;
            this.secuencias[i] = generarSecuencia(tamañoSecuencia);
        }
        
        // Mostrar secuencias generadas para depuración
        // System.out.println("\nSecuencias generadas:");
        // for (String seq : secuencias) {
        //     System.out.println(seq);
        // }

        detectarMotivo(tamañoMotivo);
    }

    // Generador de secuencias A, C, G, T
    private String generarSecuencia(int tamaño) {
        StringBuilder secuencia = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < tamaño; i++) {
            double r = random.nextDouble();
            if (r < probabilidades[0]) {
                secuencia.append('A');
            } else if (r < probabilidades[0] + probabilidades[1]) {
                secuencia.append('C');
            } else if (r < probabilidades[0] + probabilidades[1] + probabilidades[2]) {
                secuencia.append('G');
            } else {
                secuencia.append('T');
            }
        }
        return secuencia.toString();
    }

    // Detecta y cuenta los patrones en las secuencias generadas
    private void detectarMotivo(int tamañoMotivo) {
        for (String secuencia : secuencias) {
            for (int i = 0; i <= secuencia.length() - tamañoMotivo; i++) {
                String motivo = secuencia.substring(i, i + tamañoMotivo);
                conteoPatrones.put(motivo, conteoPatrones.getOrDefault(motivo, 0) + 1);
            }
        }
    }

    // Imprime los patrones encontrados y sus ocurrencias
    public void imprimirConteoPatrones() {
        System.out.println("\n=== Conteo de Patrones Encontrados ===");
        if (conteoPatrones.isEmpty()) {
            System.out.println("No se encontraron patrones.");
        } else {
            conteoPatrones.forEach((clave, valor) -> 
                System.out.printf("Patrón: %-10s | Ocurrencias: %4d%n", clave, valor)
            );
        }
        System.out.println("=====================================");
    }

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int ANCHO = 6;  // Ancho de las etiquetas para una presentación más ordenada

        try {
            System.out.println("\n=== Configuración de Secuencias ===");

            System.out.print("Ingrese el número de secuencias a generar: ");
            int numeroSecuencias = sc.nextInt();
            sc.nextLine();

            System.out.print("Digite el largo mínimo de dichas cadenas: ");
            int tamañoMinimo = sc.nextInt();
            sc.nextLine();

            int tamañoMaximo;
            do {
                System.out.print("Ahora digite el largo máximo: ");
                tamañoMaximo = sc.nextInt();
                sc.nextLine();
                if (tamañoMaximo <= tamañoMinimo) {
                    System.out.println("\nEl largo máximo debe ser mayor que el largo mínimo.");
                }
            } while (tamañoMaximo <= tamañoMinimo);
            
            System.out.println("\nIngrese las probabilidades para cada base (deben sumar 1 o menos):");
            System.out.printf("%-" + ANCHO + "s", "A: ");
            double probA = sc.nextDouble();
            System.out.printf("%-" + ANCHO + "s", "C: ");
            double probC = sc.nextDouble();
            System.out.printf("%-" + ANCHO + "s", "G: ");
            double probG = sc.nextDouble();
            System.out.printf("%-" + ANCHO + "s", "T: ");
            double probT = sc.nextDouble();
            sc.nextLine();

            // Normalizar las probabilidades si es necesario
            double total = probA + probC + probG + probT;
            if (total > 1) {
                System.out.println("\n¡Advertencia! Las probabilidades no suman 1. Normalizando.");
                probA /= total;
                probC /= total;
                probG /= total;
                probT /= total;
            }

            double[] probabilidades = {probA, probC, probG, probT};
            
            System.out.print("Ingrese el tamaño de los patrones a encontrar: ");
            int tamañoMotivo = sc.nextInt();
            sc.close();

            Secuencias secuenciasBio = new Secuencias(numeroSecuencias, tamañoMinimo, tamañoMaximo, probabilidades, tamañoMotivo);
            secuenciasBio.imprimirConteoPatrones();
        } catch (InputMismatchException e) {
            System.out.println("\nEntrada inválida. Asegúrate de ingresar números válidos.");
        }
    }
}



