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
public class Grupo {
    private String Usuario;
    private String Nombre;
    private String Descripcion;
    private int Miembros;
    private String Fecha;
    private int Status;
    private String registro;
    //Constructor
    Grupo()
    {
        this.Usuario = "";
        this.Nombre = "";
        this.Descripcion = "";
        this.Miembros = 5;
        this.Fecha = "";
        this.Status = 1;
    }
    //Metodos GET-SET
    public String GetUsuario()
    {
        return this.Usuario;
    }
    public String GetNombre()
    {
        return this.Nombre;
    }
    public String GetDescripcion()
    {
        return this.Descripcion;
    }
    public int GetMiembros()
    {
        return this.Miembros;
    }
    public String GetFecha()
    {
        return this.Fecha;
    }
    public int GetStatus()
    {
        return this.Status;
    }
    public void SetUsuario(String usuario)
    {
        this.Usuario = usuario.replaceAll("#", "");
    }
    public void SetNombre(String parametro)
    {
        this.Nombre = parametro.replaceAll("#", "");
    }
    public void SetDescripcion(String parametro)
    {
        this.Descripcion = parametro.replaceAll("#", "");
    }
    public void SetMiembros(int parametro)
    {
        this.Miembros = parametro;
    }
    public void SetFecha(String parametro)
    {
        this.Fecha = parametro.replaceAll("#", "");
    }
    public void SetStatus(int parametro)
    {
        this.Miembros = parametro;
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
        rellenar(Usuario,30,"#");
        rellenar(Nombre,30,"#");
        rellenar(Descripcion,60,"#");
        registro += Miembros + "|";
        registro += Fecha + "|";
        registro+=Status + System.getProperty("line.separator");
        return registro;
    }
    public void setRegistro(String parametro) {
        this.registro = parametro;
    }
}

