package nicolis_A_http_cl_01.file;

import java.io.*;
import java.time.LocalTime;
import java.util.Base64;

class http_f_01 {

    public static String creaHeader(long length){

        StringBuilder header;
        header = new StringBuilder();
        header.append("HTTP/1.1 200 OK \n");
        header.append("Date: ").append(LocalTime.now()).append("\n");
        header.append("Content-Length: ").append(length).append("\n");
        header.append("Content-Type: text/html \n\n");

        return header.toString();
    }

    public static boolean inviaFile(String pagina, PrintWriter scrittura) throws IOException {

        pagina = "sito" + pagina;

        // Controllo se la pagina esiste
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
            byte[] image = new byte[8192]; //8192 || foto.length()
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
}
