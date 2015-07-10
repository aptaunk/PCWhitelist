import java.net.*;
import org.xbill.DNS.*;

public class DNSProxy {
    
    static final int BUFFERSIZE = 1024;

    public static void start(String dnsServer) {
        DatagramSocket client;
        SimpleResolver remoteDNS;
        Message reply;
        DatagramPacket pack = new DatagramPacket(new byte[BUFFERSIZE],BUFFERSIZE);
        try {
            client = new DatagramSocket(53);
            remoteDNS = new SimpleResolver(dnsServer);
            System.out.println("DNS proxy started on IP address: "+InetAddress.getLocalHost().getHostAddress());
            System.out.println("DNS proxy using this DNS service: "+dnsServer);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
        while (true) {
            try {
                client.receive(pack);
                Message questionMessage = new Message(pack.getData());
                //System.out.println(questionMessage);
                Record question = questionMessage.getQuestion();
                if (Whitelist.isWhitelisted(question.getName().toString())) {
                    reply = remoteDNS.send(questionMessage);
                    //System.out.println(reply);
                    pack.setData(reply.toWire(),0,reply.toWire().length);
                    client.send(pack);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
