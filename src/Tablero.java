
import java.io.*;
import java.util.*;

/**
 * Representa el tablero de Kakuro y gestiona la carga desde archivo. espera:
 * - "X" = celda negra (no se completa)
 * - "0" = celda blanca (vacía, se debe completar con 1-9)
 * - "n/m" = celda con claves: n=suma vertical para abajo, m=suma horizontal para derecha
 */
public class Tablero {
    private Celda[][] matriz;
    private String[][] tokens; 
    private List<Run> runsHorizontales = new ArrayList<>();
    private List<Run> runsVerticales = new ArrayList<>();
    private List<Celda> celdasBlancas = new ArrayList<>();
    private Map<String, Run> mapaRuns = new HashMap<>();

    public static Tablero leerDesdeArchivo(String archivo) {
        try {
            
            List<String[]> lineas = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea.trim().split("\\s+"));
                }
            }
            br.close();

            // iniciar matriz y crear celdas blancas
            int filas = lineas.size();
            int columnas = lineas.get(0).length;
            Tablero t = new Tablero();
            t.matriz = new Celda[filas][columnas];
            t.tokens = new String[filas][columnas]; 

            // crear solo las celdas blancas
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    String token = lineas.get(i)[j];
                    t.tokens[i][j] = token;  
                    if (token.equals("0")) {
                        Celda celda = new Celda(i, j);
                        t.matriz[i][j] = celda;
                        t.celdasBlancas.add(celda);
                    } else {
                        // celda negra (X) o con claves (n/m)
                        t.matriz[i][j] = null;
                    }
                }
            }

            // creoruns desde celdas con claves 
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    String token = lineas.get(i)[j];
                    if (token.contains("/")) {
                        String[] partes = token.split("/");
                        //de - a 0 para parsear
                        int sumaV = partes[0].equals("-") ? 0 : Integer.parseInt(partes[0]);
                        int sumaH = partes[1].equals("-") ? 0 : Integer.parseInt(partes[1]);

                        // RUN HORIZONTAL
                        if (sumaH > 0) {
                            List<Celda> celdas = new ArrayList<>();
                            int col = j + 1;
                            while (col < columnas && (lineas.get(i)[col].equals(".") || lineas.get(i)[col].equals("0"))) {
                                Celda celda = t.matriz[i][col];
                                if (celda != null) celdas.add(celda);
                                col++;
                            }
                            if (!celdas.isEmpty()) {
                                Run run = new Run(sumaH, celdas);
                                t.runsHorizontales.add(run);
                                // mapear cada celda a su run horizontal
                                for (Celda c : celdas) {
                                    t.mapaRuns.put("H" + c.fila + "," + c.col, run);
                                }
                            }
                        }

                        // RUN VERTICAL:
                        if (sumaV > 0) {
                            List<Celda> celdas = new ArrayList<>();
                            int fil = i + 1;
                            while (fil < filas && (lineas.get(fil)[j].equals(".") || lineas.get(fil)[j].equals("0"))) {
                                Celda celda = t.matriz[fil][j];
                                if (celda != null) celdas.add(celda);
                                fil++;
                            }
                            if (!celdas.isEmpty()) {
                                Run run = new Run(sumaV, celdas);
                                t.runsVerticales.add(run);
                                // mapear cada celda a su run vertical
                                for (Celda c : celdas) {
                                    t.mapaRuns.put("V" + c.fila + "," + c.col, run);
                                }
                            }
                        }
                    }
                }
            }

            // cada celda blanca debe pertenecer exactamente a 1 run H y 1 run V
            for (Celda c : t.celdasBlancas) {
                String keyH = "H" + c.fila + "," + c.col;
                String keyV = "V" + c.fila + "," + c.col;
                if (!t.mapaRuns.containsKey(keyH) || !t.mapaRuns.containsKey(keyV)) {
                    System.err.println("Error: celda sin run asignada en (" + c.fila + "," + c.col + ")");
                    return null;
                }
            }

            return t;
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
            return null;
        }
    }

    public List<Celda> getCeldasBlancas() {
        return celdasBlancas;
    }

    public Run getRunHorizontal(Celda c) {
        return mapaRuns.get("H" + c.fila + "," + c.col);
    }

    public Run getRunVertical(Celda c) {
        return mapaRuns.get("V" + c.fila + "," + c.col);
    }

    public boolean validarSumasCompletas() {
        // Verificar todos los runs horizontales
        for (Run run : runsHorizontales) {
            if (!Validador.sumaEsValida(run)) return false;
        }
        // Verificar todos los runs verticales
        for (Run run : runsVerticales) {
            if (!Validador.sumaEsValida(run)) return false;
        }
        return true;
    }
    
    // funcion que ayuda a imprimir
    private String obtenerContenido(int i, int j) {
        if (matriz[i][j] != null) {
            int val = matriz[i][j].valor;
            return (val == 0 ? "0" : String.valueOf(val));
        }
        
        String token = tokens[i][j];
        if (token.contains("/")) {
            String[] partes = token.split("/");
            String parte1 = partes[0].equals("0") ? "-" : partes[0];
            String parte2 = partes[1].equals("0") ? "-" : partes[1];
            return parte1 + "/" + parte2;
        }
        return token;
    }
    
    public void imprimir() {
        // calcular ancho maximo de cada columna
        int[] anchosColumna = new int[matriz[0].length];
        for (int j = 0; j < matriz[0].length; j++) {
            for (int i = 0; i < matriz.length; i++) {
                anchosColumna[j] = Math.max(anchosColumna[j], obtenerContenido(i, j).length());
            }
        }
        // imprimir con formato alineado
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%-" + (anchosColumna[j] + 1) + "s", obtenerContenido(i, j));
            }
            System.out.println();
        }
    }
}