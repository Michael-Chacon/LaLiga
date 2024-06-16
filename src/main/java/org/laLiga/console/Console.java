package org.laLiga.console;
import  java.util.Scanner;
public class Console {
    private Scanner scanner;

    public Console(){
        this.scanner = new Scanner(System.in);
    }


    public int readInt(String mensaje){
        int valor;
        while (true){
            System.out.print(mensaje);
            try{
                valor =  Integer.parseInt(scanner.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.print("Debe ingresar un dato valido, ");
            }
        }
        return valor;
    }

    public String readString(String mensaje){
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public void closeScanner(){
        scanner.close();
    }
}
