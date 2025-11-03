import java.util.List;

/**
 * Representa un "run" (grupo de celdas consecutivas) en el Kakuro.
 * 
 * Un run puede ser:
 * - Horizontal: celdas consecutivas en la misma fila
 * - Vertical: celdas consecutivas en la misma columna
 * 
 * Restricciones de un run:
 * 1. La suma de los valores debe ser igual a sumaObjetivo
 * 2. No puede haber números repetidos
 * 3. Solo se usan valores del 1 al 9
 * 
 * Ejemplo: Un run horizontal con objetivo 15 y 3 celdas podría ser: [9, 4, 2]
 */
public class Run {
    /** Suma objetivo que deben alcanzar las celdas del run */
    public int sumaObjetivo;
    
    /** Lista de celdas blancas que conforman este run */
    public List<Celda> celdas;

    /**
     * Constructor de un run.
     * 
     * @param suma Suma objetivo que debe alcanzar este grupo de celdas
     * @param celdas Lista de celdas consecutivas que forman el run
     */
    public Run(int suma, List<Celda> celdas) {
        this.sumaObjetivo = suma;
        this.celdas = celdas;
    }
}