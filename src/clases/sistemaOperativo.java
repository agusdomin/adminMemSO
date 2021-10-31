
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import clases.Proceso;
public class sistemaOperativo {
    
    public static void main(String[] args)throws FileNotFoundException {
        System.out.println("funciona");
        ArrayList<Proceso> procesos = new ArrayList<>();
        
        /*File file = new File("C:/Users/Agustin/Documents/proyectos/texto.txt");
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
        Proceso proceso = new Proceso("proc1",50,3,2);
        procesos.add(proceso);
        proceso = new Proceso("proc2",10,4,9);
        procesos.add(proceso);
        Admin adminMemoria = new Admin(procesos);
        adminMemoria.administrar();
    }
}
