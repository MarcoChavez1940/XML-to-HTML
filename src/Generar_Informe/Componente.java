package Generar_Informe;
/**
 * Clase correspondiente para los objectos tipo Componente.
 * @author Marco
 */
public abstract class Componente {
    
    /**
     * Texto incluido entre tags de la hoja de estilos.
     */
    protected String incluido;
    
    /**
     * Metodo para poder agregar un componente de tipo alumno a otro
     * componente que sea capaz de almacenar dicho componente.
     * @param componente Una instancia de tipo Componente.
     */
    public void agregarAlumno(Componente componente){
    }
}
