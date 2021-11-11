
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import clases.Proceso;
public class sistemaOperativo {
    
    public static void main(String[] args)throws FileNotFoundException {
        
        ArrayList<Proceso> procesos = new ArrayList<>();
        /*
         El formato del archivo de procesos es:
         Nombre, memoria requerida, instante de arribo, tiempo de ejecucion
         
        File file = new File("C:/Users/Agustin/Documents/proyectos/texto.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            System.out.println("1"+sc.nextLine());
        }
        while(sc.hasNextLine()){
            sc.useDelimiter(",");
            String nombre=sc.next();
            System.out.println(nombre);
            int mem_req=Integer.parseInt(sc.next());
            System.out.println(mem_req);
            int t_trabajo=Integer.parseInt(sc.next());
            System.out.println(t_trabajo);
            int t_arribo=Integer.parseInt(sc.next());
            System.out.println(t_arribo);
            Proceso proceso = new Proceso(nombre,mem_req,t_trabajo,t_arribo);
            procesos.add(proceso);
        } */

        Proceso proceso = new Proceso("proc1",30,0,6);
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
        
        
        Admin adminMemoria = new Admin(procesos);
        adminMemoria.administrar();
    }
}
