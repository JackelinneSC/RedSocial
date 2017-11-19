/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author jsala
 */
public class SecuencialIndizado {
    
    private static final String DESCRIPTOR =  "Desc_";
    private static final String INDICE ="Indice_";
    private static final String BLOQUE = "";
    public static final String GRUPOAMIGOS = "grupo_amigos.txt";
    private static final String RUTA_ABSOLUTA = "c:\\MEIA\\";
    private static final int INDICESIZE = 151; // 30,20,80,20,1
    FileMethods archivos = new FileMethods();
    public String BuscarAmigoEnGrupo(String usuario, String grupo, String usuarioAmigo){
        if (usuario == "" || usuario == null)  return ""; //retorno cadena vacía
        if(grupo == "" || grupo == null) return "";
        if(usuarioAmigo == "" || usuarioAmigo==null) return "";
        return BuscarIndizado(GRUPOAMIGOS, usuario, grupo, usuarioAmigo);
        
        
        
    }
    public String NuevoRegistroIndex(int numRegistro, String posicion, String llave, String siguiente, String estatus ){     
       //FORMATO: Registro|posicion|llave|siguiente|estado
       String registro = "";
       registro+=Rellenar(Integer.toString(numRegistro),30,"#" );
       registro+=Rellenar(posicion, 20, "#");
       registro+=Rellenar(llave, 80, "#");
       registro+=Rellenar(siguiente, 20, "#");
       registro+=estatus + System.getProperty("line.separator");
       
       return registro;        
    }
    private String Rellenar(String var,int cantidad,String relleno) {
        String registro = "";
        registro+=var;
        for (int i = var.length(); i < cantidad; i++) {
           registro+=relleno; 
        }
        return registro+="|"; 
    }
    
    private void CrearIndice(String fileName, String user){
        try{
            File descriptor = new File(RUTA_ABSOLUTA + DESCRIPTOR +INDICE + fileName);
            File indice = new File(RUTA_ABSOLUTA + INDICE + fileName);
            FileWriter escritor;
            if (!descriptor.exists()) {
                if (!indice.exists()) {
                    archivos.createFile(RUTA_ABSOLUTA + DESCRIPTOR +INDICE + fileName);
                    archivos.createFile(RUTA_ABSOLUTA + INDICE + fileName);
                    escritor = new FileWriter(descriptor);
                    Date fecha = new Date();
                    escritor.write("Archivo: " + RUTA_ABSOLUTA + DESCRIPTOR + INDICE + fileName + "\r\n"
                            + "Descripción: índice_del_archivo_" + fileName+"\r\n"
                            + "Tipo: archivo_de_datos\r\n"      
                            + "Organización: secuencial_indizado\r\n"
                            + "Autor: " + user + "\r\n"
                            + "Creado: " + fecha + "\r\n"
                            +"Modificado: " + fecha + "\r\n"
                            +"Separador: |\r\n"
                            + "Llave: Usuario,grupo,Usuario_amigo\r\n"
                            +"Registro_inicial: 0\r\n"
                            + "Bloque_actual: 1");
                    escritor.close();
                   
                
                }
                        
            } 
            
        
        }  catch(IOException e)
        {
           
        }
        
        
        
    }
    private void CrearBloque(int count, String fileName, String user){
        try{
            File descriptor = new File(RUTA_ABSOLUTA + DESCRIPTOR +  BLOQUE  +Integer.toString(count) + fileName);
            File bloque = new File(RUTA_ABSOLUTA + BLOQUE  +Integer.toString(count) + fileName);
            FileWriter escritor;
            if (!descriptor.exists()) {
                if (!bloque.exists()) {
                    archivos.createFile(RUTA_ABSOLUTA + DESCRIPTOR + BLOQUE  +Integer.toString(count)+ fileName);
                    archivos.createFile(RUTA_ABSOLUTA + BLOQUE +Integer.toString(count) + fileName );
                    escritor = new FileWriter(descriptor);
                    Date fecha = new Date();
                    escritor.write("Archivo: " + RUTA_ABSOLUTA + DESCRIPTOR + BLOQUE  +Integer.toString(count) + fileName + "\r\n"
                            + "Descripción: descriptor_bloque\r\n"
                            + "Tipo: archivo_de_datos\r\n"
                            + "Organización: Secuencial\r\n"
                            + "Autor: " + user + "\r\n"
                            + "Creado: " + fecha +"\r\n"
                            + "Modificado: " + fecha +"\r\n"
                            + "Separador: |\r\n"
                            + "No.Registros: 0\r\n"
                            + "Máximo_Registros: 3");
                   
                    escritor.close();
                
                }
                        
            }     
                   
        
        }  catch(IOException e)
        {
            
        }
    }
    private void ActualizarDescriptorIndice(String fileName, int inicial, int bloqueActual, String user){
        try{
            File descriptor = new File(RUTA_ABSOLUTA + DESCRIPTOR +INDICE + fileName);
            Date Fecha = new Date(); 
            RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
            for (int j = 0; j < 4; j++) {
                raf.readLine();
            }
            if(user != null){
                raf.writeBytes("Autor: "+user+"\r\n");
            }
            raf.readLine();
            raf.writeBytes("Modificado: "+Fecha+"\r\n");
            raf.readLine();
            raf.readLine();
            if(inicial != -1)
                raf.writeBytes("Registro_inicial: " + Integer.toString(inicial) +"\r\n"); 
                
            
            if (bloqueActual != 1) 
                raf.writeBytes("Bloque actual: " + Integer.toString(bloqueActual) + "\r\n");
            
            raf.close();
        }
        catch(IOException e)
        {
            
        }
    }
    private void ActualizarDescriptorBloque(int count, String fileName, int numeroRegistro, String user){
        try{
            File descriptor = new File(RUTA_ABSOLUTA + DESCRIPTOR +  BLOQUE  +Integer.toString(count) + fileName);
            Date Fecha = new Date(); 
            RandomAccessFile raf = new RandomAccessFile(descriptor,"rw");
            
            for (int j = 0; j < 4; j++) {
                   
                raf.readLine();
            }
            if (user != null) {
                raf.writeBytes("Autor: "+user+"\r\n");
            }
            raf.readLine();
            raf.writeBytes("Modificado: "+Fecha+"\r\n");
            raf.readLine();
            raf.writeBytes("No.Registros: " + Integer.toString(numeroRegistro) +"\r\n"); //no sé todavía como hacerlo
            raf.close();
                    
                    
        }
        catch(IOException e)
        {
            
        }
    }
    
    public void EscribirIndizado(String fileName, String datos){
        File archivoIndice = new File(RUTA_ABSOLUTA + INDICE + fileName);
        if (!archivoIndice.exists()) {
            //no existe el índice entonces se crea el descriptorBloque y el indice
            CrearIndice(fileName, datos.split(Pattern.quote("|"))[0]);
        }
             try{
                String llaveCompuesta = datos.split(Pattern.quote("|"))[0]+ ","+datos.split(Pattern.quote("|"))[1]+ ","+ datos.split(Pattern.quote("|"))[2];
                //1. validar que no exista otra clave igual
                if (BuscarAmigoEnGrupo(datos.split(Pattern.quote("|"))[0], datos.split(Pattern.quote("|"))[1], datos.split(Pattern.quote("|"))[2]) == "") 
                    return; //Lo debería ser booleano           
                
                //Suponiendo que la clave esté validada
                //2. Se abre el descriptorBloque del indice de archivo 
                //para ver el primer registro y el bloque actual
                int primerRegistro = 0, numeroBloqueActual = 0;
                File descriptorFile = new File(RUTA_ABSOLUTA + DESCRIPTOR + INDICE + fileName);
                RandomAccessFile descriptor = new RandomAccessFile(descriptorFile,"rw");
                String lineat = descriptor.readLine();
                while(lineat!= null) {
                    
                    if (lineat.split(Pattern.quote(" "))[0].equals("Registro_inicial:")) {
                        primerRegistro = Integer.parseInt(lineat.split(" ")[1]);
                      
                    }
                    if(lineat.split(Pattern.quote(" "))[0].equals("Bloque_actual:")){
                        numeroBloqueActual = Integer.parseInt(lineat.split(Pattern.quote(" "))[1]);
                       
                    }
                    lineat = descriptor.readLine();
                }
                //cierro el descriptorBloque del índice
                descriptor.close();
                
                //3. Se valida que exista el bloque actual, sino crearlo.
                File archivoBloque = new File(RUTA_ABSOLUTA + BLOQUE  +Integer.toString(numeroBloqueActual) + fileName);
                if (!archivoBloque.exists()) {
                    CrearBloque(numeroBloqueActual, fileName, datos.split(Pattern.quote("|"))[0]);
                }
                //4. Abrir el descriptorBloque del bloque actual para 
                //obtener el número máx. de registros por bloque, 
                //número de registros 
                int numeroRegistro =0, numeroMaximo = 0;
                RandomAccessFile descriptorBloque = new RandomAccessFile(descriptorFile,"rw");
                String lineaa = descriptorBloque.readLine();
                while(lineaa!= null) {
                    
                    if (lineaa.split(Pattern.quote(" "))[0].equals( "No.Registros:")) {
                        numeroRegistro = Integer.parseInt(lineaa.split(Pattern.quote(" "))[1]);
                      
                    }
                    if(lineaa.split(Pattern.quote(" "))[0].equals("Máximo_Registros:")){
                        numeroMaximo = Integer.parseInt(lineaa.split(Pattern.quote(" "))[1]);
                        
                    }
                    lineaa = descriptorBloque.readLine();
                }
                //cierro el descriptorBloque del bloque
                descriptorBloque.close();
                //validar que el bloque pueda seguir recibiendo registros, sino se crea uno nuevo
                if (numeroRegistro >= numeroMaximo) {
                    numeroBloqueActual++;
                    CrearBloque(numeroBloqueActual, fileName, datos.split(Pattern.quote("|"))[0]);
                    //el nuevo bloque no tiene ningún registro
                    numeroRegistro =0;
                }
                //5. Se abren los arhivos de index y bloque actual para empezar a escribir
                //5.1Se escribe en el bloque hasta el final
                //5.2 Se escribe en el indice (ya se validaron las llaves)
                
                RandomAccessFile indice = new RandomAccessFile(archivoIndice, "rw");
                RandomAccessFile bloque = new RandomAccessFile(archivoBloque, "rw");
                //5.1
                bloque.seek(bloque.length());
                bloque.writeBytes(datos); //asumo que ya viene con el formato
                bloque.close();
                //5.2
                //Se genera la clave para escibirla
                String llaveAEscribir = datos.split(Pattern.quote("|"))[0] + "," + datos.split(Pattern.quote("|"))[1] + datos.split(Pattern.quote("|"))[2];
                String escribirIndex = "";
                String dataActualizada = "";
                if (primerRegistro == 0) {
                    //Es el primer registro en el índice
                    escribirIndex = NuevoRegistroIndex(numeroRegistro +1, Integer.toString(numeroBloqueActual) + "." + Integer.toString(numeroRegistro + 1), llaveAEscribir, "0", "1"  );
                    indice.seek(indice.length());
                    indice.writeBytes(escribirIndex+"\r\n");
                    indice.close();
                    primerRegistro = numeroRegistro++;
                }
                else{
                    int pivot = primerRegistro; 
                    int nuevoRegistro = primerRegistro; 
                    boolean bandera = true;
                    while(bandera){
                        indice.seek((nuevoRegistro-1)*INDICESIZE); //BUSCAR LA LInea DEL REGISTROsupuestamente el máximo de caracteres en el archivo es de 151
                        String linea = indice.readLine();
                        String[] temporal = linea.split(Pattern.quote("|"));
                        //estamos en el primer registro y la calve es mayor
                        if (Integer.parseInt(temporal[3]) == 0 && llaveAEscribir.compareTo(temporal[2]) > 0) {
                            escribirIndex = NuevoRegistroIndex(numeroRegistro+1, Integer.toString(numeroBloqueActual)  +"." + Integer.toString(numeroRegistro + 1), llaveAEscribir,"0", "1");
                            //cambio apuntador
                            temporal[3] = Integer.toString((int)(indice.length() / INDICESIZE + 1));
                            for (int i = 0; i < temporal.length -1; i++) 
                                dataActualizada += temporal[i] + "|";
                            break;
                        }
                        //estamos en la posición del primer registro y la llave es menor
                        else if(nuevoRegistro == numeroRegistro && llaveAEscribir.compareTo(temporal[2]) <= 0 ){
                            primerRegistro = numeroRegistro+1; //le asignamos a "numeroRegistro" el valor de registro de la nueva llave
                            escribirIndex = NuevoRegistroIndex(numeroRegistro+1,Integer.toString(numeroBloqueActual)  +"." + Integer.toString(numeroRegistro + 1), llaveAEscribir,temporal[0], "1"  );
                            break;
                        }
                        //Se coloca en medio de dos llaves
                        else if(llaveAEscribir.compareTo(temporal[2]) <=0){
                            escribirIndex = NuevoRegistroIndex(numeroRegistro + 1,Integer.toString(numeroBloqueActual)  +"." + Integer.toString(numeroRegistro + 1), llaveAEscribir,Integer.toString(nuevoRegistro), "1" );
                            //cambio el apuntador
                            temporal[3] = Integer.toString((int)(indice.length() / INDICESIZE + 1));
                            for (int i = 0; i < temporal.length -1; i++) 
                                dataActualizada += temporal[i] + "|";
                            nuevoRegistro = pivot; 
                            break;
                        }
                        pivot = nuevoRegistro;
                        nuevoRegistro = Integer.parseInt(temporal[3]);
                    }
                    //Lo escribo en indice
                    indice.seek(indice.length());
                    indice.writeBytes(escribirIndex + "\r\n");
                    if (!dataActualizada.equals("")) {
                        indice.seek((nuevoRegistro-1)*INDICESIZE);
                        indice.writeBytes(dataActualizada + "\r\n");
                    }
                    indice.close();
                }
                //Se actualiza los descriptores              
                ActualizarDescriptorIndice(fileName, numeroBloqueActual, primerRegistro+1, null);
                ActualizarDescriptorBloque(numeroBloqueActual,fileName, numeroRegistro+1, null);
            }
            catch (IOException  e){
                
            }
         
        
    }
    public String BuscarIndizado(String fileName, String usuario, String grupo, String usuarioAmigo ){
        try{
            String pivotLlave = usuario + "," + grupo + "," + usuarioAmigo;
            File currentIndice = new File(RUTA_ABSOLUTA + INDICE + fileName);
            if (currentIndice.exists()) {
                File descriptor = new File(RUTA_ABSOLUTA + DESCRIPTOR +INDICE + fileName);
                RandomAccessFile indiceDescriptor = new RandomAccessFile(descriptor, "rw");
                int primerRegistro =0 ;
                for (int i = 0; i < 8; i++) 
                    indiceDescriptor.readLine();
                //creo que al final no me va a servir la variable
                //primerRegistro
                primerRegistro = Integer.parseInt(indiceDescriptor.readLine().split(" ")[1]);
                indiceDescriptor.close();
                RandomAccessFile indice = new RandomAccessFile(currentIndice, "rw");
                while(true){
                    String linea = indice.readLine();
                    String currentLlave = linea.split(Pattern.quote("|"))[2];
                    if (linea.split(Pattern.quote("|"))[3].equals("0")) 
                        break;                    
                    if (currentLlave.equals(pivotLlave)) {
                        indice.close();
                        if (linea.split(Pattern.quote("|"))[4].equals("0")) //estado cero, no devuelvo nada
                            return null;
                        return linea; //registro|posicion|llave|siguiente|estado
                            
                    }
                    indice.close();
                    return null;
                }
                return null;                
            }
        }
        catch(Exception e){
            return null;
        }
        return null;
    }
    public boolean ReorganizarIndizado(String fileName, String user){
        try{
            //1. crear un archivo indiceTemporal
            archivos.createFile(RUTA_ABSOLUTA + INDICE + "_temp.txt");
            File currentIndiceTemporal = new File(RUTA_ABSOLUTA + INDICE + "_temp.txt");
            RandomAccessFile indiceTemporal = new RandomAccessFile(currentIndiceTemporal, "rw");
            //2. se  abre el descriptorIndice del índice para ver cuantos bloques tiene
            File currentDescriptorIndice = new File(RUTA_ABSOLUTA + DESCRIPTOR +INDICE + fileName);
            RandomAccessFile descriptorIndice = new RandomAccessFile(currentDescriptorIndice, "rw");
            for (int i = 0; i < 9; i++) 
                descriptorIndice.readLine();
            int bloqueActual = Integer.parseInt(descriptorIndice.readLine().split(" ")[1]);
            descriptorIndice.close();
            //3. se recorren los archivos bloques y se esciben en el indiceTemporal los activos
            for (int i = 1; i < bloqueActual+1; i++) {
                File currentBloque = new File(RUTA_ABSOLUTA + BLOQUE  +Integer.toString(i) + fileName);
                RandomAccessFile bloque = new RandomAccessFile(currentBloque, "rw");
                if (bloque.length() == 0) 
                    continue;
                while(bloque.getFilePointer() != bloque.length()){
                    String linea = bloque.readLine();
                    if (linea.split(Pattern.quote("|"))[4].equals("0")) 
                        continue;
                    indiceTemporal.writeBytes(linea + "\r\n");
                    
                }
                bloque.close();
                //Se eliminan los bloques leido
                currentBloque.delete();
                //Se elimina el descritor del bloque leido
                File descriptorCurrentBloque = new File(RUTA_ABSOLUTA + DESCRIPTOR +  BLOQUE  +Integer.toString(bloqueActual) + fileName);
                descriptorCurrentBloque.delete();
                
            }
            //4. Se reorganiza todo. 
            //se elimina el indice original y el descriptorIndice del índice
            File indiceOriginal = new File(RUTA_ABSOLUTA + INDICE + fileName);
            indiceOriginal.delete();
            //elimino el descriptor del indice original, el cual ya está abierto arriba
            currentDescriptorIndice.delete();
            
            
            
            //me ubico en el archivo de indice temporal y lo reorganizo
            indiceTemporal.seek(0);
            //escribo en el índiceTemporal 
            while(indiceTemporal.getFilePointer() != indiceTemporal.length())
                EscribirIndizado(fileName, indiceTemporal.readLine());
                
            //mi duda es si el descriptorIndice de indice lleva autor, si es así debo
            //modificar el método de actualizar el descriptorIndice del indice para que
            //tenga un parámetro de autor y se actulice
            ActualizarDescriptorIndice(fileName,-1,-1, user);
            //5.Borrar archivo temporal del indice
            indiceTemporal.close();
            currentIndiceTemporal.delete();
            return true;
        }
        catch(IOException  e){
            return false;
        }
            
    }
    public boolean ActualizarIndizado(String fileName, String datos){
        try{
            File currentIndice = new File(RUTA_ABSOLUTA + INDICE + fileName);
            if (currentIndice.exists()) {
                //abrir el descriptor
                int primerRegistro = 0;
                String llaveDelArchivo = ""; //old key
                String llaveDelParametro = ""; //newk
                String linea = "";
                File currentIndiceDescriptor = new File(RUTA_ABSOLUTA + DESCRIPTOR +INDICE + fileName);
                RandomAccessFile indiceDescriptor = new RandomAccessFile(currentIndiceDescriptor,"rw");
                //obtener el primer registro
                while(indiceDescriptor.getFilePointer() != indiceDescriptor.length()){
                    linea = indiceDescriptor.readLine();
                    if (linea.split(" ")[0].equals("Registro_inicial:")) {
                        primerRegistro = Integer.parseInt(linea.split(" ")[1]);
                        break;
                    }
                }
                indiceDescriptor.close();
                llaveDelParametro = datos.split(Pattern.quote("|"))[0]+ datos.split(Pattern.quote("|"))[1] + datos.split(Pattern.quote("|"))[2];
                //guardo el apuntador de la llave 
                int pivot = primerRegistro; //newR
                int current = primerRegistro; //lastR
                
                //Abro el indice para buscar la llave. busco la línea que contenga la llave.
                RandomAccessFile indice = new RandomAccessFile(currentIndice, "rw");
                linea = indice.readLine();
                while(linea!=null){
                    //me coloco sobre el registro que busco
                    indice.seek((pivot-1)* INDICESIZE);
                    //obtengo la llave
                    String[] temporal = linea.split(Pattern.quote("|"));
                    llaveDelArchivo = temporal[2];
                    //validar si el registro tiene estado 0
                    if (temporal[4].equals("0")) {
                        indice.close();
                        break;
                        //si entra es porque no existe y no a hay nada que atualizar
                    }
                    //se verifican si son iguales, si sí se inserta un nuevo registro y se elimna de manera lógica
                    if (llaveDelArchivo.equals(llaveDelParametro)) {
                        indice.seek((pivot-1)*INDICESIZE);
                        //lo escribo en el indice 
                        indice.writeBytes(NuevoRegistroIndex(Integer.parseInt(temporal[0]), temporal[1], llaveDelArchivo, temporal[3], temporal[4]));
                        //escibir el registro actulizado
                        //1.Abrir el bloque del archivo
                        File currentBloque = new File(RUTA_ABSOLUTA + BLOQUE  + (temporal[1].split(".")[0]) + fileName);
                        RandomAccessFile bloque = new RandomAccessFile(currentBloque, "rw");
                        //me ubico en la posición correcta bloque
                        bloque.seek(Integer.parseInt(temporal[2].split(Pattern.quote("."))[1]) -1 * INDICESIZE);
                        //escribo la data actualizada
                        bloque.writeBytes(datos);
                        //se actualiza el descriptor del archivo
                        File currentBloqueDescriptor = new File(RUTA_ABSOLUTA +DESCRIPTOR +BLOQUE  + (temporal[1].split(Pattern.quote("."))[0]) + fileName);
                        RandomAccessFile bloqueDescriptor = new RandomAccessFile(currentBloqueDescriptor,"rw");
                        int numeroDeRegistros = 0;
                        while(bloqueDescriptor.getFilePointer() != bloqueDescriptor.length()){
                            String lineaD = bloqueDescriptor.readLine();
                            if (lineaD.split(" ")[0].equals("No.Registros:")) {
                                numeroDeRegistros = Integer.parseInt(lineaD.split(" ")[1]);
                                break;
                            }
                        }
                        bloque.close();
                        bloqueDescriptor.close();
                        ActualizarDescriptorBloque(Integer.parseInt(temporal[1].split(Pattern.quote("."))[0]),fileName, numeroDeRegistros-1, null);
                        //2. verifico si se dio de baja los datos actualizados
                        //caso1: es el primer registro y se dio de baja. Cambio su apuntador numeroDeRegistro
                        if (pivot == primerRegistro && datos.split(Pattern.quote("|"))[4].equals("0")) 
                            primerRegistro = Integer.parseInt(temporal[3]);
                        //caso2: se dio de baja pero no es el primer registro
                        else if(datos.split(Pattern.quote("|"))[4].equals("0")){
                            //Buscar el puntero de la llave que estoy usando
                            //Posicionarme en el indice en el registro anterior del registro que se dio de baja
                            //Guardo esa linea 
                            //Vuelvo a colocarme en la posicion del registro anterior
                            //Se actualiza el índice, le mando los valores de la nueva linea y el apuntador obtenido al inicio
                            String puntero = temporal[3];
                            indice.seek((current-1)*INDICESIZE);
                            temporal = indice.readLine().split(Pattern.quote("|"));
                            indice.seek((current-1)*INDICESIZE);
                            indice.writeBytes(NuevoRegistroIndex(Integer.parseInt(temporal[0]),temporal[1], temporal[2], puntero, temporal[4]));
                        }
                        //actualizo el descriptor del indice
                        indice.close();
                        ActualizarDescriptorIndice(fileName, -1, primerRegistro,null);
                                                                            
                    }
                    current = pivot; // cambio los punteros
                    pivot = Integer.parseInt(temporal[3]); // el nuevo puntero apunta a la llave que se está leyendo
                    
                }
                indice.close();
                ActualizarDescriptorIndice(fileName, -1, primerRegistro,null);
                return false; //false porque no se actualizó nada
            }                
            else
                return false; //no existe el archivo
            
        }
        catch(IOException e){
            return false;
        }
        
    }
}
