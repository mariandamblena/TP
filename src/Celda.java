/**
 * Representa una celda blanca del tablero de Kakuro.cada celda Posición: fila y columna en el tablero y Valor: número del 1 al 9, o 0 
 */
public class Celda {
    public int fila;
    public int col;
    public int valor;

    public Celda(int fila, int col) {
        this.fila = fila;
        this.col = col;
        this.valor = 0;
    }
}