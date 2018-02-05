
package obtenerdatos;

/**
 *
 * @author Gonzalo
 */
public class ObtenerDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ObtenerConsulta obtener=new ObtenerConsulta();
        obtener.obtenerArchivo();
        //obtener.Imprimir();
        obtener.SacarArchivo();
    }
    
}
