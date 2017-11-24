package Ventanas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

/**
 *
 * @author keviu
 */
public class ArbolBB {
      
      NodoArbol raiz;
      String RutaArbol="c:\\MEIA\\Imagenes.txt";
      String RutaDesArbol="c:\\MEIA\\DesciptorImagenes.txt";
      FileMethods archivos = new FileMethods();
      String resultados;
      int cantidad=0;
    public ArbolBB(){
        archivos.createFile(RutaArbol);
        archivos.createFile(RutaArbol);
        if(archivos.fileSizeNotZero(RutaArbol))
            {   raiz= new NodoArbol();         
                raiz.leernodo(0);                
            }
        else
        {
               raiz=null;
        }
         
    }
 
    public boolean esVacio(){
        return (raiz == null);
    }
 
    public void insertar(String dato,NodoArbol actual,String usuario) throws IOException{
        
        NodoArbol nuevo = new NodoArbol(dato,usuario);
        if (actual==null) {
            raiz = nuevo;
            raiz.num=1;
            raiz.Guardarnodo();
        }
        else {
            nuevo.conteoRegistros(RutaArbol);
            
            
            if (actual.dato.compareTo(nuevo.dato) < 0) {
                if (actual.rizq == -1)
                {
                    //actual.Izq = nuevo;
                    actual.regis=actual.num-1;
                    actual.rizq=nuevo.regis+1;
                    actual.Actualizar();
                    nuevo.num=nuevo.regis+1;
                    nuevo.Guardarnodo();
                 
                }
                else{
                    actual.leernodo(actual.rizq-1);
                    insertar( dato,actual,usuario);
                }
                    
            }
            else if (actual.dato.compareTo(nuevo.dato) > 0){
                if (actual.rder == -1)
                {
                    actual.regis=actual.num-1;
                    actual.rder=nuevo.regis+1;
                    actual.Actualizar();
                    nuevo.num=nuevo.regis+1;
                    nuevo.Guardarnodo();
                    
                    //actual.Der = nuevo;
                }
                else{
                     actual.leernodo(actual.rder-1);
                    insertar( dato,actual,usuario);
                }
                
                    
                   
            }
        }
    }
    
public String busquedaInterna( String cadena) throws FileNotFoundException, IOException
    {
        String Salida = "";
        File Archivo = new File(RutaArbol);
            if(Archivo.exists())
            {
            int númLinea = 0;
            RandomAccessFile LeerArchivo = new RandomAccessFile(Archivo,"rw");
            String Linea = LeerArchivo.readLine();
            String[] values;
            while(Linea != null)  //Leemos linea por linea hasta el final.
            {
                if(!"".equals(Linea))
                {
                    
                        User newUser=new User();
                        values = Linea.split(Pattern.quote("|"));
                        if(cadena.equals(values[3].replaceAll("#", "" )))
                            {
                                Salida += values[4].replaceAll("#", "")+ "|";
                            }
                        
   
                }
                númLinea=(int)LeerArchivo.getFilePointer();
                Linea=LeerArchivo.readLine();
            }
            LeerArchivo.close();
            }
            
          return Salida;

    }

 
    
    
}
