/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informe;

/**
 *
 * @author Marco
 */
public class Alumno {
    
    String nombre;
    String dni;
    String nota;
    String incluido;

    public Alumno(String nombre, String dni, String nota) {
        this.nombre = nombre;
        this.dni = dni;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", dni=" + dni + ", nota=" + nota + '}';
    }
    
    
    
}
