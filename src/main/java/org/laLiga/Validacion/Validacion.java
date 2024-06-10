package org.laLiga.Validacion;
import java.util.Scanner;
public class Validacion {

    static Scanner sc = new Scanner(System.in);

    public static int validarInt(String mensaje){
        int valor;
        while (true){
            System.out.print(mensaje);
            try{
               valor =  Integer.parseInt(sc.nextLine());
               break;
            }catch (NumberFormatException e){
                System.out.print("Debe ingresar un dato valido, ");
            }
        }
        return valor;
    }

}
