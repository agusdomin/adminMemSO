
package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class sistemaOperativo {
    public static void main(String[] args)throws FileNotFoundException {
        
        Admin adminMemoria = new Admin();
        
        File file = new File("/home/agustin/Escritorio/texto.txt");
        Scanner sc = new Scanner(file);
        /*while (sc.hasNextLine()){
            System.out.println("1"+sc.nextLine());
        }*/
        //while(sc.hasNextLine()){
            sc.useDelimiter(",");
            String nombre=sc.next();
            System.out.println(nombre);
            int mem_req=Integer.parseInt(sc.next());
            System.out.println(mem_req);
            int t_trabajo=Integer.parseInt(sc.next());
            System.out.println(t_trabajo);
            int t_arribo=Integer.parseInt(sc.next());
            System.out.println(t_arribo);
            adminMemoria.addProceso(nombre,mem_req,t_trabajo,t_arribo);
        //} 
        adminMemoria.administrar();
    }
}
