/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;



/**
 *
 * @author User_Len
 */
public class Solicitud {
    //Atributos
    private String emisor;
    private String receptor;
    private int aceptado;
    private String Fecha;
    private String user;
    private int status;
    private String registro;
    //Constructor
    Solicitud()
    {
        this.emisor = "";
        this.receptor = "";
        this.aceptado = 0;
        this.Fecha = "";
        this.user = "";
        this.status = 1;
    }
    //Metodos GET-SET
    public String GetEmisor()
    {
        return this.emisor;
    }
    public String GetReceptor()
    {
        return this.receptor;
    }
    public int GetAceptado()
    {
        return aceptado;
    }
    public String GetFecha()
    {
        return this.Fecha;
    }
    public String GetUser()
    {
        return this.user;
    }
    public int GetStatus()
    {
        return this.status;
    }
    public void SetEmisor(String usuario)
    {
        this.emisor = usuario.replaceAll("#", "");
    }
    public void SetReceptor(String usuario)
    {
        this.receptor = usuario.replaceAll("#", "");
    }
    public void SetAceptado(int numero)
    {
        this.aceptado = numero;
    }
    public void SetFecha(String momento)
    {
        this.Fecha = momento;
    }
    public void SetUser(String usuario)
    {
        this.user = usuario.replaceAll("#", "");
    }
    public void SetStatus(int estado)
    {
        this.status = estado;
    }
    //Procedimiento que establece un tamaño fijo para un atributo.
    public void rellenar(String var,int cantidad,String relleno) {
        registro+=var;
        for (int i = var.length(); i < cantidad; i++) {
           registro+=relleno; 
        }
        registro+="|"; 
    }
    public String GetRegistro()
    {
        registro = "";
        rellenar(emisor,20,"#");
        rellenar(receptor,20,"#");
        registro += aceptado + "|";
        registro += Fecha + "|";
        rellenar(emisor,20,"#");
        registro+=status + System.getProperty("line.separator");
        return registro;
    }
    public void setRegistro(String parametro) {
        this.registro = parametro;
    }
}