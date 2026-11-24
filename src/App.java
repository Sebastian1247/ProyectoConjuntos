import java.util.Arrays;
import java.util.List;

/**
 * Programa de prueba de la clase {@link Conjunto}.
 * <p>
 * Ejecuta las 13 operaciones del enunciado con sus mismos ejemplos. Los días de
 * la semana se representan con números: lunes = 1, martes = 2, miércoles = 3,
 * jueves = 4, viernes = 5, sábado = 6 y domingo = 7.
 *
 * @author Sebastián Verdugo Bermúdez
 * @version 1.0
 */
public class App {

    /**
     * Constructor privado: la clase solo tiene métodos estáticos y no se instancia.
     */
    private App() {
    }

    /**
     * Punto de entrada del programa. Crea los conjuntos de ejemplo y muestra en
     * consola el resultado de cada operación.
     *
     * @param args argumentos de la línea de comandos (no se usan)
     */
    public static void main(String[] args) {

        titulo("Constructores");
        Conjunto vacio = new Conjunto();
        Conjunto conRepetidos = new Conjunto(Arrays.asList(1, 1, 2, null, 3));
        System.out.println("Vacío: " + vacio);
        System.out.println("A partir de [1, 1, 2, null, 3]: " + conRepetidos);

        titulo("1. Conjunto vacío (isEmpty)");
        System.out.println(vacio + " está vacío? " + vacio.isEmpty());
        System.out.println(conRepetidos + " está vacío? " + conRepetidos.isEmpty());

        // A = {martes, jueves, sábado}
        titulo("2. Pertenencia (contains)");
        Conjunto a = new Conjunto(List.of(2, 4, 6));
        System.out.println("A = " + a);
        System.out.println("6 pertenece a A? " + a.contains(6));
        System.out.println("1 pertenece a A? " + a.contains(1));

        titulo("3. Subconjunto (subset)");
        Conjunto b = new Conjunto(List.of(2, 3, 4, 5, 6, 7));
        System.out.println("A = " + a + "   B = " + b);
        System.out.println("A es subconjunto de B? " + a.subset(b));
        System.out.println("B es subconjunto de A? " + b.subset(a));
        System.out.println("{} es subconjunto de A? " + vacio.subset(a));

        titulo("4. Subconjunto propio (subsetP)");
        Conjunto semana = new Conjunto(List.of(1, 2, 3, 4, 5, 6, 7));
        System.out.println("A = " + a + "   B = " + semana);
        System.out.println("A es subconjunto propio de B? " + a.subsetP(semana));
        System.out.println("A es subconjunto propio de A? " + a.subsetP(a));

        // c = {lunes, miércoles, jueves, viernes, domingo}
        Conjunto c = new Conjunto(List.of(1, 3, 4, 5, 7));

        titulo("5. Unión (union)");
        System.out.println("A = " + a + "   B = " + c);
        System.out.println("A union B = " + a.union(c));

        titulo("6. Intersección (intersection)");
        System.out.println("A interseccion B = " + a.intersection(c));

        titulo("7. Diferencia (difference)");
        System.out.println("A - B = " + a.difference(c));
        System.out.println("B - A = " + c.difference(a));

        titulo("8. Complemento (complement)");
        System.out.println("U = " + semana + "   A = " + c);
        System.out.println("Complemento de A = " + c.complement(semana));
        try {
            Conjunto fuera = new Conjunto(List.of(8, 9));
            fuera.complement(semana);
        } catch (IllegalArgumentException e) {
            System.out.println("Complemento de {8, 9} -> error: " + e.getMessage());
        }

        titulo("9. Clonación (clone)");
        Conjunto copia = a.clone();
        System.out.println("A = " + a + "   copia = " + copia);
        System.out.println("Son iguales (equals)? " + a.equals(copia));
        System.out.println("Son el mismo objeto (==)? " + (a == copia));

        titulo("10. Listar elementos (toString)");
        System.out.println("A = " + a.toString());

        titulo("11. Igualdad (equals)");
        Conjunto desordenado = new Conjunto(List.of(6, 2, 4));
        System.out.println(a + " = " + desordenado + "? " + a.equals(desordenado));
        System.out.println(a + " = " + c + "? " + a.equals(c));

        titulo("12. Producto cartesiano (productC)");
        Conjunto p = new Conjunto(List.of(1, 4, 14));
        Conjunto q = new Conjunto(List.of(1, 3));
        System.out.println("A = " + p + "   B = " + q);
        System.out.println("A x B = " + Conjunto.formatear(p.productC(q)));

        titulo("13. Conjunto potencia (pow)");
        Conjunto r = new Conjunto(List.of(1, 2, 3));
        System.out.println("A = " + r);
        System.out.println("P(A) = " + Conjunto.formatear(r.pow()));
        System.out.println("|P(A)| = " + r.pow().size());
    }

    /**
     * Imprime una línea en blanco y el encabezado de una sección de la prueba.
     *
     * @param texto nombre de la sección
     */
    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }
}
