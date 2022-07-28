// Java program to illustrate Server side
// Implementation using DatagramSocket
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DatagramPeer02
{
    public static void main(String[] args) throws IOException
    {
        // Step 1 : Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(2002);
        DatagramPacket DpReceive = null;

        InetAddress ip = InetAddress.getLocalHost();

        byte[] receive = new byte[1000];
        byte[] send = new byte[1000];

        Scanner sc = new Scanner(System.in);

        while (true)
        {
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            send = inp.getBytes();

            // Step 2 : Create the datagramPacket for sending
            // the data.
            DatagramPacket DpSend = new DatagramPacket(send, send.length, ip, 2001);

            // Step 3 : invoke the send call to actually send
            // the data.
            ds.send(DpSend);

            // Step 2 : create a DatgramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Step 3 : revieve the data in byte buffer.
            ds.receive(DpReceive);

            System.out.println("Peer 01 says -" + data(receive));

            // Exit the server if the client sends "bye"
            if (data(receive).toString().equals("bye"))
            {
                System.out.println("Peer 01 sent bye.....EXITING");
                break;
            }

            // Clear the buffer after every message.
            receive = new byte[1000];
        }
    }

    // A utility method to convert the byte array
    // data into a string representation.
    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}