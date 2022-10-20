package nicolis_A_http_cl_01.file;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Objects;

public class http_f_01 {

    public static String creaHeader(long length){

        String header;
        header = "HTTP/1.1 200 OK \n" +
                "Date: " + LocalTime.now() + "\n" +
                "Content-Length: " + length + "\n" +
                "Content-Type: text/html \n\n";

        return header;
    }

    public static boolean inviaFile(String pagina, PrintWriter scrittura) throws IOException {

        pagina = "sito" + pagina;

        File file = new File(pagina);
        if(file.exists()) {
            scrittura.println(creaHeader(file.length()));

            BufferedReader html = new BufferedReader(new FileReader(pagina));
            String riga;
            while((riga = html.readLine()) != null){
                scrittura.println(riga);
            }
            return true;
        }
        return false;
    }

    public static boolean inviaImage(String foto, PrintWriter scrittura) throws IOException {

        foto = "sito" + foto;

        File file = new File(foto);

        if(file.exists()) {

            scrittura.println(creaHeader(file.length()));

            InputStream inputStream = new FileInputStream(foto);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] fine;
            byte[] image = new byte[8192];
            int bytesRead;

            while ((bytesRead = inputStream.read()) != -1) {
                output.write(image, 0, bytesRead);
                System.out.println(bytesRead);
            }

            fine = output.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(fine);

            scrittura.println(base64);

            return true;
        }

        return false;
    }

    public static void main(String... args) throws IOException {

        int porta = 2000;
        ServerSocket serverSocketsoket = new ServerSocket(porta);
        Socket clientSoket;

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

            clientSoket = serverSocketsoket.accept();
            lettura = new BufferedReader(new InputStreamReader(clientSoket.getInputStream()));
            scrittura = new PrintWriter(new OutputStreamWriter(clientSoket.getOutputStream()), true);

            do {

                riga = lettura.readLine();

                if(riga != null) {
                    elementi = riga.split(" ");

                    if (elementi[0].equals("GET")) {
                        pagina = elementi[1];

                        if (pagina.equals("/")) pagina = "/index";
                    }

                    if (elementi[0].equals("Accept:")) {
                        estensioni = elementi[1].split(",");

                        if (estensioni[0].equals("text/html")) pagina += ".html";

                        if(estensioni[0].split("/")[0].equals("image")) image = true;
                    }
                }
            } while (!Objects.equals(riga, ""));

            if (!image && !inviaFile(pagina, scrittura)) {
                if(pagina.split("\\.")[1].equals("html")){
                    pagina = "/404.html";
                    inviaFile(pagina, scrittura);
                }
            }

            if(image){
                ris = inviaImage(pagina, scrittura);
            }

            System.out.println(pagina);
            if(image) System.out.println("immagine inviata: " + ris);
            System.out.print("\n");
        }
    }
}
