import java.util.HashSet;

/**
 * Clase que implementa las funciones de validación de restricciones del Kakuro, las restricciones son:
 * 1. los numeros en cada grupo (run) deben sumar el valor objetivo
 * 2. no puede haber numeros repetidos dentro de un grupo
 * 3. la suma parcial no debe exceder el valor objetivo (poda temprana)
 */
public class Validador {
    
    /**verifica si una celda con un valor asignado es valida según las restricciones.
     */
    public static boolean esValido(Tablero t, Celda c) {
        Run runH = t.getRunHorizontal(c);
        Run runV = t.getRunVertical(c);
        return runEsValido(runH) && runEsValido(runV);
    }

    /**
     * verifica si un run es valido parcialmente.
     */
    private static boolean runEsValido(Run run) {
        HashSet<Integer> usados = new HashSet<>();
        int suma = 0;
        int vacias = 0;

        // recorrer todas las celdas del grupo
        for (Celda c : run.celdas) {
            if (c.valor == 0) {
                vacias++;  
                continue;
            }

            if (usados.contains(c.valor)) {
                return false;  
            }
            usados.add(c.valor);
            suma += c.valor;
        }

        // si el grupo esta completo, verificar suma exacta
        if (vacias == 0) {
            return suma == run.sumaObjetivo;
        }
        
        // poda temprana: si la suma parcial ya excede el objetivo, es invalido
        return suma <= run.sumaObjetivo;
    }

    /**
     * Verifica si un grupo esta completo y cumple todas las restricciones.
     */
    public static boolean sumaEsValida(Run run) {
        int suma = 0;
        HashSet<Integer> usados = new HashSet<>();
        
        for (Celda c : run.celdas) {
            if (c.valor == 0) {
                return false;
            }
            
            if (usados.contains(c.valor)) {
                return false;
            }
            
            usados.add(c.valor);
            suma += c.valor;
        }
        return suma == run.sumaObjetivo;
    }
}