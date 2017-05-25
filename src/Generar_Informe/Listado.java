package Generar_Informe;
/**
 * Clase correspondiente para los objectos tipo Listado.
 * @author Marco
 */
public class Listado extends Componente{
    
    /**
     * Objecto tipo Asignatura asociado al listado.
     */
    public Asignatura asignatura;
    /**
     * Objecto tipo Fecha asociado al listado.
     */
    public Fecha fecha;
    /**
     * Objecto tipo notas(que contiene alumnos) asociado al listado.
     */
    public Notas notas;
    
    /**
     * Contructor para crear un nuevo listado con su atributo incluido.
     * @param incluido texto incluido entre tags de la asignatura.
     */
    public Listado(String incluido){
        this.incluido = incluido;
    }

    /**
     * Metodo proporcionado de la clase heredada Componente
     * se sobreescribio de forma que no pueda ser usada.
     * @param componente Una instancia de tipo Componente.
     */
    @Override
    public void agregarAlumno(Componente componente){
        System.out.println("Listado no contiene alumnos");
    }
    
}
