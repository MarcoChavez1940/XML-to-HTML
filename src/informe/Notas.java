/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informe;

import java.util.ArrayList;

/**
 *
 * @author Marco
 */
public class Notas {
    
    ArrayList<Alumno> alumnos;
    String incluido;

    public Notas(){
        alumnos = new ArrayList();
    }
    
    @Override
    public String toString() {
        return "Notas{" + "alumnos=" + alumnos + '}';
    }
    
    
    
}
