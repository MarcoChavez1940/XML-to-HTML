package Generar_Informe;
/**
 * Clase correspondiente para los objectos tipo Asignatura.
 * @author Marco
 */
public class Asignatura extends Componente{
    
    /**
     * Nombre de la asignatura.
     */
    public String nombre;
    /**
     * Curso de la asignatura.
     */
    public String curso;
    /**
     * Plan de la asignatura.
     */
    public String plan;

    /**
     * Contructor para crear una nueva asignatura con sus atributos.
     * @param nombre    nombre de la asignatura.
     * @param curso curso de la asignatura.
     * @param plan  plan de la asignatura.
     * @param incluido texto incluido entre tags de la asignatura.
     */
    public Asignatura(String nombre, String curso, String plan, String incluido) {
        this.nombre = nombre;
        this.curso = curso;
        this.plan = plan;
        this.incluido = incluido;
    }
    
    /**
     * Metodo proporcionado de la clase heredada Componente
     * se sobreescribio de forma que no pueda ser usada.
     * @param componente Una instancia de tipo Componente.
     */
    @Override
    public void agregarAlumno(Componente componente){
        System.out.println("Asignatura no contiene alumnos");
    }

}
