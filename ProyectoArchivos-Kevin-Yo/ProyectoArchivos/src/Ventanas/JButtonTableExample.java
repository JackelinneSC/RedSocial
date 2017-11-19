/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author keviu
 */
public class JButtonTableExample extends JFrame {

  public JTable table;
  public User aux;
  public String Datos;
  public int Num;
  String RutaU = "c:\\MEIA\\Usuarios.txt";
    String RutaBU = "c:\\MEIA\\BitácoraUsuarios.txt";
    String DescriptorU = "c:\\MEIA\\DescriptorU.txt";
    String DescriptorBU = "c:\\MEIA\\DescriptorBU.txt";
    String RutaA = "c:\\MEIA\\Lista_Amigos.txt";
    String RutaBA = "c:\\MEIA\\BitácoraAmigos.txt";
    String DescriptorA = "c:\\MEIA\\DescriptorA.txt";
    String DescriptorBA = "c:\\MEIA\\DescriptorBA.txt";
  public JButtonTableExample(String datos,int num,User usuario) {
    super("JButtonTable Example");
    aux=usuario;
    Datos=datos;
    Num=num;
    DefaultTableModel dm = new DefaultTableModel();
    Object[][] filas ;
    filas=Registro(datos,num);
    Object[] Encafilas ;
    Encafilas=EncabezadoRegistro(num);
    
    dm.setDataVector(filas,Encafilas);

    table = new JTable(dm);
    table.getColumn("Button").setCellRenderer(new ButtonRenderer());
    table.getColumn("Button").setCellEditor(
        new ButtonEditor(new JCheckBox()));
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add(scroll);
    setSize(400, 100);
    setVisible(false);
    
  }
  public Object[][] Registro(String datos,int tabla)
  { 
    String[]nume=datos.split(Pattern.quote("|"));
    Secuencial metodos= new Secuencial(RutaBA);
    Object[][] filas = null;
    if(tabla==1){
        filas =new Object[nume.length][4];
     for (int j = 0; j < nume.length; j++) {
         String[] para=nume[j].split(Pattern.quote(","));
//         String busqueda=metodos.busqueda(nume[j]);
//          User auxiliar=new User();
//          auxiliar =metodos.readUser(busqueda);
         
         if(aux.getUser().equals(para[0])==false)
         {
         filas[j][0]="Enviar solicitud";
         filas[j][1]=para[0];
         filas[j][2]=para[1];
         filas[j][3]=para[2];
         }
         
         
     
      }
     
    }
    else if(tabla==2){
          filas =new Object[nume.length][2];
     for (int j = 0; j < nume.length; j++) {
         String[] para=nume[j].split(Pattern.quote(","));
         if((para[2].equals("0")==true)&(para[3].equals("1")==true)&(aux.getUser().equals(para[1])==true))
         {
         filas[j][0]="Procesar";
         filas[j][1]=para[0];
         
         }
      }
        
    }
    else if(tabla==3){
          filas =new Object[nume.length][4];
     for (int j = 0; j < nume.length; j++) {
         String[] para=nume[j].split(Pattern.quote(","));
         
         if((para[2].equals("1")==true)&(para[3].equals("1")==true))
         {
             if((aux.getUser().equals(para[0])==true))
             {
               String busqueda=metodos.busqueda(false,para[1],RutaBU,RutaU);
               User auxiliar=new User();
               auxiliar =metodos.readUser(busqueda);
                filas[j][0]="Eliminar";
                filas[j][1]=auxiliar.getUser();
                filas[j][2]=auxiliar.getName(); 
                filas[j][3]=auxiliar.getLastName(); 
             }
             else if(aux.getUser().equals(para[1])==true)
             {
                String busqueda=metodos.busqueda(false,para[0],RutaBU,RutaU);
                User auxiliar=new User();
                auxiliar =metodos.readUser(busqueda);
                filas[j][0]="Eliminar";
                filas[j][1]=auxiliar.getUser();
                filas[j][2]=auxiliar.getName(); 
                filas[j][3]=auxiliar.getLastName(); 
             }
          
         
         }
      }
        
    }
   
    return filas;
     
  }
  public Object[] EncabezadoRegistro(int tabla)
  { 
    Object[] filas =  null;
    if(tabla==1){
        filas =  new Object[] { "Button", "String","",""};
    }
    else if(tabla==2){
        filas =  new Object[] { "Button","Usuario"};
    }
    else if(tabla==3){
        filas =  new Object[] { "Button", "User","Name","Last Name"};
    }
   
    return filas;
     
  }
  public JTable gettable() {
    return table;
  }
    class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;
  private int row;
  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
       
        int filaseleccionada = table.getSelectedRow();
        String nombrebtn = (String)table.getValueAt(filaseleccionada, 0);
        String Receptor = (String)table.getValueAt(filaseleccionada, 1);
        if(nombrebtn.equals("Enviar solicitud"))
        {
        Secuencial listaA=new Secuencial(RutaBA);
        Solicitud s=new Solicitud();
        s.SetEmisor(aux.getUser());
        s.SetReceptor(Receptor);
        s.SetFecha((new Date()).toString());
        s.SetUser(aux.getUser());  
        listaA.ActualizarDescriptor(DescriptorBA, aux.getUser());
        listaA.Insertar(RutaBA, new User(), s,new Grupo(), new Mensaje());
        button.setText("Amigo");
        label="Solicitud enviada";
        }
        else if(nombrebtn.equals("Eliminar")){
            int resp = JOptionPane.showConfirmDialog(null, "Desea Eliminar Amistad", "Eliminar", JOptionPane.YES_NO_OPTION);
        Secuencial listaA=new Secuencial(RutaBA);
        Receptor = (String)table.getValueAt(filaseleccionada, 1);
        Solicitud s=new Solicitud();
       s.SetEmisor(Receptor);
        s.SetReceptor(aux.getUser());
        s.SetFecha((new Date()).toString());
        s.SetUser(aux.getUser()); 
        if (resp == 0){ 
            JOptionPane.showMessageDialog(button, "Elimidado");
            label="Elimidado";
          
            try {
                String busqueda=listaA.busqueda(false,Receptor+"|"+aux.getUser(),RutaBA,RutaA);
                s.SetAceptado(0);
                s.SetStatus(0);
                listaA.Actualizar(busqueda,s.GetRegistro());
            } catch (IOException ex) {
                Logger.getLogger(JButtonTableExample.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
       
        table.removeRowSelectionInterval(filaseleccionada, filaseleccionada);
        }
        else if(nombrebtn.equals("Procesar")){
            
        int resp = JOptionPane.showConfirmDialog(null, "Desea Aceptar la Solicitud de Amistad", "Solicitud De Amistad", JOptionPane.YES_NO_OPTION);
        Secuencial listaA=new Secuencial(RutaBA);
        Solicitud s=new Solicitud();
        s.SetEmisor(Receptor);
        s.SetReceptor(aux.getUser());
        s.SetFecha((new Date()).toString());
        s.SetUser(aux.getUser()); 
        if (resp == 0){ 
            JOptionPane.showMessageDialog(button, "aceptado");
            label="aceptado";
          
            try {
                String busqueda=listaA.busqueda(false,Receptor+"|"+aux.getUser(),RutaBA,RutaA);
                s.SetAceptado(1);
                listaA.Actualizar(busqueda,s.GetRegistro());
            } catch (IOException ex) {
                Logger.getLogger(JButtonTableExample.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
             JOptionPane.showMessageDialog(button, "Rechazado");
             label="Rechazado";
             try {
                String busqueda=listaA.busqueda(false,Receptor+"|"+aux.getUser(),RutaBA,RutaA);
                s.SetAceptado(0);
                s.SetStatus(0);
                listaA.Actualizar(busqueda,s.GetRegistro());
            } catch (IOException ex) {
                Logger.getLogger(JButtonTableExample.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        }
    }
    
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
class DataWithIcon {
  public DataWithIcon(Object data, Icon icon) {
    this.data = data;
    this.icon = icon;
  }

  public Icon getIcon() {
    return icon;
  }

  public Object getData() {
    return data;
  }

  public String toString() {
    return data.toString();
  }

  protected Icon icon;

  protected Object data;
}

class FractionCellRenderer extends DefaultTableCellRenderer {
  public FractionCellRenderer(int integer, int fraction, int align) {
    this.integer = integer; // maximum integer digits
    this.fraction = fraction; // exact number of fraction digits
    this.align = align; // alignment (LEFT, CENTER, RIGHT)
  }

  @Override
  protected void setValue(Object value) {
    if (value != null && value instanceof Number) {
    } else {
      super.setValue(value);
    }
    setHorizontalAlignment(align);
  }

  protected int integer;

  protected int fraction;

  protected int align;

//  protected static NumberFormat formatter = NumberFormat.getInstance();
}
class TextWithIconCellRenderer extends DefaultTableCellRenderer {
  protected void setValue(Object value) {
    if (value instanceof DataWithIcon) {
      if (value != null) {
        DataWithIcon d = (DataWithIcon)value;
        Object dataValue = d.getData();

        setText(dataValue == null ? "" : dataValue.toString());
        setIcon(d.getIcon());
//        setHorizontalTextPosition(SwingConstants.RIGHT);
//        setVerticalTextPosition(SwingConstants.CENTER);
//        setHorizontalAlignment(SwingConstants.LEFT);
//        setVerticalAlignment(SwingConstants.CENTER);
      } else {
        setText("");
        setIcon(null);
      }
    } else {
      super.setValue(value);
    }
  }
}

        
        
}
