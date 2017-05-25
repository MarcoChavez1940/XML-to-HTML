package Generar_Informe;
/**
 * Clase correspondiente para los objectos tipo Alumno.
 * @author Marco
 */
public class Alumno extends Componente{
    
    /**
     * Nombre del alumno.
     */
    public String nombre;
    /**
     * Dni del alumno.
     */
    public String dni;
    /**
     * Nota (calificación) del alumno.
     */
    public String nota;

    /**
     * Contructor para crear un nuevo alumno con sus atributos.
     * @param nombre nombre del alumno.
     * @param dni   dni del alumno.
     * @param nota  nota del alumno.
     * @param incluido texto incluido entre tags del alumno.
     */
    
    public Alumno(String nombre, String dni, String nota, String incluido) {
        this.nombre = nombre;
        this.dni = dni;
        this.nota = nota;
        this.incluido = incluido;
    }
    
    /**
     * Metodo proporcionado de la clase heredada Componente
     * se sobreescribio de forma que no pueda ser usada.
     * @param componente Una instancia de tipo Componente.
     */
    @Override
    public void agregarAlumno(Componente componente){
        System.out.println("Alumno no contiene otros alumnos.");
    }
 
}
