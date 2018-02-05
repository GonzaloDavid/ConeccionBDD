
package obtenerdatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gonzalo
 */
public class Conexion {
    public Connection cadena_coneccion()
    {
    Connection cn=null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn=DriverManager.getConnection("jdbc:sqlserver://localhost\\sqlnet:1433;databaseName=AdventureWorksDW2012","sa","david");
            
        } catch (Exception e) {
            System.out.println("ERROR EN CLASE CONECCION"+e);
        }
        return cn;
    }
    
}
