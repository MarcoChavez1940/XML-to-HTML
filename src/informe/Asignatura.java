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
public class Asignatura {
    
    String nombre;
    String curso;
    String plan;
    String incluido;

    public Asignatura(String nombre, String curso, String plan) {
        this.nombre = nombre;
        this.curso = curso;
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "nombre=" + nombre + ", curso=" + curso + ", plan=" + plan + '}';
    }
    
    
    
}
