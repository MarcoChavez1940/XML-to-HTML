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
    
    //--------------------------------------------------------------------------
    
    public void remplazarListado(String estilo){
        int indice1, indice2;
        String lista, remplazado, aux;
        int contador=0;
        
        indice1 = estilo.indexOf("{LISTADO");
        
        if(indice1 != -1){
            indice2 = estilo.indexOf("}LISTADO");
            
            lista = estilo.substring(indice1, indice2+8);
            
            remplazado = lista;
            
            //Saber cuantas ocurrencias de Asignatura hay.
            aux = remplazado;
            while (aux.indexOf("}ASIGNATURA") > -1) {
                aux = aux.substring(aux.indexOf(
                "}ASIGNATURA")+11,aux.length());
                contador++; 
                
            }
            
            
            //REMPLAZANDO ASIGNATURAS INTERNAS
            for(int i=0;i<contador;i++){
                remplazado = remplazarAsignatura(remplazado);
                
            }
            
            //
            
            if(remplazado.indexOf("@@") != -1)
                remplazado = remplazado.replace("@@", "Lista de notas");
            
            remplazado = remplazado.replace("{LISTADO", "");
            remplazado = remplazado.replace("}LISTADO", "");
            
            estilo = estilo.replace(lista, remplazado);
            System.out.println(estilo);
            
            
        }
    }
    
    public String remplazarAsignatura(String estilo){
        int indice1, indice2;
        String asignatura, remplazado;
        
        
        
        indice1 = estilo.indexOf("{ASIGNATURA");
        
        if(indice1 != -1){
            indice2 = estilo.indexOf("}ASIGNATURA");
            
            asignatura = estilo.substring(indice1, (indice2+11));
            remplazado = asignatura;
            
            
            /*
                Aqui iria la validacion de las demas reglas
            */
                                
            //Remplazar las reglas por los datos del XML
            if(remplazado.indexOf("@NOMBRE@") != -1)
                remplazado = remplazado.replaceAll("@NOMBRE@", "Marco");
            
            if(remplazado.indexOf("@CURSO@") != -1)
                remplazado = remplazado.replace("@CURSO@", "TLP");
            
            if(remplazado.indexOf("@PLAN@") != -1)
                remplazado = remplazado.replace("@PLAN@", listado.asignatura.plan); 
                      
            if(remplazado.indexOf("@@") != -1)
                remplazado = remplazado.replace("@@", "SOYOTRO");
            
            remplazado = remplazado.replace("{ASIGNATURA", "");
            remplazado = remplazado.replace("}ASIGNATURA", "");
            
            estilo = estilo.replace(asignatura, remplazado);
            
            
            
        }
        return estilo;
    }


    public void generarHTML(){
        
    }
    
   
    
    
}
