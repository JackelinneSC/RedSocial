/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import static java.awt.image.ImageObserver.WIDTH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author jsala
 */
public class User {
    String RutaA = "c:\\MEIA\\Lista_Amigos.txt";
    String RutaBA = "c:\\MEIA\\BitácoraAmigos.txt";
    Secuencial objSecuencial = new Secuencial();
    private String user;
    private String name;
    private String lastName;
    private String password;
    private String rol;
    private String date; //fecha actual
    private String email;
    private int celNumber;
    private String description;
    private String photoPath;
    private int status;
    private String registro;
    
        //Constructor
    User(){
        this.user = "";
        this.name = "";
        this.lastName = "";
        this.rol = "0";
        this.date = "";
        this.email ="";
        this.celNumber = 00000000;
        this.description = "";
        this.photoPath = "";
        this.status = 1; //está vigente  
    }

    //Métodos GET-SET
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user.replaceAll("#", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replaceAll("#", "");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.replaceAll("#", "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email.replaceAll(" ", "");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCelNumber() {
        return celNumber;
    }

    public void setCelNumber(int celNumber) {
        this.celNumber = celNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("#", "");;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath.replaceAll(" ", "");;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
     public String getRegistro() {
         registro="";
         rellenar(user,16,"#");
         rellenar(name,50,"#");
         rellenar(lastName,50,"#");
         registro+= password +"|";
         registro+=rol +"|";
         registro+=date +"|";
         rellenar(email,32," ");
         registro+=celNumber +"|";
         rellenar(photoPath,50," ");
         rellenar(description,50,"#");
         registro+=status + System.getProperty("line.separator");
         return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
    //Procedimiento que establece un tamaño fijo para un atributo.
    public void rellenar(String var,int cantidad,String relleno) {
        registro+=var;
        for (int i = var.length(); i < cantidad; i++) {
           registro+=relleno; 
        }
        registro+="|"; 
    }
        //Verificar el formato del correo - Usuario/Registro.
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        //Booleano que verifica el formato del correo -Usuario/Registro
    public boolean validateEmail(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
        //Método que estable la seguridad de la contraseña - Usuario
    public Boolean contraseñaSegura(char[] caracteres)
    {
        //Atributos
        int puntaje;
        int Mayúsculas = 0;
        int Letras = 0;
        int Números = 0;
        int Símbolos = 0;
        //Analizar la cadena ingresada
        for(int i = 0; i < caracteres.length; i++)
        {
            //Si es letra o número
            if(Character.isLetterOrDigit(caracteres[i]))
            {
              //Si es una letra
              if(Character.isLetter(caracteres[i]))
               {
                  //Si es mayúscula
                  if(Character.isUpperCase(caracteres[i]))
                   {
                     Mayúsculas++;
                   }
               Letras++;
               }
              //Si es un número
                else if(Character.isDigit(caracteres[i]))
                {
                Números++;
                }   
            }
            //Si es un símbolo
            else
            {
                Símbolos++;
            }
        }
        //Determinar el puntaje
        puntaje = 3 * caracteres.length;
        puntaje += (2 * Mayúsculas);
        puntaje += 1 + Letras;
        puntaje += 2 + Números;
        puntaje += (Símbolos * (caracteres.length + 4));
        if(Números == 0 && Símbolos == 0)
        {
            puntaje = puntaje - 6;
        }
        else if(Letras == 0 && Símbolos == 0)
        {
            puntaje = puntaje - 3;
        }
        if(puntaje <= 35)  
        {
            JOptionPane.showMessageDialog(null, "La contraseña es insegura o poco segura", "Error", WIDTH);
            return false;
        }
        else
        {
            return true;
        }
    }
    public String ObtenerAmigos()
    {
        String Amigos = "";
        String[] Solicitudes = objSecuencial.busqueda(true, user, RutaBA, RutaA).split(Pattern.quote("|"));
        if(!Solicitudes[0].equals("")) //Si la busqueda de amigos tiene un resultado válido.
            {
                for (int i = 0; i < Solicitudes.length; i++) {
                    String[] Data = Solicitudes[i].split(Pattern.quote(","));
                    if((Data[2].equals("1")) & (Data[3].equals("1"))) //Si la solicitud fue aceptada y está vigente
                    {
                        if(user.equals(Data[0])) //Si el usuario fue el emisor, agregar al receptor
                        {
                            Amigos += Data[1] + ",";
                        }
                        else //Si el usuario no fue el emisor, agregar al emisor.
                        {
                            Amigos += Data[0] + ",";
                        }
                    }
                }
            }
        return Amigos;
    }
}
