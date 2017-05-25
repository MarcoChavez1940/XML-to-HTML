package Generar_Informe;

import java.util.ArrayList;
/**
 * Clase correspondiente para los objectos tipo Nota.
 * @author Marco
 */
public class Notas extends Componente{
    
    /**
     * ArrayList de todos los alumnos del listado.
     */
    public ArrayList<Alumno> alumnos;

    /**
     * Contructor para crear una clase Notas, inicializado su ArrayList y 
     * su atributo incluido.
     * @param incluido texto incluido entre tags de la asignatura.
     */
    public Notas(String incluido){
        alumnos = new ArrayList();
        this.incluido = incluido;
    }
    
    /**
     * Metodo proporcionado de la clase heredada Componente
     * los componentes recibidos tipo alumnos son agregados al arraylist
     * de alumnos.
     * @param componente Una instancia de tipo Componente.
     */
    @Override
    public void agregarAlumno(Componente componente){
        alumnos.add((Alumno) componente);
    }

}
