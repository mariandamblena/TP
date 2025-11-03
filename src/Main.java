/**
 * Programa principal para resolver tableros de Kakuro usando Backtracking.
 * 
 * Trabajo Práctico Obligatorio - Programación III
 * Tema 2: KAKURO
 * 
 * El programa lee un tablero desde un archivo, aplica el algoritmo de backtracking
 * para encontrar una solución válida, y muestra estadísticas de ejecución.
 */
public class Main {
    public static void main(String[] args) {
        // Archivo de entrada con el tablero inicial
        String archivoEntrada = "kakuro.txt";

        System.out.println("=================================================");
        System.out.println("    SOLVER DE KAKURO - ALGORITMO BACKTRACKING    ");
        System.out.println("=================================================\n");

        // 1. CARGAR TABLERO desde archivo
        System.out.println("Cargando tablero desde: " + archivoEntrada);
        Tablero tablero = Tablero.leerDesdeArchivo(archivoEntrada);
        
        if (tablero == null) {
            System.err.println("ERROR: No se pudo construir el tablero desde '" + archivoEntrada + "'.");
            System.err.println("Verifica el formato del archivo de entrada.\n");
            return;
        }
        
        System.out.println("Tablero cargado exitosamente.");
        System.out.println("Celdas blancas a completar: " + tablero.getCeldasBlancas().size() + "\n");

        // 2. CREAR SOLVER y resolver
        KakuroSolver solver = new KakuroSolver(tablero);
        
        System.out.println("Iniciando resolución con Backtracking...\n");
        
        // Medir tiempo de ejecución
        long inicio = System.nanoTime();
        boolean exito = solver.resolver();
        long fin = System.nanoTime();
        
        // 3. MOSTRAR RESULTADOS
        System.out.println("=================================================");
        System.out.println("               RESULTADOS                        ");
        System.out.println("=================================================");
        System.out.println("Tiempo de ejecución: " + (fin - inicio) / 1e6 + " ms");
        System.out.println("Llamadas recursivas: " + solver.getContadorLlamadas());
        System.out.println();

        if (exito) {
            System.out.println("✓ SOLUCIÓN ENCONTRADA:\n");
            tablero.imprimir();
        } else {
            System.out.println("✗ No se encontró solución para este Kakuro.");
        }
        
        System.out.println("\n=================================================");
    }
}