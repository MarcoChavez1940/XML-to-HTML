package Generar_Informe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Esta clase realiza todo lo correspondiente a la manipulacion del archivo
 * de hoja de espilos (.pl).
 * @author Marco
 */
public class Estilos {

    /**
     * Objecto tipo listado.
     */
    public Listado listado;
    /**
     * Cadena de la hoja de estilo (minimificado).
     */
    public String estilo;
    
    /**
     * Contructor para crear un objeto tipo Estilos.
     * @param listado recibe un listado (proveniente de la clase
     * Procedimiento).
     */
    public Estilos(Listado listado){
        this.listado=listado;
    }

    /**
     * Método que minimifica el archivo de la hoja de estilos.
     * @param hojaEstilo nombre del archivo de hoja de estilos.
     * @throws FileNotFoundException lanzado en caso de no hayar el archivo en
     * el directorio
     */
    public void reducirEstilo(String hojaEstilo) throws FileNotFoundException, IOException{
        String cadena;
        //lectura
        FileReader f = new FileReader(hojaEstilo);
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
    
    /**
     * Metodo para obtener la hoja de estilos minimificado. 
     */
    public void getEstiloReducido() throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader("auxiliarEstilo.txt");
        BufferedReader b = new BufferedReader(f);
        
        if((cadena = b.readLine()) != null){
            this.estilo=cadena;  
        }
    }
    
    /**
     * Método que busca remplazar las reglas de listado con base la informacion
     * dada en el XML.
     * @param estilo recibe la regla de asignatura para poder sustituir la
     * la informacion dada en el XML
     */
    public void remplazarListado(String estilo){
        
        int indice1, indice2;
        String lista, remplazado, aux;
        int contador=0;
        
        indice1 = estilo.indexOf("{LISTADO");
        
        if(indice1 != -1){
            indice2 = estilo.indexOf("}LISTADO");
            lista = estilo.substring(indice1, indice2+8);
            remplazado = lista;
            
            //Remplazar todos los alumnos
            remplazado = remplazarAlumnos(remplazado);
            //------------------------------------------------------------------
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
            //------------------------------------------------------------------
            
            //Remplazar las fechas
            remplazado = remplazarFecha(remplazado);
            
            //Remplazar notas
            remplazado = remplazarNotas(remplazado);
            
            if(remplazado.indexOf("@@") != -1)
                remplazado = remplazado.replace("@@", listado.incluido);
            
            remplazado = remplazado.replace("{LISTADO", "");
            remplazado = remplazado.replace("}LISTADO", "");
            
            estilo = estilo.replace(lista, remplazado);
            //System.out.println(estilo);
            this.estilo = estilo;
        }
    }
    
    /**
     * Método que busca remplazar las reglas de asignatura con base la informacion
     * dada en el XML.
     * @param estilo recibe la regla de asignatura para poder sustituir la
     * la informacion dada en el XML.
     * @return retorna la seccion de la hoja de estilo (asignatura) ya con los
     * remplazamientos por parte del XML.
     */
    public String remplazarAsignatura(String estilo){
        int indice1, indice2;
        String asignatura, remplazado;
        
        indice1 = estilo.indexOf("{ASIGNATURA");
        
        if(indice1 != -1){
            indice2 = estilo.indexOf("}ASIGNATURA");
            
            asignatura = estilo.substring(indice1, (indice2+11));
            remplazado = asignatura;
            
            //Remplazar las reglas por los datos del XML
            if(remplazado.indexOf("@NOMBRE@") != -1)
                remplazado = remplazado.replace("@NOMBRE@", listado.asignatura.nombre);
            
            if(remplazado.indexOf("@CURSO@") != -1)
                remplazado = remplazado.replace("@CURSO@", listado.asignatura.curso);
            
            if(remplazado.indexOf("@PLAN@") != -1)
                remplazado = remplazado.replace("@PLAN@", listado.asignatura.plan); 
                      
            if(remplazado.indexOf("@@") != -1)
                remplazado = remplazado.replace("@@", listado.asignatura.incluido);
            
            remplazado = remplazado.replace("{ASIGNATURA", "");
            remplazado = remplazado.replace("}ASIGNATURA", "");
            
            estilo = estilo.replace(asignatura, remplazado);
  
        }
        return estilo;
    }
    
    /**
     * Método que busca remplazar las reglas de fecha con base la informacion
     * dada en el XML.
     * @param estilo recibe la regla de fecha para poder sustituir la
     * la informacion dada en el XML.
     * @return retorna la seccion de la hoja de estilo (fecha) ya con los
     * remplazamientos por parte del XML.
     */
    public String remplazarFecha(String estilo){
        int indice1, indice2;
        String fecha, remplazado;
        
        indice1 = estilo.indexOf("{FECHA");
        
        if(indice1 != -1){
            indice2 = estilo.indexOf("}FECHA");
            
            fecha = estilo.substring(indice1, (indice2+11));
            remplazado = fecha;
            
            //Remplazar las reglas por los datos del XML
            if(remplazado.indexOf("@FORMATO@") != -1)
                remplazado = remplazado.replace("@FORMATO@", listado.fecha.formato); 
                      
            if(remplazado.indexOf("@@") != -1)
                remplazado = remplazado.replace("@@", listado.fecha.incluido);
            
            remplazado = remplazado.replace("{FECHA", "");
            remplazado = remplazado.replace("}FECHA", "");
            
            estilo = estilo.replace(fecha, remplazado);
        }
        return estilo;
    }
    
    
    /**
     * Método que busca remplazar las reglas de notas con base la informacion
     * dada en el XML.
     * @param estilo recibe la regla de notas para poder sustituir la
     * la informacion dada en el XML.
     * @return retorna la seccion de la hoja de estilo (notas) ya con los
     * remplazamientos por parte del XML.
     */
    public String remplazarNotas(String estilo){
        
        int indice1, indice2;
        String notas, remplazado;
        
        indice1 = estilo.indexOf("{NOTAS");
        if(indice1 != -1){
            indice2 = estilo.indexOf("}NOTAS");
            
            notas = estilo.substring(indice1, (indice2+6));
            remplazado = notas;
            
            if(remplazado.indexOf("@@") != -1)
                remplazado = remplazado.replace("@@", listado.notas.incluido);
            
            remplazado = remplazado.replace("{NOTAS", "");
            remplazado = remplazado.replace("}NOTAS", "");
            
            estilo = estilo.replace(notas, remplazado);
            
        }
        return estilo;
    }
    
    
    /**
     * Método que busca remplazar las reglas de alumnos con base la informacion
     * dada en el XML.
     * @param estilo recibe la regla de alumnos para poder sustituir la
     * la informacion dada en el XML (todos los alumnos dados en el).
     * @return retorna la seccion de la hoja de estilo (alumno) ya con los
     * remplazamientos por parte del XML.
     */
    public String remplazarAlumnos(String estilo){
        int indice1, indice2;
        String alumno, remplazado="";
        
        indice1 = estilo.indexOf("{ALUMNO");
        if(indice1 != -1){
            indice2 = estilo.indexOf("}ALUMNO");
            
            alumno = estilo.substring(indice1, (indice2+7));
           
            for(int i=0;i<listado.notas.alumnos.size();i++){
                remplazado = remplazado + alumno;
                
                if(remplazado.indexOf("@NOMBRE@") != -1)
                    remplazado = remplazado.replace("@NOMBRE@", listado.notas.alumnos.get(i).nombre);
                if(remplazado.indexOf("@DNI@") != -1)
                    remplazado = remplazado.replace("@DNI@", listado.notas.alumnos.get(i).dni);
                if(remplazado.indexOf("@NOTA@") != -1)
                    remplazado = remplazado.replace("@NOTA@", listado.notas.alumnos.get(i).nota);
                if(remplazado.indexOf("@@") != -1)
                    remplazado = remplazado.replace("@@", listado.notas.alumnos.get(i).incluido);
                
                remplazado = remplazado.replace("{ALUMNO", "");
                remplazado = remplazado.replace("}ALUMNO", "");              
            }
            estilo = estilo.replace(alumno, remplazado);    
        }
        return estilo;
    }

    /**
     * Método que genera un html con la hoja de estilos ya con todas las
     * sustituciones de las reglas apoyada con los datos otorgada por el XML.
     * @throws IOException  Excepción lanzada si no se encuentra el archivo
     * en el directorio.
     */
    public void generarHTML() throws IOException{
        FileWriter fichero = new FileWriter("Generar_Informe/informe.html");
        PrintWriter pw = new PrintWriter(fichero);
        pw.print(this.estilo);   
        fichero.close();
        System.out.println("informe.html creado con exito");
    }  
}
