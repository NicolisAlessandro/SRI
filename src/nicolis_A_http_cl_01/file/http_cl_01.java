package nicolis_A_http_cl_01.file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static nicolis_A_http_cl_01.bin.http_f_01.*;

class http_cl_01 {
    public static void main(String[] args) throws IOException {
        int porta = 2000;
        ServerSocket serverSocketsoket = new ServerSocket(porta);
        Socket socket;

        BufferedReader lettura;
        PrintWriter scrittura;

        String riga;
        String[] elementi;
        String[] estensioni;
        String pagina = "";

        boolean image;
        boolean ris = false;

        while (true) {
            image = false;

            socket = serverSocketsoket.accept();
            lettura = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scrittura = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            do {
                riga = lettura.readLine();

                if (riga != null) {
                    elementi = riga.split(" ");

                    if (elementi[0].equals("GET")) {
                        pagina = elementi[1];

                        if (pagina.equals("/")) pagina = "/index";
                    }

                    if (elementi[0].equals("Accept:")) {
                        estensioni = elementi[1].split(",");

                        if (estensioni[0].equals("text/html")) pagina += ".html";

                        if (estensioni[0].split("/")[0].equals("image")) image = true;
                    }
                }
            } while (!(riga != null && riga.equals("")));
            if (!image && !inviaFile(pagina, scrittura)) {
                if (pagina.split("\\.")[1].equals("html")) {
                    pagina = "/404.html";
                    inviaFile(pagina, scrittura);
                }
            }

            if (image) {
                ris = inviaImage(pagina, scrittura);
            }

            System.out.println(pagina);
            if (image) System.out.println("immagine inviata: " + ris);
            System.out.print("\n");
        }
    }
}