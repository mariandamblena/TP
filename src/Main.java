import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Programa para resolver Kakuro con backtracking
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Kakuro con  Backtracking \n");
        
        // Busco archivos .txt en la carpeta tablerostest, son lso test disponibles
        File carpeta = new File("tablerostest");
        File[] archivosEncontrados = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));
        
        if (archivosEncontrados == null || archivosEncontrados.length == 0) {
            System.out.println("No se encontraron archivos de prueba en carpeta de testss");
            return;
        }
        
        // Convertir a rutas relativas
        List<String> archivos = new ArrayList<>();
        for (File archivo : archivosEncontrados) {
            archivos.add("tablerostest/" + archivo.getName());
        }
        
        System.out.println("Se encontraron " + archivos.size() + " archivos de prueba\n");
        
        // Menu
        System.out.println("Opciones:");
        System.out.println("  0. Ejecutar todos los tests");
        for (int i = 0; i < archivos.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + archivosEncontrados[i].getName());
        }
        System.out.print("\nOpcion: ");
        
        int opcion;
        try (Scanner scanner = new Scanner(System.in)) {
            opcion = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Opcion invalida, ejecutando todos los tests");
            opcion = 0;
        }
        
        System.out.println();
        
        // Elegir que tests ejecutar
        List<Integer> testsAEjecutar = new ArrayList<>();
        if (opcion == 0 || opcion < 0 || opcion > archivos.size()) {
            for (int i = 0; i < archivos.size(); i++) {
                testsAEjecutar.add(i);
            }
        } else {
            testsAEjecutar.add(opcion - 1);
        }
        
        int totalPruebas = 0;
        int exitosas = 0;
        int fallidas = 0;
        long tiempoTotal = 0;
        long llamadasTotales = 0;
        
        // Ejecutar tests
        for (int idx : testsAEjecutar) {
            totalPruebas++;
            String archivo = archivos.get(idx);
            String nombre = archivosEncontrados[idx].getName();
            System.out.println("\n TEST #" + (idx + 1) + ": " + nombre + " ---");
            System.out.println("Archivo: " + archivo);
            
            // Cargar tablero
            Tablero tablero = Tablero.leerDesdeArchivo(archivo);
            
            if (tablero == null) {
                System.out.println("  no se pudo cargar el tablero\n");
                fallidas++;
                continue;
            }
            
            System.out.println("Celdas a completar: " + tablero.getCeldasBlancas().size());
            
            // Resolver
            KakuroSolver solver = new KakuroSolver(tablero);
            long inicio = System.nanoTime();
            boolean exito = solver.resolver();
            long fin = System.nanoTime();
            
            long tiempo = fin - inicio;
            long llamadas = solver.getContadorLlamadas();
            
            tiempoTotal += tiempo;
            llamadasTotales += llamadas;
            
            // Resultados
            System.out.println("Tiempo: " + String.format("%.2f", tiempo / 1e6) + " ms");
            System.out.println("Llamadas recursivas: " + llamadas);
            
            if (exito) {
                exitosas++;
                System.out.println("SOLUCION ENCONTRADA\n");
                tablero.imprimir();
            } else {
                fallidas++;
                System.out.println("SIN SOLUCION");
            }
        }
        
        // Resumen
        if (testsAEjecutar.size() > 1) {
            System.out.println("\nResumen");
            System.out.println("Total pruebas: " + totalPruebas);
            System.out.println("Resueltos: " + exitosas);
            System.out.println("Sin solucion: " + fallidas);
            System.out.println("Tiempo total: " + String.format("%.2f", tiempoTotal / 1e6) + " ms");
            System.out.println("Llamadas totales: " + llamadasTotales);
        }
    }
}