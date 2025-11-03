import java.util.List;

/**
 * Clase que resuelve un tablero de Kakuro utilizando el algoritmo de Backtracking.
 * 
 * El algoritmo explora recursivamente el espacio de soluciones, asignando valores
 * a cada celda blanca y verificando las restricciones del Kakuro:
 * 1. Los números en cada grupo deben sumar el valor indicado
 * 2. No puede haber números repetidos en un grupo
 * 3. Solo se permiten dígitos del 1 al 9
 * 
 * Complejidad temporal: O(9^n) en el peor caso, donde n es la cantidad de celdas vacías
 * Complejidad espacial: O(n) por la pila de recursión
 */
public class KakuroSolver {
    private Tablero tablero;
    private int contadorLlamadas = 0;  // Métrica para análisis de complejidad
    private List<Celda> celdasBlancas;

    /**
     * Constructor del solver
     * @param tablero El tablero de Kakuro a resolver
     */
    public KakuroSolver(Tablero tablero) {
        this.tablero = tablero;
        this.celdasBlancas = tablero.getCeldasBlancas();
    }

    /**
     * Método público para iniciar la resolución
     * @return true si se encontró solución, false en caso contrario
     */
    public boolean resolver() {
        return backtrack(0);
    }

    /**
     * Algoritmo de backtracking recursivo.
     * 
     * @param idx Índice de la celda actual en la lista de celdas blancas
     * @return true si se encontró una solución válida, false si no hay solución
     * 
     * Pasos del algoritmo:
     * 1. CASO BASE: Si completamos todas las celdas, verificar si la solución es válida
     * 2. GENERAR CANDIDATOS: Probar valores del 1 al 9
     * 3. VALIDAR: Verificar restricciones antes de continuar (poda temprana)
     * 4. RECURSIÓN: Continuar con la siguiente celda
     * 5. BACKTRACK: Si no hay solución, deshacer el cambio y probar otro valor
     */
    private boolean backtrack(int idx) {
        contadorLlamadas++;  // Contar llamada recursiva para análisis
        
        // CASO BASE: Todas las celdas están llenas
        if (idx == celdasBlancas.size()) {
            return tablero.validarSumasCompletas();
        }

        // Obtener la celda actual
        Celda celda = celdasBlancas.get(idx);

        // GENERAR CANDIDATOS: Probar valores del 1 al 9
        for (int val = 1; val <= 9; val++) {
            celda.valor = val;
            
            // PODA: Verificar si el valor es válido antes de continuar
            if (Validador.esValido(tablero, celda)) {
                // RECURSIÓN: Intentar resolver el resto del tablero
                if (backtrack(idx + 1)) {
                    return true;  // Solución encontrada
                }
            }
            
            // BACKTRACK: Deshacer el cambio si no llevó a una solución
            celda.valor = 0;
        }
        
        return false;  // No se encontró solución con esta configuración
    }

    /**
     * Obtiene la cantidad de llamadas recursivas realizadas
     * @return Número total de llamadas a backtrack()
     */
    public int getContadorLlamadas() {
        return contadorLlamadas;
    }
}