import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Conjunto matemático de números enteros.
 * <p>
 * No admite elementos repetidos ni nulos, y el orden de los elementos no afecta
 * la igualdad. Los elementos se guardan en un {@link LinkedHashSet}, que conserva
 * el orden de inserción para que la salida sea predecible.
 * <p>
 * Las operaciones que producen un conjunto (unión, intersección, diferencia,
 * complemento y clonación) devuelven un objeto nuevo y no modifican a los
 * operandos.
 *
 * @author Sebastián Verdugo Bermúdez
 * @version 1.0
 */
public class Conjunto implements Cloneable {

    /** Elementos del conjunto, sin repetidos y en orden de inserción. */
    private Set <Integer> elementos;

    /**
     * Crea un conjunto vacío.
     */
    public Conjunto(){
        elementos = new LinkedHashSet<>();
    }

    /**
     * Crea un conjunto con los elementos de una colección. Los valores repetidos
     * se guardan una sola vez y los valores {@code null} se descartan. La
     * colección se copia, así que modificarla después no altera el conjunto.
     *
     * @param datos colección con los elementos iniciales
     */
    public Conjunto(Collection<Integer> datos){
        elementos = new LinkedHashSet<>();
        for(Integer dato : datos){
            if(dato != null){
                elementos.add(dato);
            }
        }
    }

    /**
     * Indica si el conjunto no tiene elementos.
     *
     * @return {@code true} si el conjunto está vacío
     */
    public boolean isEmpty(){
        return elementos.isEmpty();
    }

    /**
     * Devuelve la cantidad de elementos del conjunto (su cardinalidad).
     *
     * @return el número de elementos
     */
    public int size(){
        return elementos.size();
    }

    /**
     * Indica si un elemento pertenece al conjunto.
     *
     * @param elemento elemento que se busca
     * @return {@code true} si el elemento está en el conjunto
     */
    public boolean contains(Integer elemento){
        return elementos.contains(elemento);
    }

    /**
     * Indica si este conjunto es subconjunto de otro, es decir, si todos sus
     * elementos están también en el otro conjunto.
     *
     * @param otro conjunto con el que se compara
     * @return {@code true} si este conjunto es subconjunto de {@code otro}
     */
    public boolean subset(Conjunto otro) {
        return otro.elementos.containsAll(elementos);
    }

    /**
     * Indica si este conjunto es subconjunto propio de otro: es subconjunto y
     * además tiene menos elementos.
     *
     * @param otro conjunto con el que se compara
     * @return {@code true} si este conjunto es subconjunto propio de {@code otro}
     */
    public boolean subsetP(Conjunto otro) {
        return this.subset(otro) && this.size() < otro.size();
    }

    /**
     * Calcula la unión de este conjunto con otro.
     *
     * @param otro conjunto con el que se une
     * @return un conjunto nuevo con los elementos de ambos conjuntos
     */
    public Conjunto union(Conjunto otro) {
        Conjunto resultado = this.clone();
        resultado.elementos.addAll(otro.elementos);
        return resultado;
    }

    /**
     * Calcula la intersección de este conjunto con otro.
     *
     * @param otro conjunto con el que se intersecta
     * @return un conjunto nuevo con los elementos que están en ambos conjuntos
     */
    public Conjunto intersection(Conjunto otro) {
        Conjunto resultado = this.clone();
        resultado.elementos.retainAll(otro.elementos);
        return resultado;
    }

    /**
     * Calcula la diferencia entre este conjunto y otro.
     *
     * @param otro conjunto cuyos elementos se quitan
     * @return un conjunto nuevo con los elementos de este conjunto que no están en {@code otro}
     */
    public Conjunto difference(Conjunto otro) {
        Conjunto resultado = this.clone();
        resultado.elementos.removeAll(otro.elementos);
        return resultado;
    }

    /**
     * Calcula el complemento de este conjunto respecto de un universo: los
     * elementos del universo que no están en este conjunto.
     *
     * @param universo conjunto que contiene a todos los elementos posibles
     * @return un conjunto nuevo con el complemento
     * @throws IllegalArgumentException si este conjunto no es subconjunto del universo
     */
    public Conjunto complement(Conjunto universo) {
        if(!this.subset(universo)) {
            throw new IllegalArgumentException("El conjunto no es subconjunto del universo");
        }
        return universo.difference(this);
    }

    /**
     * Calcula el producto cartesiano de este conjunto con otro: todos los pares
     * ordenados cuyo primer componente está en este conjunto y el segundo en
     * {@code otro}.
     * <p>
     * Devuelve un {@code Set} y no un {@code Conjunto} porque esta versión de
     * {@code Conjunto} solo almacena enteros.
     *
     * @param otro conjunto de donde se toma el segundo componente de cada par
     * @return un conjunto con todos los pares ordenados
     */
    public Set<Par> productC(Conjunto otro) {
        Set<Par> resultado = new LinkedHashSet<>();
        for(Integer elemento1 : this.elementos) {
            for(Integer elemento2 : otro.elementos) {
                resultado.add(new Par(elemento1, elemento2));
            }
        }
        return resultado;
    }

    /**
     * Calcula el conjunto potencia: todos los subconjuntos de este conjunto,
     * incluidos el vacío y el propio conjunto. Con n elementos se obtienen
     * 2<sup>n</sup> subconjuntos.
     * <p>
     * Parte del conjunto vacío y, por cada elemento, agrega una copia de cada
     * subconjunto existente con ese elemento. Los subconjuntos nuevos se juntan
     * aparte y se agregan al final de cada vuelta, porque no se puede modificar
     * una colección mientras se recorre.
     *
     * @return un conjunto con todos los subconjuntos
     */
    public Set<Conjunto> pow() {
        Set<Conjunto> potencia = new LinkedHashSet<>();
        potencia.add(new Conjunto());

        for(Integer elemento : elementos) {
            Set<Conjunto> nuevosSubconjuntos = new LinkedHashSet<>();
            for(Conjunto subconjunto : potencia) {
                Conjunto nuevoSubconjunto = subconjunto.clone();
                nuevoSubconjunto.elementos.add(elemento);
                nuevosSubconjuntos.add(nuevoSubconjunto);
            }
            potencia.addAll(nuevosSubconjuntos);
        }
        return potencia;
    }

    /**
     * Crea una copia independiente de este conjunto. La copia tiene su propio
     * {@code Set}, así que modificar una no afecta a la otra. Los elementos no se
     * clonan porque {@code Integer} es inmutable.
     *
     * @return una copia del conjunto
     */
    @Override
    public Conjunto clone() {
       Conjunto copia;
       try {
           copia = (Conjunto) super.clone();
           copia.elementos = new LinkedHashSet<>(this.elementos);
       } catch (CloneNotSupportedException e) {
           throw new AssertionError("No debería ocurrir: Conjunto implementa Cloneable", e);
       }
       return copia;
    }

    /**
     * Devuelve los elementos entre llaves y separados por comas, por ejemplo
     * {@code {2, 4, 6}}.
     *
     * @return la representación en texto del conjunto
     */
    @Override
    public String toString() {
        return formatear(elementos);
    }

    /**
     * Compara este conjunto con otro objeto. Son iguales si el otro objeto también
     * es un {@code Conjunto} y tiene los mismos elementos, sin importar el orden.
     *
     * @param obj objeto con el que se compara
     * @return {@code true} si ambos conjuntos tienen los mismos elementos
     */
    @Override
    public boolean equals(Object obj) {
        boolean iguales = false;

        if (this == obj) {
            iguales = true;
        } else if((obj instanceof Conjunto)) {
            Conjunto otro = (Conjunto) obj;
            iguales =  elementos.equals(otro.elementos);
        }

        return iguales;
    }

    /**
     * Calcula el código hash a partir de los elementos, de forma consistente con
     * {@link #equals(Object)}.
     *
     * @return el código hash del conjunto
     */
    @Override
    public int hashCode() {
        return elementos.hashCode();
    }

    /**
     * Da formato de conjunto a cualquier colección: sus elementos entre llaves y
     * separados por comas. Se usa para mostrar los resultados de
     * {@link #productC(Conjunto)} y {@link #pow()}, que no son de tipo
     * {@code Conjunto}.
     *
     * @param coleccion colección que se quiere mostrar
     * @return el texto con el formato {@code {a, b, c}}
     */
    public static String formatear(Collection<?> coleccion) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean esPrimero = true;
        for (Object elemento : coleccion) {
            if (!esPrimero) {
                sb.append(", ");
            }
            sb.append(elemento);
            esPrimero = false;
        }
        sb.append("}");
        return sb.toString();
    }
}