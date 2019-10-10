/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lector;

/**
 *
 * @author Gustavo
 */
import java.io.*;
public class Archivo {
    String lectura="";
    public String leer(String nombre)
    {
        try{
            File f;
            FileReader lectorArchivo;
            f=new File(nombre);
            lectorArchivo = new FileReader(f);
            BufferedReader br = new BufferedReader(lectorArchivo);
            String l="";
            String aux="";
            
            
            while(true){
                aux=br.readLine();
                if(aux!=null)
                    l=l+aux+"\n";
                else
                    break;
                }
            br.close();
            lectorArchivo.close();
            return l;
        }
        catch(IOException e){
        System.out.println("Error:"+e.getMessage());
        }
        return null;
    }
    
        public String LeerGrafico()
    {
            File f;
            javax.swing.JFileChooser j= new javax.swing.JFileChooser();
            j.showOpenDialog(j);
            String path = j.getSelectedFile().getAbsolutePath();
            String lectura="";
            f=new File(path);
        try{
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String aux;
            
            while((aux=br.readLine())!=null)
                lectura=lectura+aux+"\n";
        }
        catch(IOException e){}
        return lectura;
        
    }
    
}
