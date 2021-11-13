
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.*;
import clases.Proceso;
public class sistemaOperativo {
    
    public static void main(String[] args)throws FileNotFoundException {
        
        ArrayList<Proceso> procesos = new ArrayList<>();
        /*
         El formato del archivo de procesos es:
         Nombre, memoria requerida, instante de arribo, tiempo de ejecucion
         */
        File file = new File("C:/Users/Agus/Documents/Proyectos/Java/entrada.txt");
        Scanner sc = new Scanner(file);
        
        while(sc.hasNextLine()){
            
            String[] parametros = sc.nextLine().split(",");
            
            
            String nombre=parametros[0];
            System.out.println(nombre);

            int mem_req=Integer.parseInt(parametros[1]);
            System.out.println(mem_req);

            int t_arribo=Integer.parseInt(parametros[2]);
            System.out.println(t_arribo);

            int t_trabajo=Integer.parseInt(parametros[3]);
            System.out.println(t_trabajo);

            Proceso proceso = new Proceso(nombre,mem_req,t_arribo,t_trabajo);
            procesos.add(proceso);
        } 
        

        /*Proceso proceso = new Proceso("proc1",30,0,6);
        procesos.add(proceso);
        Proceso proceso2 = new Proceso("proc2",20,1,15);
        procesos.add(proceso2);
        Proceso proceso3 = new Proceso("proc3",20,2,4);
        procesos.add(proceso3);
        Proceso proceso4 = new Proceso("proc4",20,3,10);
        procesos.add(proceso4);
        Proceso proceso5 = new Proceso("proc5",30,4,2);
        procesos.add(proceso5);
        Proceso proceso6 = new Proceso("proc6",20,5,8);
        procesos.add(proceso6);
        Proceso proceso7 = new Proceso("proc7",30,6,10);
        procesos.add(proceso7);
        Proceso proceso8 = new Proceso("proc8",10,7,3);
        procesos.add(proceso8);
        */
        
        Admin adminMemoria = new Admin(procesos);
        adminMemoria.administrar();
    }
}
