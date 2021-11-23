/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Agus
 */
public class ControldeArchivos {
    
    private File OutputLogFile;
    private File OutputTabFile;
    private File OutputResultFile;
    private PrintWriter  escritorLog;
    private PrintWriter  escritorTab;
    private PrintWriter  escritorResult;
    
    public ControldeArchivos(){
        crearArchivos();
    }
    //Manejo de archivos
    public void crearArchivos(){
        try {
            OutputLogFile= new File("Log.txt");
            OutputTabFile= new File("Tabla.txt");
            OutputResultFile= new File("Resultados.txt");
            
            if (OutputLogFile.createNewFile()) {
              System.out.println("Archivo creado " + OutputLogFile.getName());
            } else {
              System.out.println("El archivo ya existe");
            }
            if (OutputTabFile.createNewFile()) {
              System.out.println("Archivo creado  " + OutputTabFile.getName());
            } else {
              System.out.println("El archivo ya existe");
            }
            if (OutputResultFile.createNewFile()) {
              System.out.println("Archivo creado  " + OutputResultFile.getName());
            } else {
              System.out.println("El archivo ya existe");
            }
            
            this.escritorLog = new PrintWriter(this.OutputLogFile);
            this.escritorTab = new PrintWriter(this.OutputTabFile);
            this.escritorResult = new PrintWriter(this.OutputResultFile);
            
        } catch (IOException e) {
            System.out.println("No se pudieron crear los archivos");
            e.printStackTrace();
        }
    }
    public void escribirEvento(String s){
        this.escritorLog.println(s);
    }
    public void estadoTabla(String s){
        escritorTab.println(s);
    }
    public void escribirResultados(String s){    
        escritorResult.println(s);
    }
    public void close(){
        escritorLog.close();
        escritorTab.close();
        escritorResult.close();
    }
}
