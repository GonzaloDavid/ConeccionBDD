
package obtenerdatos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author Gonzalo
 */
public class ObtenerConsulta extends Conexion {
    ArrayList<String> lista=new ArrayList();
    Connection cn=cadena_coneccion();
    public void obtenerArchivo()
    {
    String sqlConsulta="SELECT         c.GeographyKey, c.NameStyle, c.MaritalStatus, c.Gender, c.YearlyIncome, \n" +
"                         c.TotalChildren, c.NumberChildrenAtHome, c.EnglishEducation, c.SpanishEducation, c.FrenchEducation, c.EnglishOccupation, c.SpanishOccupation, c.FrenchOccupation, c.HouseOwnerFlag, c.NumberCarsOwned, \n" +
"                           c.CommuteDistance, x.Region, x.Age, CASE x.[Bikes] WHEN 0 THEN 0 ELSE 1 END AS BikeBuyer\n" +
"FROM            dbo.DimCustomer AS c INNER JOIN\n" +
"                             (SELECT        CustomerKey, Region, Age, SUM(CASE [EnglishProductCategoryName] WHEN 'Bikes' THEN 1 ELSE 0 END) AS Bikes\n" +
"                               FROM            dbo.vDMPrep\n" +
"                               GROUP BY CustomerKey, Region, Age) AS x ON c.CustomerKey = x.CustomerKey";
        try {
            String var="";
            PreparedStatement ps=null;
            ResultSet rs=null;
            ps=cn.prepareStatement(sqlConsulta);
            rs=ps.executeQuery();
            
             while (rs.next()) {
                Object[] datos = new Object[19];
                 var="";
                for (int i = 0; i < 19; i++) {
                   
                    if(i==18)
                    {
                     
                    datos[i] = rs.getString(i + 1);
                    var+="'"+datos[i]+"'";
                        
                    }else{
                    
                    datos[i] = rs.getString(i + 1);
                    var+="'"+datos[i]+"',";
                    }
                }
                lista.add(var);
               
            }
            ps.close();
           
            
        } catch (Exception e) {
        }
    
    }
    public void Imprimir()
    {
        for (int i = 0; i <lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }
    public void SacarArchivo()
    {
        String aux="";
    try{
            String direccion1="C:\\Users\\Gonzalo\\Documents\\BI\\datosBI.arff";
		
		
		File abrir=new File (direccion1);
		FileWriter  w=new FileWriter(abrir);
		BufferedWriter bw = new BufferedWriter(w);
		PrintWriter wr =new PrintWriter(bw);
                
                for (int i = 0; i <lista.size(); i++) {
                     aux+=lista.get(i)+"\n";
           
        }
		wr.write(aux);
                
            
		wr.close();
		bw.close();
	}catch(Exception ex)
	{
		JOptionPane.showMessageDialog(null, "La direccion del archivo esta incorrecta");
	}
    
    
    }
    
}
