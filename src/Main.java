import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<validar> hilos = new ArrayList<>();

        // Archivo de registro
        String rutaArchivo = System.getProperty("user.home") + "/Documents/registroContra.txt";

        System.out.println("🔐 Validador concurrente de contraseñas\n");
        System.out.print("🔢 ¿Cuantas contraseñas deseas validar?: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        try (FileWriter escritor = new FileWriter(rutaArchivo, true)) { // true para APPEND
            for (int i = 1; i <= cantidad; i++) {
                System.out.print("➡️ Ingresa la contraseña #" + i + ": ");
                String pass = scanner.nextLine();

                validar hilo = new validar(pass, resultado -> {
                    try {
                        escritor.write("Contraseña: " + pass + " --> " + resultado + "\n");
                    } catch (IOException e) {
                        System.out.println("⚠️ Error al escribir en el archivo: " + e.getMessage());
                    }
                });

                hilos.add(hilo);
                hilo.start(); // Ejecutar el hilo
            }

            // Esperar que todos los hilos terminen
            for (validar hilo : hilos) {
                try {
                    hilo.join();
                } catch (InterruptedException e) {
                    System.out.println("⚠️ Error en el hilo: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("⚠️ Error al abrir el archivo: " + e.getMessage());
        }

        System.out.println("\n🧵 Todas las validaciones han finalizado.");
    }
}