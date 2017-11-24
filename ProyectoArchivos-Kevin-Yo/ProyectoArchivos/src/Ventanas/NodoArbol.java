/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author keviu
 */
public class NodoArbol {
    
        public NodoArbol Der;
        public NodoArbol Izq;
        public int rder;
        public int rizq;
        public String dato;
        public String linea;
        public int num;
        public int regis;
        String RutaArbol="c:\\MEIA\\Imagenes.txt";
        FileMethods Metodos = new FileMethods();
        String RutaDesArbol="c:\\MEIA\\DesciptorImagenes.txt";
        String user;

        public NodoArbol(String valor,String actual){
            Der = null;
            Izq = null;
            rder=-1;
            rizq=-1;
            user=actual;
            dato = rellenar(actual,20,"#")+rellenar(valor,80,"#");
        }
            public NodoArbol(){
            
        }
        public void Guardarnodo() throws IOException{
            File archivo = new File(RutaArbol);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(num+"|"+String.format("%09d", rder)+"|"+String.format("%09d", rizq)+"|"+dato+(new Date()).toString()+"|"+1+ System.getProperty("line.separator"));
                Escribir.close();
                ActualizarDescriptor(RutaDesArbol,user);
        }
         public String rellenar(String var,int cantidad,String relleno) {
             String text="";
        text+=var;
        for (int i = var.length(); i < cantidad; i++) {
           text+=relleno; 
        }
        text+="|"; 
        return text;
    }
        public void leernodo(int registro){
           FileMethods metodos= new FileMethods();
          
        File archivo = new File(RutaArbol);      
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(registro*158);
            linea=raf.readLine();
            raf.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
            String[] values = linea.split(Pattern.quote("|"));
            num=Integer.parseInt(values[0]);
            rder=Integer.parseInt(values[1]);
            rizq=Integer.parseInt(values[2]);
            dato = values[3]+"|"+values[4];
            user= values[3].replaceAll("#", "");
            
        }
        public void ActualizarDescriptor(String direccion,String cadena)
    {
        try
        {
            File descriptor = new File(direccion);
            if(!descriptor.exists())
            {
                Metodos.createFile(direccion);
                FileWriter escritor = new FileWriter(descriptor);
                Date fecha = new Date();
                if(direccion.equals(RutaDesArbol))
                {
                    escritor.write("Archivo:"+RutaArbol+"\r\nDescripción: Imagenes_usuario\r\nTipo: archivo_de_datos\r\nOrganización: Arbol Binario\r\nAutor: "+cadena+ "\r\nCreado: "+ fecha+ "\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0\r\nRegistros_máximo: 3");
                }
                
                escritor.close();
            }
            else
            {
                if(direccion.equals(RutaDesArbol))
                {
                    String[] Registros = conteoRegistros(RutaArbol).split("|");
                    Date Fecha = new Date();
                    RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
                    for (int j = 0; j < 6; j++) {
                        raf.readLine();
                    }
                    
                    raf.writeBytes("Modificado: "+Fecha+"\r\n");
                    raf.readLine();
                    raf.writeBytes("Registros_activos: "+Registros[0]+"\r\n");
                    raf.writeBytes("Registros_inactivos: "+Registros[2]+"\r\n");
                    raf.close();
                }
                
            }
        }
        catch(IOException e)
        {
            
        }
    }
        public String conteoRegistros(String Direccion)
    {
        int contadorActivo = 0;
        int contadorInactivo = 0;
        try
        {
            File Archivo = new File(Direccion);
            FileReader lector = new FileReader(Archivo);
            BufferedReader leerArchivo = new BufferedReader(lector);
            String Linea = leerArchivo.readLine();
            String[] Registro;
            while(Linea != null)
            {
                Registro = Linea.split(Pattern.quote("|"));
                if("1".equals(Registro[Registro.length -1]))
                {
                    contadorActivo++;
                }
                else
                {
                    contadorInactivo++;
                }
                Linea = leerArchivo.readLine();
            }
            regis=contadorActivo;
            lector.close();
            leerArchivo.close();
        }
        catch(IOException ex)
        {
        }
        return contadorActivo + "|" + contadorInactivo;
    }
            public void Actualizar() throws IOException
     {
        
        File archivo = new File(RutaArbol);      
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(regis*158);
            raf.writeBytes(num+"|"+String.format("%09d", rder)+"|"+String.format("%09d", rizq)+"|"+dato+"|"+(new Date()).toString()+"|"+1+ System.getProperty("line.separator"));
            
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        
        
    
    
}
