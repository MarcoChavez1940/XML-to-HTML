package informe;

import java.io.IOException;

/**
 *
 * @author Marco
 */
public class Informe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        
        Procedimientos pro = new Procedimientos();
        
        pro.reducirXML("archivo.txt");
        pro.crearListado();
        pro.crearNotas();
        
            
        pro.ExtraerAsignatura();
        pro.ExtraerFecha();
        pro.ExtraerAlumnos();
        
        
        
        Estilos estilos = new Estilos();
        estilos.remplazarListado("{LISTADO<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\">{ASIGNATURA<head><title>Notas de @NOMBRE@ @CURSO@</title></head>}ASIGNATURA<body><p style=\"font-size: 30pt\">@@</p>{ASIGNATURA<head><title>Notas de @NOMBRE@ @CURSO@</title></head>}ASIGNATURA}LISTADO");
        
        
        
    }
    
    
    
}
