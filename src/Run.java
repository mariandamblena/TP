import java.util.List;

/**
 * Run son el grupo de celdas consecutivas en Kakuro, puede ser horizontal o vertical.
    */
public class Run {
    public int sumaObjetivo;
    public List<Celda> celdas;// celdas blancas del run

    public Run(int suma, List<Celda> celdas) {
        this.sumaObjetivo = suma;
        this.celdas = celdas;
    }
}