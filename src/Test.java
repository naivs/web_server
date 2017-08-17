import org.eclipse.jetty.http.DateParser;

public class Test {

    public static void main(String[] args) {

        long date = DateParser.parseDate("14-mar-1992 12:03:55");

        for(int i = 0; i < 20; i++) {
            System.out.println("Hello Red planet!! Today: " + date);
        }
    }
}
