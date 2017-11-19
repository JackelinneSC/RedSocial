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
public class Mensaje {
    private String emisor;
    private String receptor;
    private String fecha;
    private String mensaje;
    private int tipo;
    private int status;
    private String registro;
    //Constructor
    Mensaje()
    {
        this.emisor = "";
        this.receptor = "";
        this.fecha = "";
        this.mensaje = "";
        this.tipo = 0;
        this.status = 1;
    }
    //Metodos GET-SET
    public String getEmisor()
    {
        return emisor;
    }
    public String getReceptor()
    {
        return receptor;
    }
    public String getFecha()
    {
        return fecha;
    }
    public String getMensaje()
    {
        return mensaje;
    }
    public int getTipo()
    {
        return tipo;
    }
    public int getStatus()
    {
        return status;
    }
    public void setEmisor(String parametro)
    {
        this.emisor = parametro.replaceAll("#", "");
    }
    public void setReceptor(String parametro)
    {
        this.receptor = parametro.replaceAll("#", "");
    }
    public void setFecha(String parametro)
    {
        this.fecha = parametro.replaceAll("#", "");
    }
    public void setMensaje(String parametro)
    {
        this.mensaje = parametro.replaceAll("#", "");
    }
    public void setTipo(int parametro)
    {
        this.tipo = parametro;
    }
    public void setStatus(int parametro)
    {
        this.status = parametro;
    }
    public String getRegistro()
    {
        registro = "";
        rellenar(emisor,20,"#");
        rellenar(receptor,20,"#");
        registro += fecha + "|";
        rellenar(mensaje,140,"#");
        registro += tipo + "|";
        registro+=status + System.getProperty("line.separator");
        return registro;
    }
    public void setRegistro(String parametro)
    {
        this.registro = parametro;
    }
    //Procedimiento que establece un tamaño fijo para un atributo.
    public void rellenar(String var,int cantidad,String relleno) {
        registro+=var;
        for (int i = var.length(); i < cantidad; i++) {
           registro+=relleno; 
        }
        registro+="|"; 
    }
}
