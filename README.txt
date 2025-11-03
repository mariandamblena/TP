==========================================================================
                    TRABAJO PRÁCTICO OBLIGATORIO
              PROGRAMACIÓN III - TEMA 2: KAKURO
                    Algoritmo: BACKTRACKING
==========================================================================

📌 ESTRUCTURA DEL PROYECTO
==========================================================================

TP/
├── src/
│   ├── Main.java                 - Punto de entrada, medición de tiempo
│   ├── KakuroSolver.java         - Algoritmo backtracking recursivo
│   ├── Validador.java            - Validación de restricciones + poda
│   ├── Tablero.java              - Representación del tablero y parser
│   ├── Run.java                  - Grupo de celdas (horizontal/vertical)
│   ├── Celda.java                - Celda individual del tablero
│   ├── TestKakuro.java           - Batería completa de tests
│   ├── kakuro.txt                - Tablero principal (SOLUCIÓN ✓)
│   ├── kakuro_simple.txt         - Test básico
│   ├── kakuro_medio.txt          - Test medio
│   ├── kakuro_dificil.txt        - Test difícil (22 celdas)
│   ├── kakuro_completo.txt       - Test integración
│   └── kakuro_imposible.txt      - Test sin solución
├── PRUEBAS.txt                   - Documentación de pruebas y resultados
└── README.txt                    - Este archivo


==========================================================================
🎯 CARACTERÍSTICAS IMPLEMENTADAS
==========================================================================

✅ Backtracking Recursivo Puro
   - Sin iteraciones, solo recursión como requiere el TPO
   - Esquema clásico: generar → validar → recursión → backtrack

✅ Poda Temprana (Pruning)
   - Descartar ramas si suma parcial > objetivo
   - Validación incremental en cada asignación
   - Reduce drásticamente el espacio de búsqueda

✅ Validación de Restricciones
   - No repetir números en un run
   - Suma exacta = objetivo
   - Solo dígitos 1-9

✅ Métricas de Rendimiento
   - Tiempo de ejecución en milisegundos
   - Contador de llamadas recursivas
   - Análisis empírico de complejidad

✅ Documentación Completa
   - Javadoc en todas las clases
   - Comentarios explicando algoritmo paso a paso
   - Análisis de complejidad O(9^n)

✅ Modularización Clara
   - 6 clases con responsabilidades únicas
   - Separación de concerns: parser, solver, validator
   - Código mantenible y extensible


==========================================================================
🚀 CÓMO EJECUTAR
==========================================================================

1. Compilar el proyecto:
   > cd src
   > javac *.java

2. Ejecutar el programa principal (usa kakuro.txt):
   > java Main

3. Ejecutar tests completos (todos los tableros):
   > java TestKakuro

4. Para usar otro tablero:
   - Opción A: Editar Main.java y cambiar "kakuro.txt" por otro archivo
   - Opción B: Reemplazar el contenido de kakuro.txt


==========================================================================
📊 COMPLEJIDAD COMPUTACIONAL
==========================================================================

Clase del Problema: NP-Completo
   - Similar a: Sudoku, N-Queens, Ciclo Hamiltoniano
   - No existe solución polinómica conocida
   - Backtracking es el enfoque correcto para problemas NP

Complejidad Temporal:
   ⏱ Peor caso: O(9^n)
   - n = cantidad de celdas blancas
   - Cada celda puede tomar 9 valores (1-9)
   - Con poda, se reduce significativamente en la práctica

Complejidad Espacial:
   💾 O(n) - Pila de recursión
   - Profundidad máxima = n celdas
   - No se almacenan soluciones intermedias

Ejemplo Empírico:
   - 4 celdas  → 5 llamadas    → 1.2 ms
   - 22 celdas → 29,907,960 llamadas → 54,108 ms (54 seg)
   - Crecimiento exponencial claramente observable


==========================================================================
✅ REQUISITOS DEL TPO CUMPLIDOS
==========================================================================

[✓] Implementación recursiva (NO iterativa)
[✓] Algoritmo de backtracking puro
[✓] Poda para optimización
[✓] Lectura de tablero desde archivo
[✓] Validación de restricciones de Kakuro
[✓] Medición de tiempo de ejecución
[✓] Contador de llamadas recursivas
[✓] Documentación con Javadoc
[✓] Modularización del código en clases
[✓] Análisis de complejidad temporal y espacial
[✓] Pruebas con diferentes casos
[✓] Comentarios explicando el algoritmo


==========================================================================
📝 FORMATO DEL ARCHIVO DE ENTRADA
==========================================================================

Estructura del archivo kakuro.txt:

   X       = Celda negra (no se completa)
   .       = Celda blanca vacía (se debe completar con 1-9)
   n/m     = Celda con claves:
             n = suma vertical (hacia abajo)
             m = suma horizontal (hacia la derecha)
             0 = no hay run en esa dirección

Ejemplo:
   X X 4/0 3/0
   X 0/3 . .
   X 0/4 . .

Interpretación:
   - Columna 2 debe sumar 4 verticalmente
   - Columna 3 debe sumar 3 verticalmente
   - Fila 1 debe sumar 3 horizontalmente
   - Fila 2 debe sumar 4 horizontalmente

Solución:
   X X X X
   X X 1 2    (1+2=3 horizontal, 1+3=4 vertical)
   X X 3 1    (3+1=4 horizontal, 2+1=3 vertical)


==========================================================================
🔍 PRUEBAS Y VALIDACIÓN
==========================================================================

Ver archivo PRUEBAS.txt para:
   - Descripción detallada de cada tablero de prueba
   - Resultados empíricos de ejecución
   - Análisis de rendimiento
   - Gráficas de complejidad observada


==========================================================================
👨‍💻 NOTAS TÉCNICAS
==========================================================================

JDK Version: Java SE-24
Paradigma: Programación Orientada a Objetos
Técnica: Backtracking con Poda (Branch and Bound)
Entrada: Archivos de texto (.txt)
Salida: Consola con formato visual

Clase Principal: Main.java
Clase Core: KakuroSolver.java (contiene el algoritmo recursivo)


==========================================================================
📚 REFERENCIAS TEÓRICAS
==========================================================================

Basado en:
   - Capítulo 5: BACKTRACKING (apunte de Programación III)
   - Capítulo 6: Complejidad Computacional
   - Algoritmos de búsqueda con vuelta atrás
   - Problemas de satisfacción de restricciones (CSP)
   - Teoría de NP-Completitud


==========================================================================
