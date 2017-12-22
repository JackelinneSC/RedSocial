/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Color;
import java.io.File;

/**
 *
 * @author jsala
 */
public class Registro extends javax.swing.JFrame {

    //ATRIBUTOS
    Secuencial objSecuencial;
    User objUsuario = new User();
    FileMethods archivos = new FileMethods();
    String fotografía;
    String descripción = "Añade una breve descripción";
    String contraseñaEncriptada;
    Boolean usuario;
    Boolean contraseña;
    Boolean nuevoUsuario;
    Boolean nuevaContraseña;
    Boolean nombre;
    Boolean apellido;
    Boolean fecha;
    Boolean correo;
    Boolean teléfono;
    Boolean Foto = false;
    String RutaU = "c:\\MEIA\\Usuarios.txt";
    String RutaBU = "c:\\MEIA\\BitácoraUsuarios.txt";
    String DescriptorU = "c:\\MEIA\\DescriptorU.txt";
    String DescriptorBU = "c:\\MEIA\\DescriptorBU.txt";
    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        archivos.createFolder("c:\\MEIA\\");
        archivos.createFolder("c:\\MEIA\\Fotografías");
        archivos.createFolder("c:\\MEIA\\pictures");
        objSecuencial = new Secuencial("c:\\MEIA\\BitácoraUsuarios.txt");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNUsuario = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        btnImagen = new javax.swing.JButton();
        tfNContraseña = new javax.swing.JPasswordField();
        lbNContraseña = new javax.swing.JLabel();
        lbCorreo = new javax.swing.JLabel();
        lbTeléfono = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        tfNUsuario = new javax.swing.JTextField();
        tfNNombre = new javax.swing.JTextField();
        tfNApellido = new javax.swing.JTextField();
        cbDía = new javax.swing.JComboBox<>();
        cbMes = new javax.swing.JComboBox<>();
        cbAño = new javax.swing.JComboBox<>();
        tfNCorreo = new javax.swing.JTextField();
        tfNTeléfono = new javax.swing.JTextField();
        lbEslogan = new javax.swing.JLabel();
        lbNombre1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbNUsuario.setText("Usuario");

        lbNombre.setText("Nombre");

        btnImagen.setText("Foto Perfil");
        btnImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagenActionPerformed(evt);
            }
        });

        lbNContraseña.setText("Contraseña");

        lbCorreo.setText("Correo electónico");

        lbTeléfono.setText("Teléfono");

        btnRegistrar.setText("Registarse");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        cbDía.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic" }));

        cbAño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950" }));

        lbEslogan.setText("¿Eres nuevo? ¡Registrate!");

        lbNombre1.setText("Apellido");

        btnRegresar.setText("Cerrar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEslogan)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNombre)
                                    .addComponent(lbNUsuario)
                                    .addComponent(lbNombre1)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfNUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnImagen)
                                        .addComponent(lbTeléfono))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tfNTeléfono, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnRegistrar)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnRegresar))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbCorreo)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfNCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbNContraseña)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfNContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbDía, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbAño, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(tfNNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfNApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbEslogan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre1))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNContraseña))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDía, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCorreo)
                    .addComponent(tfNCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTeléfono)
                    .addComponent(tfNTeléfono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImagen)
                    .addComponent(btnRegistrar)
                    .addComponent(btnRegresar))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagenActionPerformed
        //Seleccionar una foto de perfil.
       
        fotografía = archivos.fotoPerfil(this);
      
        if (archivos.CopiarUnArchivo(fotografía + "\\", "C:\\MEIA\\Fotografías\\")) {
            File archivoOriginal = new File(fotografía); 
            fotografía = "C:\\MEIA\\Fotografías\\" + archivoOriginal.getName();
            Foto = true;
        }
                 
            
    }//GEN-LAST:event_btnImagenActionPerformed

    //BOTÓN REGISTRO
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        //Verificar nuevo Usuario
        //archivos.busqueda(tfNUsurario.getText()!= "|0")
        if(objSecuencial.busqueda(false, tfNUsuario.getText(), RutaBU, RutaU) != "|0" || tfNUsuario.getText() == "")
        {
            nuevoUsuario = false;
            tfNUsuario.setBackground(Color.pink);
        }
        else
        {
            nuevoUsuario = true;
            tfNUsuario.setBackground(Color.green);
        }
        //Verificar Nombre
        if(tfNNombre.getText().equals(""))
        {
            nombre = false;
            tfNNombre.setBackground(Color.pink);
        }
        else
        {
            nombre = true;
            tfNNombre.setBackground(Color.green);
        }
        //Verificar Apellido
        if(tfNApellido.getText().equals(""))
        {
            apellido = false;
            tfNApellido.setBackground(Color.pink);
        }
        else
        {
            apellido = true;
            tfNApellido.setBackground(Color.green);
        }
        //Verificar Contraseña
        if(tfNContraseña.getPassword().length ==0 || !objUsuario.contraseñaSegura(tfNContraseña.getPassword()))
        {
            nuevaContraseña = false;
            tfNContraseña.setBackground(Color.pink);
        }
        else
        {
            nuevaContraseña = true;
            tfNContraseña.setBackground(Color.green);
            contraseñaEncriptada = archivos.EncriptadoMD5(tfNContraseña.getText());
        }
        //Verificar fecha de nacimiento
        if(cbDía.getSelectedItem() == " " || cbMes.getSelectedItem() == " " || cbAño.getSelectedItem() == " ")
        {
            fecha = false;
            cbDía.setBackground(Color.red);
            cbMes.setBackground(Color.red);
            cbAño.setBackground(Color.red);
        }
        else
        {
            fecha = true;
            cbDía.setBackground(Color.green);
            cbMes.setBackground(Color.green);
            cbAño.setBackground(Color.green);
        }
        //Verificar correo electónico
        if(tfNCorreo.getText().equals("") || !objUsuario.validateEmail(tfNCorreo.getText()))
        {
            correo = false;
            tfNCorreo.setBackground(Color.pink);
        }
        else
        {
            correo = true;
            tfNCorreo.setBackground(Color.green);
        }
        //Verificar télefono
        try
        {
            Integer.parseInt(tfNTeléfono.getText());
            teléfono = true;
            tfNTeléfono.setBackground(Color.green);
        }
        catch(Exception ex)
        {
            teléfono = false;
            tfNTeléfono.setBackground(Color.pink);
        }
        //Verificar si busca la foto
        if(Foto)
        {
            btnImagen.setBackground(Color.green);
        }
        else
        {
            btnImagen.setBackground(Color.red);
        }
        //Si todo es correcto, ingresar los registros.
        if(nuevoUsuario && nombre && apellido && nuevaContraseña && fecha && correo && teléfono && Foto)
        {
            //Crear bitácora
            User NuevoUsuario = new User();
            NuevoUsuario.setUser(tfNUsuario.getText());
            NuevoUsuario.setName(tfNNombre.getText());
            NuevoUsuario.setLastName(tfNApellido.getText());
            NuevoUsuario.setPassword(contraseñaEncriptada);
            if(archivos.fileSizeNotZero("c:\\MEIA\\BitácoraUsuarios.txt"))
            {
                NuevoUsuario.setRol("0");
            }
            else
            {
                NuevoUsuario.setRol("1");
            }
            NuevoUsuario.setDate(cbDía.getSelectedItem()+"/"+cbMes.getSelectedItem()+"/"+cbAño.getSelectedItem());
            NuevoUsuario.setEmail(tfNCorreo.getText());
            NuevoUsuario.setCelNumber(Integer.parseInt(tfNTeléfono.getText()));
            NuevoUsuario.setPhotoPath(fotografía);
            NuevoUsuario.setDescription(descripción);
            NuevoUsuario.setStatus(1);
            objSecuencial.ActualizarDescriptor("c:\\MEIA\\DescriptorBU.txt", tfNUsuario.getText() );
            objSecuencial.Insertar("c:\\MEIA\\BitácoraUsuarios.txt", NuevoUsuario, new Solicitud(), new Grupo(), new Mensaje());
            //Devolver valores por defecto.
            tfNUsuario.setBackground(Color.white);
            tfNUsuario.setText("");
            tfNNombre.setBackground(Color.white);
            tfNNombre.setText("");
            tfNApellido.setBackground(Color.white);
            tfNApellido.setText("");
            tfNContraseña.setBackground(Color.white);
            tfNContraseña.setText("");
            tfNCorreo.setBackground(Color.white);
            tfNCorreo.setText("");
            tfNTeléfono.setBackground(Color.white);
            tfNTeléfono.setText("");
            cbDía.setBackground(Color.white);
            cbMes.setBackground(Color.white);
            cbAño.setBackground(Color.white);
            btnImagen.setBackground(Color.white);
            fotografía = "";
            Foto = false;
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void OcultarCerrar(boolean var) {                                            
        // TODO add your handling code here:
        if(var){
        btnRegresar.setVisible(false);
        }
        
    }
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImagen;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbAño;
    private javax.swing.JComboBox<String> cbDía;
    private javax.swing.JComboBox<String> cbMes;
    private javax.swing.JLabel lbCorreo;
    private javax.swing.JLabel lbEslogan;
    private javax.swing.JLabel lbNContraseña;
    private javax.swing.JLabel lbNUsuario;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbNombre1;
    private javax.swing.JLabel lbTeléfono;
    private javax.swing.JTextField tfNApellido;
    private javax.swing.JPasswordField tfNContraseña;
    private javax.swing.JTextField tfNCorreo;
    private javax.swing.JTextField tfNNombre;
    private javax.swing.JTextField tfNTeléfono;
    private javax.swing.JTextField tfNUsuario;
    // End of variables declaration//GEN-END:variables
}
