import java.io.File;

public class Init
{
    public static void main (String[] args) {
        File whitelistFile = new File("white_list.txt");
        if (args.length >= 1) {
            whitelistFile = new File(args[0]);
            return;
        }
        if (!whitelistFile.isFile()) {
            System.err.println("Can't find the specified file");
            return;
        }
        String dnsIPAddress = "8.8.8.8";
        if (args.length > 1) {
            dnsIPAddress = args[1];
        }
        try {
            Whitelist.load(whitelistFile);
            DNSProxy.start(dnsIPAddress);
        } catch (Exception e) {
            System.err.println(e);
            return;
        }
    }
}
