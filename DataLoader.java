import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataLoader {

    public static void dataConnection() throws SQLException {
        Connection connection = null;


        try {

            String url = "jdbc:mysql://localhost:3306/JavaDatabase";
            String user = "root";
            String password = "Yaqoob50";

            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected");
            }


        }
        catch (SQLException  e)
        {
            System.out.println("password or user invalid");
            e.printStackTrace();
        }
    }
}
