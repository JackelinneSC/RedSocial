/*/*
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
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author User_Len
 */
public class Secuencial {
//Atributos
FileMethods Metodos = new FileMethods();
String RutaU = "c:\\MEIA\\Usuarios.txt";
String RutaBU = "c:\\MEIA\\BitácoraUsuarios.txt";
String DescriptorU = "c:\\MEIA\\DescriptorU.txt";
String DescriptorBU = "c:\\MEIA\\DescriptorBU.txt";
String RutaA = "c:\\MEIA\\Lista_Amigos.txt";
String RutaBA = "c:\\MEIA\\BitácoraAmigos.txt";
String DescriptorA = "c:\\MEIA\\DescriptorA.txt";
String DescriptorBA = "c:\\MEIA\\DescriptorBA.txt";
String RutaG = "c:\\MEIA\\Grupos.txt";
String RutaBG = "c:\\MEIA\\BitácoraGrupos.txt";
String DescriptorG = "c:\\MEIA\\DescriptorG.txt";
String DescriptorBG = "c:\\MEIA\\DescriptorBG.txt";
String RutaM = "c:\\MEIA\\Mensajes.txt";
String RutaBM =  "c:\\MEIA\\BitácoraMensajes.txt";
String DescriptorM = "c:\\MEIA\\DescriptorM.txt";
String DescriptorBM = "c:\\MEIA\\DescriptorBM.txt";
//Constructor
Secuencial(String bitácora)
{
    Metodos.createFile(bitácora);
}
Secuencial()
{}
//Insertar=================================================================================================================================
public void Insertar(String rutaBitacora, User usuario, Solicitud amistad, Grupo group, Mensaje publicacion)
{
    try
    {
        if(rutaBitacora == RutaBU)
        {
            int límite = maxRegistros(DescriptorBU);
            String[] Registros = conteoRegistros(RutaBU).split(Pattern.quote("|"));
            int cantidadRegistros = Integer.parseInt(Registros[0]) + Integer.parseInt(Registros[1]);
            if(cantidadRegistros < límite)
            {
                File archivo = new File(RutaBU);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(usuario.getRegistro());
                Escribir.close();
            }
            else
            {
                reorganizar(límite, RutaU, RutaBU);
                Metodos.createFile(RutaBU);
                File archivo = new File(RutaBU);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(usuario.getRegistro());
                Escribir.close();
                ActualizarDescriptor(DescriptorU, "");
            }
            ActualizarDescriptor(DescriptorBU, usuario.getUser());
        }
        else if(rutaBitacora == RutaBA)
        {
            int límite = maxRegistros(DescriptorBA);
            String[] Registros = conteoRegistros(RutaBA).split(Pattern.quote("|"));
            int cantidadRegistros = Integer.parseInt(Registros[0]) + Integer.parseInt(Registros[1]);
            if(cantidadRegistros < límite)
            {
                File archivo = new File(RutaBA);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(amistad.GetRegistro());
                Escribir.close();
            }
            else
            {
                reorganizar(límite, RutaA, RutaBA);
                Metodos.createFile(RutaBA);
                File archivo = new File(RutaBA);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(amistad.GetRegistro());
                Escribir.close();
                ActualizarDescriptor(DescriptorA, "");
            }
            ActualizarDescriptor(DescriptorBA, amistad.GetEmisor());
        }
        else if(rutaBitacora == RutaBG)
        {
            int límite = maxRegistros(DescriptorBG);
            String[] Registros = conteoRegistros(RutaBG).split(Pattern.quote("|"));
            int cantidadRegistros = Integer.parseInt(Registros[0]) + Integer.parseInt(Registros[1]);
            if(cantidadRegistros < límite)
            {
                File archivo = new File(RutaBG);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(group.GetRegistro());
                Escribir.close();
            }
            else
            {
                reorganizar(límite, RutaG, RutaBG);
                Metodos.createFile(RutaBG);
                File archivo = new File(RutaBG);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(group.GetRegistro());
                Escribir.close();
                ActualizarDescriptor(DescriptorG, "");
            }
            ActualizarDescriptor(DescriptorBG, group.GetUsuario()); 
        }
        else // Mensajes
        {
            int límite = maxRegistros(DescriptorBM);
            String[] Registros = conteoRegistros(RutaBM).split(Pattern.quote("|"));
            int cantidadRegistros = Integer.parseInt(Registros[0]) + Integer.parseInt(Registros[1]);
            if(cantidadRegistros < límite)
            {
                File archivo = new File(RutaBM);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(publicacion.getRegistro());
                Escribir.close();
            }
            else
            {
                reorganizar(límite, RutaM, RutaBM);
                Metodos.createFile(RutaBM);
                File archivo = new File(RutaBM);
                FileWriter Escribir = new FileWriter(archivo, true);
                Escribir.write(publicacion.getRegistro());
                Escribir.close();
                ActualizarDescriptor(DescriptorM, "");
            }
            ActualizarDescriptor(DescriptorBM, publicacion.getEmisor()); 
        }
    }catch(IOException e){}
}
  //Obtiene el límite de registros de la bitácora.
    private int maxRegistros(String direccion)
    {
        int Salida = 0;
        try
        {
            File Descriptor = new File(direccion);
            FileReader lector = new FileReader(Descriptor);
            BufferedReader LeerArchivo = new BufferedReader(lector);
            for (int i = 0; i < 10; i++) {
                LeerArchivo.readLine();
            }
            String[] Linea = LeerArchivo.readLine().split(" ");
            Salida = Integer.parseInt(Linea[1]);
        }
        catch(IOException e){}
        return Salida;
    }
          //Obtener la cantidad de registros activos e inactivos de un archivo.
    private String conteoRegistros(String Direccion)
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
            lector.close();
            leerArchivo.close();
        }
        catch(IOException ex)
        {
        }
        return contadorActivo + "|" + contadorInactivo;
    }
        //Actualizar el respectivo descriptor tras una inserción
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
                if(direccion.equals(DescriptorBU))
                {
                    escritor.write("Archivo: c:\\MEIA\\BitácoraUsuarios.txt\r\nDescripción: Bitácora_de_usuarios\r\nTipo: archivo_de_datos\r\nOrganización: Apilo\r\nAutor: "+cadena+ "\r\nCreado: "+ fecha+ "\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0\r\nRegistros_máximo: 3");
                }
                else if(direccion.equals(DescriptorU))
                {
                    escritor.write("Archivo: C://MEIA/Usuarios.txt\r\nDescripción: Usuarios_del_sistema.\r\nTipo: archivo_de_datos\r\nOrganización: Secuencial\r\nAutor: "+cadena+"\r\nCreado: "+fecha+"\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nLlave: Usuario\r\nOrden: Ascendente\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0");
                }
                else if(direccion.equals(DescriptorBA))
                {
                    escritor.write("Archivo: c:\\MEIA\\BitácoraAmigos.txt\r\nDescripción: Bitácora_de_solicitudes_de_amistad\r\nTipo: archivo_de_datos\r\nOrganización: Apilo\r\nAutor: "+cadena+ "\r\nCreado: "+ fecha+ "\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0\r\nRegistros_máximo: 3");
                }
                else if(direccion.equals(DescriptorA))
                {
                    escritor.write("Archivo: C://MEIA/Lista_Amigos.txt\r\nDescripción: Solicitudes_de_amistad.\r\nTipo: archivo_de_datos\r\nOrganización: Secuencial\r\nAutor: "+cadena+"\r\nCreado: "+fecha+"\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nLlave: Usuario_y_Usuarioamigo\r\nOrden: Ascendente\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0");
                }
                else if(direccion.equals(DescriptorBG))
                {
                    escritor.write("Archivo: c:\\MEIA\\BitácoraGrupos.txt\r\nDescripción: Bitácora_de_grupos\r\nTipo: archivo_de_datos\r\nOrganización: Apilo\r\nAutor: "+cadena+ "\r\nCreado: "+ fecha+ "\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0\r\nRegistros_máximo: 3");
                }
                else if(direccion.equals(DescriptorG))
                {
                    escritor.write("Archivo: C://MEIA/Grupos.txt\r\nDescripción: Grupos_del_sistema.\r\nTipo: archivo_de_datos\r\nOrganización: Secuencial\r\nAutor: "+cadena+"\r\nCreado: "+fecha+"\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nLlave: Usuario\r\nOrden: Ascendente\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0");
                }
                else if(direccion.equals(DescriptorBM))
                {
                    escritor.write("Archivo: c:\\MEIA\\BitácoraMensajes.txt\r\nDescripción: Bitácora_de_mensajes\r\nTipo: archivo_de_datos\r\nOrganización: Apilo\r\nAutor: "+cadena+ "\r\nCreado: "+ fecha+ "\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0\r\nRegistros_máximo: 3");
                }
                else // dirección iguala Descriptor Mensajes
                {
                    escritor.write("Archivo: C://MEIA/Mensajes.txt\r\nDescripción: Mensajes_del_sistema.\r\nTipo: archivo_de_datos\r\nOrganización: Secuencial\r\nAutor: "+cadena+"\r\nCreado: "+fecha+"\r\nModificado: "+fecha+"\r\nSeparador_campos: |\r\nLlave: emisor_receptor_fecha\r\nOrden: Ascendente\r\nRegistros_activos: 1\r\nRegistros_inactivos: 0");
                }
                escritor.close();
            }
            else
            {
                if(direccion.equals(DescriptorBU))
                {
                    String[] Registros = conteoRegistros(RutaBU).split("|");
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
                else if(direccion.equals(DescriptorBA))
                {
                    String[] Registros = conteoRegistros(RutaBA).split("|");
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
                else if(direccion.equals(DescriptorBG))
                {
                    String[] Registros = conteoRegistros(RutaBG).split("|");
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
                else if(direccion.equals(DescriptorBM))
                {
                    String[] Registros = conteoRegistros(RutaBM).split("|");
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
                else if(direccion.equals(DescriptorU))
                {
                    String[] Registros = conteoRegistros(RutaU).split("|");
                    Date Fecha = new Date();
                    RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
                    for (int j = 0; j < 6; j++) {
                        raf.readLine();
                    }
                    raf.writeBytes("Modificado: "+Fecha+"\r\n");
                    raf.readLine();
                    raf.readLine();
                    raf.readLine();
                    raf.writeBytes("Registros_activos: "+Registros[0]+"\r\n");
                    raf.writeBytes("Registros_inactivos: "+Registros[2]+"\r\n");
                    raf.close();
                }
                else if(direccion.equals(DescriptorA))
                {
                    String[] Registros = conteoRegistros(RutaA).split("|");
                    Date Fecha = new Date();
                    RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
                    for (int j = 0; j < 6; j++) {
                        raf.readLine();
                    }
                    raf.writeBytes("Modificado: "+Fecha+"\r\n");
                    raf.readLine();
                    raf.readLine();
                    raf.readLine();
                    raf.writeBytes("Registros_activos: "+Registros[0]+"\r\n");
                    raf.writeBytes("Registros_inactivos: "+Registros[2]+"\r\n");
                    raf.close();
                }
                else if(direccion.equals(DescriptorG))
                {
                    String[] Registros = conteoRegistros(RutaG).split("|");
                    Date Fecha = new Date();
                    RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
                    for (int j = 0; j < 6; j++) {
                        raf.readLine();
                    }
                    raf.writeBytes("Modificado: "+Fecha+"\r\n");
                    raf.readLine();
                    raf.readLine();
                    raf.readLine();
                    raf.writeBytes("Registros_activos: "+Registros[0]+"\r\n");
                    raf.writeBytes("Registros_inactivos: "+Registros[2]+"\r\n");
                    raf.close();
                }
                else if(direccion.equals(DescriptorM))
                {
                    String[] Registros = conteoRegistros(RutaM).split("|");
                    Date Fecha = new Date();
                    RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
                    for (int j = 0; j < 6; j++) {
                        raf.readLine();
                    }
                    raf.writeBytes("Modificado: "+Fecha+"\r\n");
                    raf.readLine();
                    raf.readLine();
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
//Busqueda ===========================================================================================================================
//Busqueda - publica
    //Función que devuelve la ruta y el número de linea donde se econtró la cadena
    public String busqueda(Boolean acumulativa, String cadena, String rutaBitacora, String rutaMaestro)
    {
        String Salida = "";
        if(acumulativa)
        {
            String bitacora = busquedaInterna(acumulativa, rutaBitacora,cadena);
            String Maestro = busquedaInterna(acumulativa, rutaMaestro,cadena);
            if((bitacora.equals("|0")) & (Maestro.equals("|0")))
            {
                Salida = "|0";
            }
            if(bitacora != "|0")
            {
                Salida += bitacora;
            }
            if(Maestro != "|0")
            {
                Salida += Maestro;
            }
        }
        else
        {
            Salida = busquedaInterna(acumulativa, rutaBitacora,cadena);
            if( Salida != "|0")
            {
                return Salida;
            }
            Salida = busquedaInterna(acumulativa, rutaMaestro,cadena);
            if(Salida != "|0")
            {
                return Salida;
            }
        }
        return Salida;
    }
//Busqueda - privada
//Busca una cadena en la ruta especificada
    private String busquedaInterna(Boolean acumulativa, String ruta, String cadena)
    {
        String Salida = "";
        try
        {
            //Si el archivo existe, lo leerá una linea a la vez
            File Archivo = new File(ruta);
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
                    //Si es una busqueda de usuario
                    if(ruta.equals(RutaU) || ruta.equals(RutaBU))
                    {
                        User newUser=new User();
                        values = Linea.split(Pattern.quote("|"));
                        newUser.setUser(values[0]);
                        newUser.setName(values[1]);
                        newUser.setLastName(values[2]);
                        newUser.setPassword(values[3]);
                        newUser.setRol(values[4]);
                        newUser.setDate(values[5]);
                        newUser.setEmail(values[6]);
                        newUser.setCelNumber(Integer.parseInt(values[7]));
                        newUser.setPhotoPath(values[8]);
                        newUser.setDescription(values[9]);
                        newUser.setStatus(Integer.parseInt(values[10]));
                        //Si es busqueda acumulativa o directa.
                        if(acumulativa)
                        {
                            //Si es acumulativa concatenar todos los resultados posibles.
                            if((cadena.equals(newUser.getUser()) || cadena.equals(newUser.getName()) || cadena.equals(newUser.getLastName())) & !(newUser.getStatus()==0))
                            {
                                Salida += newUser.getUser() + "," + newUser.getName() + "," + newUser.getLastName() + "|";
                            }
                        }
                        else
                        {
                            //Si es una busqueda directa, obtener la ubicación del resultado.
                            if(cadena.equals(newUser.getUser()) & !(newUser.getStatus()==0))
                            {
                                Salida = ruta+"|" + LeerArchivo.getFilePointer()+"|"+númLinea;   
                            }
                        }
                    }
                    //Si es una busqueda de solicitud de amistad.
                    else if(ruta.equals(RutaA) || ruta.equals(RutaBA))
                    {
                        Solicitud Amistad = new Solicitud();
                        values = Linea.split(Pattern.quote("|"));
                        Amistad.SetEmisor(values[0]);
                        Amistad.SetReceptor(values[1]);
                        Amistad.SetAceptado(Integer.parseInt(values[2]));
                        Amistad.SetUser(values[3]);
                        Amistad.SetFecha(values[4]);
                        Amistad.SetStatus(Integer.parseInt(values[5]));
                        if(acumulativa)
                        {
                            if((cadena.equals(Amistad.GetEmisor()) || cadena.equals(Amistad.GetReceptor())) & !(Amistad.GetStatus()==0))
                            {
                                Salida += Amistad.GetEmisor() + "," + Amistad.GetReceptor() + "," + Amistad.GetAceptado() + "," + Amistad.GetStatus() +"|";
                            }
                        }
                        else
                        {
                            String[] Llave = cadena.split(Pattern.quote("|"));
                            if(Llave[0].equals(Amistad.GetEmisor()) & Llave[1].equals(Amistad.GetReceptor()) & !(Amistad.GetStatus()==0))
                            {
                                Salida = ruta+"|" + LeerArchivo.getFilePointer()+"|"+númLinea;
                            }
                        }
                    }
                    else if(ruta.equals(RutaG) || ruta.equals(RutaBG))
                    {
                       Grupo Equipo = new Grupo();
                       values = Linea.split(Pattern.quote("|"));
                       Equipo.SetUsuario(values[0]);
                       Equipo.SetNombre(values[1]);
                       Equipo.SetDescripcion(values[2]);
                       Equipo.SetMiembros(Integer.parseInt(values[3]));
                       Equipo.SetFecha(values[4]);
                       Equipo.SetStatus(Integer.parseInt(values[5]));
                       if(acumulativa)
                       {
                           if(cadena.equals(Equipo.GetUsuario()) & !(Equipo.GetStatus() == 0))
                           {
                               Salida += Equipo.GetUsuario() + "," + Equipo.GetNombre() + "|";
                           }
                       }
                       else
                       {
                           String[] Llave = cadena.split(Pattern.quote("|"));
                           if(Llave[0].equals(Equipo.GetUsuario()) & Llave[1].equals(Equipo.GetNombre()) & !(Equipo.GetStatus() == 0))
                           {
                               Salida = ruta+"|" + LeerArchivo.getFilePointer()+"|"+númLinea;
                           }
                       }
                    }
                    //Si es una busqueda de mensajes
                    else if(ruta.equals(RutaM) || ruta.equals(RutaBM))
                    {
                        Mensaje Publicacion = new Mensaje();
                        values = Linea.split(Pattern.quote("|"));
                        Publicacion.setEmisor(values[0]);
                        Publicacion.setReceptor(values[1]);
                        Publicacion.setFecha(values[2]);
                        Publicacion.setMensaje(values[3]);
                        Publicacion.setTipo(Integer.parseInt(values[4]));
                        Publicacion.setStatus(Integer.parseInt(values[5]));
                        if(acumulativa)
                        {
                            if((cadena.equals(Publicacion.getEmisor())|| cadena.equals(Publicacion.getReceptor())) & !(Publicacion.getStatus() == 0))
                            {
                                Salida += Publicacion.getEmisor() + "," + Publicacion.getReceptor() + "," + Publicacion.getFecha() + "," + Publicacion.getTipo() + "," + Publicacion.getMensaje() +  "|";
                            }
                        }
                        else
                        {
                            String[] Llave = cadena.split(Pattern.quote("|"));
                            if(Llave[0].equals(Publicacion.getEmisor()) & Llave[1].equals(Publicacion.getReceptor()) & Llave[2].equals(Publicacion.getFecha()) & !(Publicacion.getStatus() == 0))
                            {
                                Salida = ruta+"|" + LeerArchivo.getFilePointer()+"|"+númLinea;
                            }
                        }
                    }
                }
                númLinea=(int)LeerArchivo.getFilePointer();
                Linea=LeerArchivo.readLine();
            }
            //Cerrar el lector utilizado
            LeerArchivo.close();
            }
        }
        catch(Exception ex){}
        //Si no se encontró nada, colocar el formato determinado.
        if(Salida.equals(""))
        {
            Salida = "|0";
        }
        return Salida;
    }
//ELIMINAR ==============================================================================================================================
    //Elimina un nombre de usuario especificado
    public void Eliminar(String cadena) throws IOException
    {
        FileMethods metodos= new FileMethods();
        
        String[]numeros=cadena.split(Pattern.quote("|")) ;
        File archivo = new File(numeros[0]);      
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(Integer.parseInt(numeros[1])-3);
            raf.writeBytes("0");
            raf.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//ACTUALIZAR======================================================================================================================
    public void Actualizar(String usuario,String registroactualizado) throws IOException
     {
        FileMethods metodos= new FileMethods();
        
        String[]numeros=usuario.split(Pattern.quote("|")) ;
        File archivo = new File(numeros[0]);      
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(Integer.parseInt(numeros[2]));
            raf.writeBytes(registroactualizado);
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//REORGANIZAR=====================================================================================================================    
    //Reorganiza los archivos para la inserción.
    public void reorganizar(int max, String rutaMaestro, String rutaBitacora )
    {
        File ArchivoMaestro = new File(rutaMaestro);
        File Bitácora = new File(rutaBitacora);
        //Si existe un maestro, traslada los datos y borralo
        if(ArchivoMaestro.exists())
        {
            try
            {
                RandomAccessFile raf = new RandomAccessFile(Bitácora,"rw");
                for (int i = 0; i < max; i++) {
                    raf.readLine();
                }
                FileReader lector = new FileReader(ArchivoMaestro);
                BufferedReader leerArchivo = new BufferedReader(lector);
                String Linea = leerArchivo.readLine();
                while(Linea != null)
                {
                    raf.writeBytes(Linea+"\r\n");
                    Linea = leerArchivo.readLine();
                }
                lector.close();
                leerArchivo.close();
                raf.close();
                Path ruta = Paths.get(rutaMaestro);
                Files.delete(ruta);
                reorganizar(max, rutaMaestro, rutaBitacora);
            }catch(IOException ex){}
        }
        else
        {
            try
        {
            //Obtener los nombres de la(s) llave(s).
            FileReader lector = new FileReader(Bitácora);
            BufferedReader leerArchivo = new BufferedReader(lector);
            String linea = leerArchivo.readLine();
            String Colección = "";
            String Colecciónnum = "";
            int númLineas = -1;
            while(linea != null)
            {
                númLineas++;
                String[] Datos = linea.split(Pattern.quote("|"));
                if("1".equals(Datos[Datos.length - 1]))
                {
                    if(rutaMaestro.equals(RutaU)) //una llave
                    {
                        Colección += Datos[0] + "|";
                    }
                    else if(rutaMaestro.equals(RutaM)) //tres llaves
                    {
                        Colección += Datos[0]+Datos[1] + Datos[2] + "|";
                    }
                    else // dos llaves
                    {
                        Colección += Datos[0]+Datos[1] + "|";
                    }
                    Colecciónnum += númLineas + "|";
                }
                linea = leerArchivo.readLine();
            }
            lector.close();
            leerArchivo.close();
            String[] Registros = Colección.split(Pattern.quote("|"));
            String[] Posición = Colecciónnum.split(Pattern.quote("|"));
            //Obtener el orden de los registros
            for (int j = 1; j <= Registros.length; j++) {
                for (int k = 0; k < Registros.length -j; k++) {
                    if(Registros[k].compareTo(Registros[k+1]) > 0)
                    {
                        String aux = Registros[k];
                        Registros[k] = Registros[k+1];
                        Registros[k+1] = aux;
                        aux = Posición[k];
                        Posición[k] = Posición[k+1];
                        Posición[k+1] = aux;
                    }
                }
            }
            //Leer, escribir, leer, escribir, leer...
            Metodos.createFile(rutaMaestro);
            FileWriter escribir = new FileWriter(ArchivoMaestro);
            for (int i = 0; i < Posición.length; i++) {
                lector = new FileReader(Bitácora);
                leerArchivo = new BufferedReader(lector);
                for (int j = 0; j < Integer.parseInt(Posición[i]); j++) {
                 leerArchivo.readLine();
                }
                escribir.write(leerArchivo.readLine()+"\r\n");
                if(rutaMaestro.equals(RutaU))
                {
                    ActualizarDescriptor(DescriptorU,Registros[i]);
                }
                else if(rutaMaestro.equals(RutaA))
                {
                    ActualizarDescriptor(DescriptorA,Registros[i]);
                }
                else if(rutaMaestro.equals(RutaG))
                {
                    ActualizarDescriptor(DescriptorG, Registros[i]);
                }
                else
                {
                    ActualizarDescriptor(DescriptorM, Registros[i]);
                }
                lector.close();
                leerArchivo.close();
            }
            escribir.close();
            Path rutab = Paths.get(rutaBitacora);
            Files.delete(rutab);
            Path rutac;
            if(rutaBitacora.equals(RutaBU))
            {
                rutac = Paths.get(DescriptorBU);            
            }
            else if(rutaBitacora.equals(RutaBA))
            {
                rutac = Paths.get(DescriptorBA);
            }
            else if(rutaBitacora.equals(RutaBG))
            {
                rutac = Paths.get(DescriptorBG);
            }
            else
            {
                rutac = Paths.get(DescriptorBM);
            }
            Files.delete(rutac);
        }
        catch(IOException e){}
        }
    }
     public User readUser(String busqueda){
        FileMethods metodos= new FileMethods();
        User newUser=new User();
        String[]numeros=busqueda.split(Pattern.quote("|")) ;
        File archivo = new File(numeros[0]);      
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(Integer.parseInt(numeros[2]));
            String Linea=raf.readLine();
            raf.close();
            
            String[] values = Linea.split(Pattern.quote("|"));
            newUser.setUser(values[0]);
            newUser.setName(values[1]);
            newUser.setLastName(values[2]);
            newUser.setPassword(values[3]);
            newUser.setRol(values[4]);
            newUser.setDate(values[5]);
            newUser.setEmail(values[6]);
            newUser.setCelNumber(Integer.parseInt(values[7]));
            newUser.setPhotoPath(values[8]);
            newUser.setDescription(values[9]);
            newUser.setStatus(Integer.parseInt(values[10]));
            return newUser;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            return newUser;
        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            return newUser;
        }
        
    }
     public Grupo LeerGrupo(String busqueda)
    {
        Grupo objGrupo = new Grupo();
        String[]numeros = busqueda.split(Pattern.quote("|")) ;
        File archivo = new File(numeros[0]);      
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(archivo,"rw");
            raf.seek(Integer.parseInt(numeros[2]));
            String Linea=raf.readLine();
            raf.close();
            String[] values = Linea.split(Pattern.quote("|"));
            objGrupo.SetUsuario(values[0]);
            objGrupo.SetNombre(values[1]);
            objGrupo.SetDescripcion(values[2]);
            objGrupo.SetMiembros(Integer.parseInt(values[3]));
            objGrupo.SetFecha(values[4]);
            objGrupo.SetStatus(Integer.parseInt(values[5]));
            return objGrupo;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            return objGrupo;
        } catch (IOException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            return objGrupo;
        }
    }
}
