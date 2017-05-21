/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Marco
 */
public class Procedimientos {
    
    Listado listado;
    String XML;
    
    public void reducirXML(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        
        //lectura
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        //escritura en auxiliar
        FileWriter fichero = new FileWriter("auxiliar.txt");
        PrintWriter pw = new PrintWriter(fichero);
        
        while((cadena = b.readLine())!=null) {
            pw.print(cadena.trim());
        }
        b.close();
        fichero.close();
    }
    
    public void crearListado() throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader("auxiliar.txt");
        BufferedReader b = new BufferedReader(f);
        
        if((cadena = b.readLine()) != null){
            if(cadena.indexOf("<listado>") != -1){
                this.XML = cadena;
                this.listado = new Listado();
            }
        }
        
        b.close();
    }
    
    public void ExtraerAsignatura(){
        int indice1, indice2;
        String asignatura, cadena1, cadena2;
        
        indice1 = XML.indexOf("<asignatura");
        if(indice1 != -1){
            indice2 = XML.indexOf("</asignatura>");
            
            asignatura = XML.substring(indice1, (indice2+13));
            cadena1= XML.substring(0, indice1);
            cadena2 = XML.substring((indice2+13), XML.length());
            
            //Crear asignatura y agregarlo al Listado.
            crearAsignatura(asignatura);
                               
            XML = cadena1.concat(cadena2);
        }   
    }
    public void crearAsignatura(String asignatura){
        String nombre, curso, plan;
        
        nombre = asignatura.substring(20, asignatura.indexOf("curso")-2);
        curso = asignatura.substring(asignatura.indexOf("curso")+7, asignatura.indexOf("plan")-2 );
        plan = asignatura.substring(asignatura.indexOf("plan")+6, asignatura.indexOf(">")-1);
               
        this.listado.asignatura = new Asignatura(nombre, curso, plan);       
    }
    
    public void ExtraerFecha(){
        int indice1, indice2;
        String fecha, cadena1, cadena2;
        
        indice1 = XML.indexOf("<fecha");
        if(indice1 != -1){
            indice2 = XML.indexOf("</fecha>");
            
            fecha = XML.substring(indice1, (indice2+8));
            cadena1= XML.substring(0, indice1);
            cadena2 = XML.substring((indice2+8), XML.length());
                       
            //Crear fecha y agregarlo al Listado.
            crearFecha(fecha);
                               
            XML = cadena1.concat(cadena2);
        }   
    }
    public void crearFecha(String fecha){
        String formato;
        
        formato = fecha.substring(16, fecha.indexOf(">")-1);       
             
        this.listado.fecha = new Fecha(formato);       
    }
    
    public void crearNotas(){            
        if(XML.indexOf("<notas>")!= -1){
            this.listado.notas = new Notas();           
        }   
    }
    
    
    public void ExtraerAlumnos(){
        int indice1, indice2;
        String alumnos, cadena1, cadena2;
        
        indice1 = XML.indexOf("<alumno");
        if(indice1 != -1){
            indice2 = XML.indexOf("</notas>");
            
            alumnos = XML.substring(indice1, (indice2));
            cadena1= XML.substring(0, indice1);
            cadena2 = XML.substring((indice2), XML.length());
            
                       
            //Crear alumnos y agregarlo a las notas.
            crearAlumnos(alumnos);
                               
            XML = cadena1.concat(cadena2);
        }   
    }
    public void crearAlumnos(String alumnos){
        String nombre, dni, nota;       
        while(alumnos.length()!=0){
            nombre = alumnos.substring(16, alumnos.indexOf("dni")-2);
            dni = alumnos.substring(alumnos.indexOf("dni")+5, alumnos.indexOf("nota")-2 );
            nota = alumnos.substring(alumnos.indexOf("nota")+6, alumnos.indexOf(">")-1);
            
            listado.notas.alumnos.add(new Alumno(nombre, dni, nota));
            
            alumnos = alumnos.substring(alumnos.indexOf(">")+10, alumnos.length());
           
        }             
    }
    
    
        
    
    
    
}
