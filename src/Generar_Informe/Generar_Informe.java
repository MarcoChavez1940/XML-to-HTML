package Generar_Informe;

import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author Marco
 */
public class Generar_Informe {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        Procedimientos procedimiento = new Procedimientos();
        try {
            procedimiento.init(args[1], args[0]);    
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Falta de archivos");
        } catch(NullPointerException e){
            System.out.println("No existe un listado");
        }      
    }    
}
