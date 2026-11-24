import java.util.Objects;

/**
 * Par ordenado de enteros. Es el tipo de los elementos que produce el producto
 * cartesiano de dos conjuntos.
 * <p>
 * La clase es inmutable: es {@code final}, sus atributos también lo son y no
 * tiene métodos que los modifiquen. Dos pares son iguales solo si coinciden sus
 * componentes en el mismo orden, por lo que {@code (1, 3)} es distinto de
 * {@code (3, 1)}.
 *
 * @author Sebastián Verdugo Bermúdez
 * @version 1.0
 */
public final class Par {

    /** Primer componente del par. */
    private final Integer primero;

    /** Segundo componente del par. */
    private final Integer segundo;

    /**
     * Crea un par ordenado con los componentes indicados.
     *
     * @param primero primer componente del par
     * @param segundo segundo componente del par
     */
    public Par(Integer primero, Integer segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    /**
     * Devuelve el primer componente del par.
     *
     * @return el primer componente
     */
    public Integer getPrimero() {
        return primero;
    }

    /**
     * Devuelve el segundo componente del par.
     *
     * @return el segundo componente
     */
    public Integer getSegundo() {
        return segundo;
    }

    /**
     * Compara este par con otro objeto. Son iguales si el otro objeto también es
     * un {@code Par} y coinciden sus componentes en el mismo orden.
     *
     * @param obj objeto con el que se compara
     * @return {@code true} si ambos pares tienen los mismos componentes en el mismo orden
     */
    @Override
    public boolean equals(Object obj) {
        boolean iguales = false;
        if (this == obj) {
            iguales = true;
        } else if(obj instanceof Par) {
            Par otro = (Par) obj;
            iguales =  primero.equals(otro.primero) && segundo.equals(otro.segundo);
        }
        return iguales;
    }

    /**
     * Calcula el código hash a partir de ambos componentes, de forma consistente
     * con {@link #equals(Object)}.
     *
     * @return el código hash del par
     */
    @Override
    public int hashCode() {
        return Objects.hash(primero, segundo);
    }

    /**
     * Devuelve el par con el formato {@code (primero, segundo)}, por ejemplo {@code (4, 1)}.
     *
     * @return la representación en texto del par
     */
    @Override
    public String toString() {
        return "(" + primero + ", " + segundo + ")";
    }
    
}