package nicolis_A_http_cl_01.bin;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class http_cl {
    public static void main(String[] args) throws Exception {
        Scanner inp=new Scanner(System.in);

        FileWriter fileout = new FileWriter("testo.html");

        System.out.println("inserisci URL");
        String url=inp.nextLine();
        URL goo = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(goo.openStream()));
        while ( true ) {
            String s = in.readLine();
            if ( s == null )
                break;
            else{
                System.out.println(s);
            }
            fileout.write(s);
            fileout.write('\n');
            fileout.flush();
        }
        in.close();
    }
}
