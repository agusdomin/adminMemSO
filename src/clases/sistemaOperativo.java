
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class sistemaOperativo {
    
    public static void main(String[] args)throws FileNotFoundException {
        ArrayList<Proceso> procesos = new ArrayList<>();
        File file = new File("C:/Users/Agus/Documents/Proyectos/Java/adminMemSO/entrada.txt");
        Scanner sc = new Scanner(file);
        
        /*
         El formato del archivo de procesos es:
         Nombre, memoria requerida, instante de arribo, tiempo de ejecucion
        */
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
        sc.close();
        
        Admin adminMemoria = new Admin(procesos);
        adminMemoria.administrar();
    }
}
