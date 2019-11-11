package simplewebclient;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

import edu.boisestate.elgamal.ElGamal;
import edu.boisestate.elgamal.ElGamalMessage;
import edu.boisestate.elgamal.ElGamalPrivateKey;
import edu.boisestate.elgamal.ElGamalPublicKey;

public class SimpleWebClient {
    private static final String hostName = "localhost";
    private static final int PORT = 8089;

    public static void main(String[] args) throws IOException {
        try (
                Socket serverSocket = new Socket(hostName, PORT);

                // Read user input from console
                BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));

                //Send user command to Server Via socket
                DataOutputStream out = new DataOutputStream(serverSocket.getOutputStream());


                // Read server response from socket
                //BufferedReader in =new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        ) {
            //String userInput;

            // Read Biginteger input from client
            BigInteger userInput;
            Scanner sc = new Scanner(System.in);

            //if ((userInput = stdIn.readLine()) != null) {
            if ((userInput = sc.nextBigInteger()) != null) {

                System.out.println("Original message:"+userInput);

                /* Trying to use Elgamal, Testing failed
                
                // take user input and encrypt with elgamal
                ElGamalPrivateKey privateKey = ElGamal.generateKeyPair(1024);
                System.out.println("Elgamal Private Key:"+privateKey);

                ElGamalPublicKey publicKey = new ElGamalPublicKey();
                publicKey.getP();
                ElGamalMessage eMessage= ElGamal.encryptMessage(publicKey,userInput);
                System.out.println("Elgamal message:"+eMessage);



                 */
                out.writeUTF(String.valueOf(userInput));
                out.flush();
                out.close();
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +  hostName);
            System.exit(1);
        }
    }
}
