package nicolis_A_httpClServer.bin;

import java.io.*;
import java.net.*;

class httpServer {
        public static void main(String[] args) throws IOException{
                ServerSocket serverSock = new ServerSocket(3232);;
                Socket cs = null;
                int numero = 1;

                while (numero<3){
                        System.out.print("Attesa connessione...");
                        try { cs = serverSock.accept(); }
                        catch (IOException e){
                                System.err.println("Connessione fallita");
                                System.exit(2);
                        }
                        System.out.println("Connessione da " + cs);
                        try{
                                BufferedOutputStream b = new
                                        BufferedOutputStream(cs.getOutputStream());
                                PrintStream os = new PrintStream(b,false);
                                os.println("Nuovo numero: " + numero);
                                numero++;
                                os.println("Stop"); os.close();
                                cs.close();
                        }
                        catch (Exception e){
                                System.out.println("Errore: " +e);
                                System.exit(3);
                        }
                }
        }
}