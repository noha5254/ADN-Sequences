import java.util.Scanner;


public class Sequences{
    public static void main(String[] args) {
        
        //llamado y declaracion
        Scanner sc = new Scanner(System.in);
        final int WIDTH = 2;

        double probA;
        double probT;
        double probG;
        double probC;


        //Recoleccion de informacion
        System.out.println("Ingresar el numero de secuencias que desea generar:");
        int Nsecuencias = sc.nextInt();

        System.out.println("Ingresar la longitud máxima de cada secuencia:");
        int longitudMaxima = sc.nextInt();
        
        System.out.println("Ingrese la probabilidad para cada base:");
        System.out.print(String.format("%-" + WIDTH + "s", "A: "));
        probA = sc.nextInt();
        System.out.print(String.format("%-" + WIDTH + "s", "T: "));
        probT = sc.nextInt();
        System.out.print(String.format("%-" + WIDTH + "s", "C: "));
        probC = sc.nextInt();
        System.out.print(String.format("%-" + WIDTH + "s", "G: "));
        probG = sc.nextInt();

        System.out.println("Ingresar tamaño de los patrones a analizar:");
        int longitPatron = sc.nextInt();

        //vISUALES
        //System.out.println("Probabilidad de Adenina: " + probA + "%");
        //System.out.println("Probabilidad de Timina: " + probT + "%");
        //System.out.println("Probabilidad de Citosina: " + probC + "%");
        //System.out.println("Probabilidad de Guanina: " + probG + "%"); 
   
       

        sc.close();
    }

}