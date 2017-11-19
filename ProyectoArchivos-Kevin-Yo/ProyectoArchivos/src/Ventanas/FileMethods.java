/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;
import java.awt.Component;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author jsala
 */
public class FileMethods {
    
    //Constructor
    public FileMethods()
    {}
    static final Logger LOGGER = Logger.getAnonymousLogger();
    //Crear una nueva carpeta
    public void createFolder(String path){
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    //Crear un nuevo archivo
    public void createFile(String fileName){
        File userFile = new File(fileName); 
         try{
                userFile.createNewFile();
          }catch(IOException ex){
                JOptionPane.showMessageDialog(null, ex.toString());
           }
    }
    public boolean loginMethod(String nombre, String contraseña, User newUser)
    {
        File ArchivoMaestro = new File("c:\\MEIA\\Usuarios.txt");
        if(ArchivoMaestro.exists())
        {
            if(loginMethodInterno(nombre,contraseña,"c:\\MEIA\\BitácoraUsuarios.txt",newUser))
            {
                return true;
            }
            else
            {
                return loginMethodInterno(nombre,contraseña,"c:\\MEIA\\Usuarios.txt",newUser);
            }   
        }
        else
        {
            return loginMethodInterno(nombre,contraseña,"c:\\MEIA\\BitácoraUsuarios.txt",newUser);
        }
    }
    //Revisa que el usuario y contraseña ingresadas sean válidas.
    private boolean loginMethodInterno(String name, String password, String path, User newUser){
        File filee = new File(path);
        FileReader lecturaArchivo;
            try
            {
                lecturaArchivo = new FileReader(filee);
                BufferedReader readFile = new BufferedReader(lecturaArchivo);   
                String line = "";
                try
                {
                   line = readFile.readLine();
                   String[] values;
                   String contraseñaEncriptada = EncriptadoMD5(password);
                    while (line != null) {
                        if (line !="") {
                            values = line.split("\\|");
                            newUser.setUser(values[0]);
                                newUser.setName(values[1]);
                                newUser.setLastName(values[2]);
                                newUser.setPassword(contraseñaEncriptada);
                                newUser.setRol(values[4]);
                                newUser.setDate(values[5]);
                                newUser.setEmail(values[6]);
                                newUser.setCelNumber(Integer.parseInt(values[7]));
                                newUser.setPhotoPath(values[8]);
                                newUser.setDescription(values[9]);
                                newUser.setStatus(Integer.parseInt(values[10]));
                            if (CompareStrings(name, newUser.getUser()) && CompareStrings(contraseñaEncriptada, values[3])) { //son iguales, así que se crea el objeto user
                                
                                //Se asigna valores al objeto usuario actual para poder manejarlo en los siguientes forms
                               return true;
                            }
                            
                            
                        }
                        line = readFile.readLine();
                    }
                    lecturaArchivo.close();
                    readFile.close();
                    return false;
                                    }
                catch(IOException ex){
                    
                    return false;
                }             
            }
            catch(FileNotFoundException e)
            {
               
                return false;            
            }         
       
    }
    //Revisa que existan datos en un archivo.
    public boolean fileSizeNotZero(String path){
        File b = new File(path);
        if (b.length() == 0) {
            return false;
        }
        else{
            return true;
        }
    }
    //Función que compara la contraseña ingresada con la guardada en el archivo.
    public boolean CompareStrings(String inputPassword, String correctPassword){
        String b = inputPassword.replaceAll(" ", "");
        int ib= b.length();
        String n = correctPassword.replaceAll(" ", "");
        int in= n.length();
        char[] a = n.toCharArray();
        if (b.length() != n.length()) {
            return false;
        }else{
            return inputPassword.equals(correctPassword);
        }
        
    }

    //Escribe en la bitácora de registros de copias de seguridad.
    public boolean writeOnBitacora(String path, String filePath,String user, String date){
        File file = new File(path);
        try
            {
                FileWriter write = new FileWriter(file, true);
                write.write( filePath +"|" + user +"|"+date+ System.getProperty("line.separator"));
                write.close();
                return true;
            }
            catch(IOException e)
            {
                return false;
            }
     
    }
    //Procedimiento que permite seleccionar una nueva fotografía de perfíl
    public String fotoPerfil(Component a)
    {
     String nuevaRuta = "";
     JFileChooser dialog = new JFileChooser();
     FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes", "png","jpg");
     File ficheroImagen;
     dialog.setFileFilter(filtro);
     if(dialog.showOpenDialog(a) == JFileChooser.APPROVE_OPTION)
     {
         ficheroImagen = dialog.getSelectedFile();
         nuevaRuta = ficheroImagen.getPath();
     }
     return nuevaRuta;
    }
    //Encriptar contraseña
    public String EncriptadoMD5(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuffer encrypt = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
            encrypt.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }       
        return encrypt.toString();
        }catch (java.security.NoSuchAlgorithmException e) {
        }
               
        return null;         
    }
    //Método para abrir un archivo desde la aplicación de Java
    public void AbrirArchivo(String ruta){
        try{
            File file = new File(ruta);
            Desktop.getDesktop().open(file);
        }catch(IOException ex){
            //Se generó un error
        }
    }
     //Metodo para copiar todos los archivos a una nueva ruta
    private void CopiarArchivosCarpeta(String origin, String actual){
        //static final Logger LOGGER = Logger.getAnonymousLogger();
        try{
            Path originPath = Paths.get(origin);
            Path actualPath = Paths.get(actual);
            Files.copy(originPath, actualPath, StandardCopyOption.REPLACE_EXISTING);
        }catch(FileNotFoundException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }catch(IOException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }      
            
    }
    
    public void CopiarCarpeta(String rutaCarpeta, String rutaCopiaCarpeta){
        File origin = new File(rutaCarpeta);
        String[] paths = origin.list();
        for (int i = 0; i < paths.length; i++) {
            CopiarArchivosCarpeta(rutaCarpeta + paths[i] ,rutaCopiaCarpeta + paths[i]);
        }
    }
    public boolean CopiarUnArchivo(String rutaArchivo, String rutaCopiaArchivo){
        try{
           
           FileInputStream fis = new FileInputStream(rutaArchivo); //inFile -> Archivo a copiar OJO El archivo ya debe venir con los \\ finales 
           File archivoOriginal = new File(rutaArchivo);           //es decir c:\\user\\hackerman\\texto.txt\\
           FileOutputStream fos = new FileOutputStream(rutaCopiaArchivo+ archivoOriginal.getName()); //outFile -> Copia del archivo
           FileChannel inChannel = fis.getChannel(); 
           FileChannel outChannel = fos.getChannel();
           inChannel.transferTo(0, inChannel.size(), outChannel);
           fis.close(); 
           fos.close();
           return true;
           
       }catch (IOException ioe) {
           return false;       }

    }
    
}
