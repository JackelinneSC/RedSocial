/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 *
 * @author jsala
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Derek & jsala
 */
public class Listener extends Thread {
    private Connection conn;
    private org.postgresql.PGConnection pgconn;
    private String id;
    private String grupoReceptor;
    private String grupoEmisor;
    private String user;
    private String mensaje;
    private String emisor;
    private String fecha;
    private Notificacion not;  
    private Secuencial objSecuencial = new Secuencial();
    private Mensaje objMensaje = new Mensaje();

    Listener(Connection conn) throws SQLException {
            this.conn = conn;
            this.pgconn = (org.postgresql.PGConnection)conn;
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN q_event");
            stmt.close();
    }

    public void run() {
        while (true) {
            try {
                //Escucha en la base de ddatos
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1"); 
                rs.close();
                stmt.close();

                org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
                if (notifications != null) {
                    for (int i=0; i<notifications.length; i++) {
                        //Descomponer que accion es en esta parte
                        String parameter = notifications[i].getParameter().replace("\\","");
                        String action = parameter.split("\\{")[1].split(",")[1].split(":")[1].substring(2,8);
                                          
                        if(action.equals("INSERT")){
                            //comprobar si es para mi
                            
                            id = parameter.split("\\{")[2].replace("}","").split(",")[0].split(":")[1];
                            grupoReceptor = parameter.split("\\{")[2].replace("}","").split(",")[2].split(":")[1].replace("\"","");
                            grupoEmisor = parameter.split("\\{")[2].replace("}","").split(",")[1].split(":")[1].replace("\"",""); //emisor
                            user = parameter.split("\\{")[2].replace("}","").split(",")[4].split(":")[1].replace("\"",""); //receptor
                            emisor = parameter.split("\\{")[2].replace("}","").split(",")[3].split(":")[1].replace("\"","");
                            mensaje = parameter.split("\\{")[2].replace("}","").split(",")[6].split(":")[1].replace("\"",""); //mensaje                          
                            fecha = parameter.split("\\{")[2].replace("}","").split(",")[5].split(":")[1].replace("\"",""); //fecha
                         
                            boolean existe = false;
                            objMensaje.setEmisor(emisor); 
                            objMensaje.setFecha(fecha);
                            objMensaje.setReceptor(user);
                            objMensaje.setMensaje(mensaje);
                            objMensaje.setStatus(1); //mensajes privados
                         
                            if(grupoReceptor.equals("3")){//nuestro grupo es el 3
                                //si es para mi enviar el update con la respuesta
                                Singleton.getInstancia().setMensaje("El grupo " + grupoEmisor + " le ha enviado un mensaje a " + user);
                                not = new Notificacion();
                                not.setVisible(true);
                             
                                //si es para mi enviar el update con la respuesta de que el usuario existe
                                if(objSecuencial.busqueda(false, user, objSecuencial.RutaBU, objSecuencial.RutaU) == "|0")
                                    existe = false; //si no existe
                                else
                                    existe = true; //si existe y la cadena es distinta a |0
                                if(existe){
                                    Singleton.getInstancia().Update(id, existe);
                                    //guardar el mensaje en nuestro programa
                                    objSecuencial.Insertar(objSecuencial.RutaBM,new User() , new Solicitud(), new Grupo(), objMensaje);
                                }else{
                                    Singleton.getInstancia().Update(id, existe);
                                }                                        
                            }
                        }else{
                            //UPDATE                            
                            //comprobar si yo fui el que envie la solicitud
                            //Descomponer id, grupo emisor y grupo receptor en esta parte
                            id = parameter.split("\\{")[2].replace("}","").split(",")[0].split(":")[1];
                            grupoReceptor = parameter.split("\\{")[2].replace("}","").split(",")[2].split(":")[1].replace("\"",""); //al que envio
                            grupoEmisor = parameter.split("\\{")[2].replace("}","").split(",")[1].split(":")[1].replace("\"",""); //yo
                            user = parameter.split("\\{")[2].replace("}","").split(",")[4].split(":")[1].replace("\"",""); //usaurio del otro grupo
                            emisor = parameter.split("\\{")[2].replace("}","").split(",")[3].split(":")[1].replace("\"","");//
                            mensaje = parameter.split("\\{")[2].replace("}","").split(",")[6].split(":")[1].replace("\"",""); //mensaje
                            fecha = parameter.split("\\{")[2].replace("}","").split(",")[5].split(":")[1].replace("\"",""); //fecha
                            objMensaje.setEmisor(emisor); 
                            objMensaje.setFecha(fecha);
                            objMensaje.setReceptor(user);
                            objMensaje.setMensaje(mensaje);
                            objMensaje.setStatus(1); //mensajes privados
                            if(grupoEmisor.equals("3")){
                                 String respuesta = parameter.split("\\{")[2].replace("}","").split(",")[7].split(":")[1];
                                 //Comprobar cual fue la respuesta
                                 if(respuesta.equals("false")){
                                    Singleton.getInstancia().setMensaje("El grupo " + grupoReceptor + " dice que no encontro el usuario." );
                                    not = new Notificacion();
                                    not.setVisible(true);
                                 }else{
                                    Singleton.getInstancia().setMensaje("El grupo " + grupoReceptor + " dice que ha recibido el mensaje." );
                                    objSecuencial.Insertar(objSecuencial.RutaBM,new User() , new Solicitud(), new Grupo(), objMensaje);
                                    not = new Notificacion();
                                    not.setVisible(true);
                                 }
                                 //Eliminar la solicitud
                                 Singleton.getInstancia().Delete(id);
                            }
                        }                                             
                    }
                }
                //Espera para la siguiente notifiacion
                Thread.sleep(500);
            } catch (SQLException | InterruptedException sqle) {
                    sqle.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
