package Generar_Informe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Esta clase realiza todo lo correspondiente a la manipulacion del archivo.
 * del XML.
 * @author Marco
 */
public class Procedimientos {
    
    /**
     * Objecto tipo Listado
     */
    public Listado listado;
    /**
     * Cadena del XML (minimificado).
     */
    public String XML;
    
    /**
     * Método que da inicio a todo el proceso implicado para transformar la
     * hoja de estilo junto con el xml en un HTML.
     * @param XML   nombre del archivo XML (.xml).
     * @param archivoEstilo nombre del archivo de hoja de estilo (.pl).
     * 
     */
    public void init(String XML, String archivoEstilo) throws IOException{
        reducirXML("Generar_Informe/" + XML);
        crearListado();
        crearNotas();    
        ExtraerAsignatura();
        ExtraerFecha();
        ExtraerAlumnos();
        
        Estilos estilos = new Estilos(this.listado);
        estilos.reducirEstilo("Generar_Informe/" + archivoEstilo);
        estilos.getEstiloReducido();
        
        estilos.remplazarListado(estilos.estilo);
        estilos.generarHTML();
    }
    
    
    /**
     * Método que minimifica el archivo XML.
     * @param archivo   nombre del archivo XML a minimificar.
     * @throws FileNotFoundException Excepcion lanzada en caso de no encontrar
     * dicho archivo en el directorio.
     * 
     */
    public void reducirXML(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        
        //lectura
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        //escritura en auxiliar
        FileWriter fichero = new FileWriter("auxiliarXML.txt");
        PrintWriter pw = new PrintWriter(fichero);
        
        while((cadena = b.readLine())!=null) {
            pw.print(cadena.trim());
        }
        b.close();
        fichero.close();
    }
    
    
    /**
     * Metodo para obtener el xml minimificado, ademas de validar si existe
     * un listado en ella. si es asi se inicializa el atributo listado.
     */
    public void crearListado() throws FileNotFoundException, IOException{
        String cadena, incluido;
        FileReader f = new FileReader("auxiliarXML.txt");
        BufferedReader b = new BufferedReader(f);
        
        if((cadena = b.readLine()) != null){
            if(cadena.indexOf("<listado>") != -1){
                this.XML = cadena;
                incluido = cadena.substring(cadena.indexOf("<listado>")+9, cadena.indexOf("<asignatura"));
                this.listado = new Listado(incluido);
                
                
            }
        }
        b.close();
    }
    
    /**
     * Método para extraer las etiquetas asignaturas existentes en el xml
     * minificado.
     */
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
    
    /**
     * Método para poder crear una asignatura a partir de la etiqueta extraida
     * del xml minificado
     * @param asignatura etiqueta de asignatura del xml.
     */
    public void crearAsignatura(String asignatura){
        String nombre, curso, plan, incluido;
        
        System.out.println(asignatura);
        
        nombre = asignatura.substring(20, asignatura.indexOf("curso")-2);
        curso = asignatura.substring(asignatura.indexOf("curso")+7, asignatura.indexOf("plan")-2 );
        plan = asignatura.substring(asignatura.indexOf("plan")+6, asignatura.indexOf(">")-1);
        incluido = asignatura.substring(asignatura.indexOf(">")+1,asignatura.indexOf("</"));
               
        this.listado.asignatura = new Asignatura(nombre, curso, plan, incluido);       
    }
    
    public static void main(String[] args) {
        Procedimientos pro = new Procedimientos();
        pro.crearAsignatura("<asignatura nombre=\"Estructuras de Datos II\" curso=\"04-05\" plan=\"1996\"></asignatura>");
    }
    
    /**
     * Método para extraer las etiquetas fecha existentes en el xml
     * minificado.
     */
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
    
    /**
     * Método para poder crear una fecha a partir de la etiqueta extraida
     * del xml minificado
     * @param fecha etiqueta de fecha del xml.
     */
    public void crearFecha(String fecha){
        String formato, incluido;
        
        formato = fecha.substring(16, fecha.indexOf(">")-1); 
        incluido = fecha.substring(fecha.indexOf(">")+1,fecha.indexOf("</"));
        
        this.listado.fecha = new Fecha(formato, incluido);       
    }
    
    /**
     * Método que verifica si hay la existencia de una etiqueta notas en el 
     * xml, si es asi inicializa notas que es un atributo de listado.
     */
    public void crearNotas(){        
        String incluido;
        if(XML.indexOf("<notas>")!= -1){
            incluido = XML.substring(XML.indexOf("<notas>")+7, XML.indexOf("<alumno"));
            this.listado.notas = new Notas(incluido);
        }   
    }
    
    /**
     * Método para extraer las etiquetas alumno existentes en el xml
     * minificado.
     */
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
    
    /**
     * Método para poder crear los alumnos a partir de las etiqueta extraidas
     * del xml minificado, estos alumnos son agregados a notas que es un
     * atributo de listado.
     * @param alumnos etiquetas de alumnos del xml.
     */
    public void crearAlumnos(String alumnos){
        String nombre, dni, nota, incluido;
        
        while(alumnos.length()!=0){
            
            nombre = alumnos.substring(16, alumnos.indexOf("dni")-2);
            dni = alumnos.substring(alumnos.indexOf("dni")+5, alumnos.indexOf("nota")-2 );
            nota = alumnos.substring(alumnos.indexOf("nota")+6, alumnos.indexOf(">")-1);
            incluido = alumnos.substring(alumnos.indexOf(">")+1, alumnos.indexOf("</"));
            
            listado.notas.agregarAlumno(new Alumno(nombre,dni,nota,incluido));
            
            alumnos = alumnos.substring(alumnos.indexOf("</")+9, alumnos.length());
           
        }             
    }
    
}
