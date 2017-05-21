package informe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

/**
 *
 * @author Marco
 */
public class Estilos {

    Listado listado;
    String estilo;
    Stack pila;
    
    public Estilos(Listado listado){
        this.listado=listado;
    }

    public Estilos() {
    }
    
    public void reducirEstilo(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        //lectura
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        //escritura en auxiliar
        FileWriter fichero = new FileWriter("auxiliarEstilo.txt");
        PrintWriter pw = new PrintWriter(fichero);
        
        while((cadena = b.readLine())!=null) {
            pw.print(cadena.trim());
        }
        b.close();
        fichero.close();
    }
    
    public void getEstiloReducido() throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader("auxiliarEstilo.txt");
        BufferedReader b = new BufferedReader(f);
        
        if((cadena = b.readLine()) != null){
            this.estilo=cadena;  
        }
    }
    
    public void remplazarAsignatura(String estilo){
        int indice1, indice2;
        String asignatura;
        
        indice1 = estilo.indexOf("{ASIGNATURA");
        
        if(indice1 != -1){
            indice2 = estilo.indexOf("}ASIGNATURA");
            
            asignatura = estilo.substring(indice1, (indice2+13));
            
            System.out.println(asignatura);
        }
    }


    public void generarHTML(){
        
    }
    
   
    
    
}
