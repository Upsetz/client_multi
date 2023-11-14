import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        try 
        { 
            System.out.println( "Il client è partito" );
            Socket socket = new Socket("172.20.10.2", 4567); //creo il socket e lo connetto al server
            
            Scanner input = new Scanner(System.in); //creo scanner
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //creo bufferedreader che riceve dal server
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());//creo bufferedreader che riceve dal server
            System.out.println( "Connessione effettuata" );
            int risposta = 0;
            do 
            {
                System.out.println( "Inserisci il numero: " );
                String n = input.nextLine();
                out.writeBytes(n+"\n"); //invia al server
                String confronto = in.readLine(); //riceve dal server
                risposta = Integer.parseInt(confronto);
                if(risposta == 1)
                {
                    System.out.println( "Numero troppo piccolo" );
                }
                if(risposta == 2)
                {
                    System.out.println( "Numero troppo grande" );
                }
                if(risposta == 3)
                {
                    System.out.println( "Numero indovinato" );
                }
            } while (risposta != 3);
            input.close(); //chiudo scanner
            socket.close(); //termino soket
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}