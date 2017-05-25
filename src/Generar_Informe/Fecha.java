package Generar_Informe;
/**
 * Clase correspondiente para los objectos tipo Fecha.
 * @author Marco
 */
public class Fecha extends Componente{
    
    /**
     * Formato de la fecha.
     */
    public String formato;

    /**
     * Contructor para crear una nueva fecha con sus atributos.
     * @param formato   formato de la fecha.
     * @param incluido texto incluido entre tags de la asignatura.
     */
    public Fecha(String formato, String incluido) {
        this.formato = formato;
        this.incluido = incluido;
    }
    
    /**
     * Metodo proporcionado de la clase heredada Componente
     * se sobreescribio de forma que no pueda ser usada.
     * @param componente Una instancia de tipo Componente.
     */
    @Override
    public void agregarAlumno(Componente componente){
        System.out.println("Fecha no contiene alumnos");
    } 
}
