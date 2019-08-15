package database;
import java.sql.*;

public class Driver {
    private static Connection c = null;

    private Driver() {

    }

    public static Connection getCon() {
        try {
            if (c == null) {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/colorbuster", "root", "root");
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return c;
    }

    public static void disConnect() {
        try {
            c.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void insert(String name, int points) {
        try {
            PreparedStatement p = Driver.getCon().prepareStatement("insert into players (name,score) values (?,?)");
            p.setString(1,name);
            p.setInt(2,points);
            p.executeUpdate();
            System.out.println("Inserting");
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static String[][] getTop10Scores() {
        try {
            ResultSet r = Driver.getCon().createStatement().executeQuery("SELECT * FROM players ORDER BY score DESC LIMIT 10");
            int count = 1;
            String s[][] = new String[10][2];
            int row = 0;
            while(r.next()) {
                if (r.getString("name") == null) {
                    s[row][0] = count + ". No name";
                } else {
                    s[row][0] = count + ". " + r.getString("name");
                }
                s[row][1] = r.getString("score");
                row++;
                count++;
            }
            return s;
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }


}
