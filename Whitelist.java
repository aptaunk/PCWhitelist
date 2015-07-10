import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Whitelist
{
    private static ArrayList<String> whitelist = null;
    
    public static void load (File f) throws java.io.FileNotFoundException {
        Scanner sc = new Scanner(f);
        whitelist = new ArrayList<String>() {{
            while (sc.hasNext()) {
                add(sc.next());
            }
        }};
        System.out.println("Whitelist loaded from file: "+f);
    }
    
    public static boolean isWhitelisted(String query) {
        if (whitelist == null) {
            return false;
        }
        for (String approved : whitelist) {
            if (query.contains(approved)) {
                return true;
            }
        }
        return false;
    }
}
