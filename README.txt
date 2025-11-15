BACKTRACKING:

Kakuro es un puzzle lógico similar al Sudoku. El objetivo es completar 
una cuadrícula con números del 1 al 9, cumpliendo dos restricciones:
  1. Los números en cada grupo (horizontal o vertical) deben sumar 
     un valor específico indicado como pista.
  2. No puede haber números repetidos dentro de un mismo grupo.

Este proyecto implementa un solver automático usando backtracking 
recursivo con poda temprana para resolver tableros de Kakuro de 
cualquier tamaño. El algoritmo explora el espacio de soluciones de 
forma sistemática, retrocediendo cuando encuentra restricciones 
violadas.


ESTRUCTURA DEL PROYECTO
==========================================================================

TP/
├── src/
│   ├── Main.java                 - Programa principal
│   ├── KakuroSolver.java         - Algoritmo backtracking
│   ├── Validador.java            - Validación y poda
│   ├── Tablero.java              - Carga y representación del tablero
│   ├── Run.java                  - Estructura de grupo de celdas
│   ├── Celda.java                - Estructura de celda
│   ├── TestKakuro.java           - Suite de tests con menú interactivo
│   └── tablerostest/             - Tests

CARACTERÍSTICAS IMPLEMENTADAS
==========================================================================

Algoritmo de Backtracking Recursivo
  - Implementación pura recursiva sin iteraciones
  - Esquema: generar candidato -> validar -> recursión -> backtrack
  - Espacio de búsqueda: 9^n combinaciones posibles

Poda Temprana (Optimización)
  - Descarta ramas cuando la suma parcial excede el objetivo
  - Validación incremental en cada asignación de valor
  - Reduce significativamente las llamadas recursivas

Validación de Restricciones
  - Detecta números repetidos en un grupo usando HashSet
  - Verifica que la suma final iguale el objetivo
  - Valida que cada celda blanca pertenezca a exactamente 
    un grupo horizontal y uno vertical

Métricas de Rendimiento
  - Medición de tiempo de ejecución en milisegundos
  - Contador de llamadas recursivas para análisis empírico
  - Comparación entre diferentes tamaños de tablero

Suite de Tests Interactiva
  - Menú para seleccionar tests individuales o ejecutar todos
  - 7 tableros de prueba con diferentes características
  - 4 tableros con solución, 3 sin solución (validación)


CÓMO EJECUTAR
==========================================================================
Primero compilar:
  cd src
  javac *.java

Ejecución del programa principal:
  java Main
  (Resuelve tablerostest/kakuro_example.txt por defecto)

Ejecución de la suite de tests:
  java TestKakuro
  
  Menú interactivo:
    0. Ejecutar todos los tests
    1-7. Ejecutar test específico

Para usar otro tablero:
  Editar Main.java, línea 8, cambiar el archivo en:
  String archivoEntrada = "tablerostest/kakuro_example.txt";


ANÁLISIS DE COMPLEJIDAD
==========================================================================

Clase del Problema: NP-Completo
  Kakuro pertenece a la clase de problemas de satisfacción de 
  restricciones (CSP), similar a Sudoku y N-Queens. No se conoce 
  solución polinomial. Backtracking es el enfoque estándar.

Complejidad Temporal:
  Peor caso sin poda: O(9^n)
    - n = cantidad de celdas blancas
    - 9 valores posibles por celda (1-9)
    - Ejemplo: 16 celdas = 9^16 = 1.85 x 10^15 combinaciones

  Caso real con poda: Significativamente menor
    - La poda temprana descarta ramas inválidas
    - Ejemplo: tablero 8x8 (16 celdas) se resuelve en 45 llamadas
    - Reducción del 99.99% del espacio de búsqueda

Complejidad Espacial: O(n)
  Limitada por la profundidad de la pila de recursión (n niveles).
  No se almacenan soluciones intermedias.

Resultados Empíricos:
  Tablero Simple (4 celdas)    -> 5 llamadas     -> 1.2 ms
  Tablero 8x8 (16 celdas)      -> 45 llamadas    -> 0.4 ms
  Tablero Example (28 celdas)  -> 714 llamadas   -> 4.0 ms
  Tablero Difícil (16 celdas)  -> 1826 llamadas  -> 38 ms (sin solución)


REQUISITOS DEL TPO
==========================================================================

Implementación recursiva (sin iteraciones)
Algoritmo de backtracking con poda
Lectura de tablero desde archivo .txt
Validación de restricciones del problema
Medición de tiempo de ejecución
Contador de llamadas recursivas
Análisis de complejidad temporal y espacial
Documentación con Javadoc y comentarios
Modularización en clases con responsabilidades claras
Múltiples casos de prueba (con y sin solución)


FORMATO DE ENTRADA
==========================================================================

Los tableros se definen en archivos .txt con el siguiente formato:

  X      Celda negra (no jugable)
  0      Celda blanca vacía (a completar con 1-9)
  n/m    Pista: n=suma vertical, m=suma horizontal
  n/-    Pista solo horizontal
  -/m    Pista solo vertical

Ejemplo (kakuro_simple.txt):
  X X 4/- 3/-
  X -/3 0 0
  X -/4 0 0

Interpretación:
  - Primera fila: pistas horizontales (suma 4 y suma 3)
  - Segunda fila: pista vertical (suma 3) y dos celdas a completar
  - Tercera fila: pista vertical (suma 4) y dos celdas a completar

Solución:
  X X 4/- 3/-
  X -/3 1 2    (horizontal: 1+2=3, vertical: 1+3=4)
  X -/4 3 1    (horizontal: 3+1=4, vertical: 2+1=3)

Reglas importantes:
  - Cada celda blanca debe pertenecer a exactamente un grupo 
    horizontal y uno vertical
  - Todas las filas deben tener el mismo ancho
  - Separar elementos con espacios


PRUEBAS Y VALIDACIÓN
==========================================================================

Se incluyen 7 tableros de prueba en tablerostest/:

Con solución:
  - kakuro_simple.txt (4 celdas)
  - kakuro.txt (4 celdas, tablero original del TPO)
  - kakuro_8x8.txt (16 celdas, tablero grande)
  - kakuro_example.txt (28 celdas)

Sin solución:
  - kakuro_medio.txt (restricciones contradictorias)
  - kakuro_dificil.txt (sobre-restricciones)
  - kakuro_imposible.txt (matemáticamente imposible)

Ver PRUEBAS.txt para resultados detallados y análisis de rendimiento.


DETALLES TÉCNICOS
==========================================================================

Lenguaje: Java SE-24
Paradigma: Programación Orientada a Objetos
Algoritmo: Backtracking recursivo con poda
Entrada: Archivos de texto (.txt)
Salida: Consola

Clase principal: Main.java
Algoritmo core: KakuroSolver.java (método backtrack recursivo)
Validación: Validador.java (verifica restricciones y realiza poda)
Parser: Tablero.java (carga y representa el tablero)


REFERENCIAS
==========================================================================

Material teórico:
  - Capítulo 5: Backtracking (apunte Programación III)
  - Capítulo 6: Complejidad Computacional
  - Algoritmos de búsqueda con vuelta atrás
  - Problemas de satisfacción de restricciones (CSP)
  - Teoría de NP-Completitud

Problema similar: Sudoku (también NP-Completo, resuelto con backtracking)

==========================================================================
