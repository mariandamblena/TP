import java.util.List;

/**
 * Clase que resuelve un tablero de Kakuro utilizando el algoritmo de Backtracking.
 */
public class KakuroSolver {
    private Tablero tablero;
    private int contadorLlamadas = 0; 
    private List<Celda> celdasBlancas;

    public KakuroSolver(Tablero tablero) {
        this.tablero = tablero;
        this.celdasBlancas = tablero.getCeldasBlancas();
    }

    public boolean resolver() {
        return backtrack(0);
    }

    /**
     * algoritmo de backtracking recursivo
*/
    private boolean backtrack(int idx) {
        contadorLlamadas++;  

        if (idx == celdasBlancas.size()) {
            return tablero.validarSumasCompletas();
        }

        Celda celda = celdasBlancas.get(idx);

        //pruebo valores del 1 al 9
        for (int val = 1; val <= 9; val++) {
            celda.valor = val;
            
            // poda, verifico si el valor es válido antes deseguir
            if (Validador.esValido(tablero, celda)) {
                if (backtrack(idx + 1)) {
                    return true;}
            }
            
            // backtrack, deshacer el cambio si no llevaa una solución
            celda.valor = 0;
        }
        
        return false;  
    }

    public int getContadorLlamadas() {
        return contadorLlamadas;
    }
}