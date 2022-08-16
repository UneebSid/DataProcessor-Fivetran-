import java.sql.*;
import java.util.List;

public class DataLoader

{
    public static Connection dataConnection() throws SQLException
    {
            Connection connection = null;

        try
        {
            String url = "jdbc:mysql://localhost:3306/JavaDatabase";
            String user = "root";
            String password = "Yaqoob50";
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null)
            {
                System.out.println("Connected");
            }

        }

        catch (SQLException e)
        {
            System.out.println("password or user invalid");
            e.printStackTrace();
        }

        return connection;
    }

    public static void createTable(Connection connection) throws SQLException
    {
        if (connection != null)
        {
            Statement statement = connection.createStatement();
            statement.execute("create table Clients (Client_Id INT UNSIGNED NOT NULL AUTO_INCREMENT," +
                                   "PRIMARY KEY (Client_Id), First_Name varchar (25), Last_Name varchar (25), " +
                                   "Phone_Number double , State varchar (30), Zip_Code integer)");
        }

    }

    public static void dataLoading(List<ClientInfo> clientList, Connection dataBaseConnection) throws SQLException
    {
        String insert = "INSERT INTO Clients (First_Name, Last_Name, Phone_Number, State, Zip_Code) Values" +
                "(?,?,?,?,?)";

        if (dataBaseConnection != null)
        {
            PreparedStatement preparedStatement = dataBaseConnection.prepareStatement(insert);
                int batchCount = 0;

                for (int i = 0; i < clientList.size(); i++)
                {
                    dataBaseConnection.setAutoCommit(false);
                    preparedStatement.setString(1, clientList.get(i).firstName);
                    preparedStatement.setString(2, clientList.get(i).lastName);
                    preparedStatement.setLong(3, clientList.get(i).phoneNumber);
                    preparedStatement.setString(4, clientList.get(i).state);
                    preparedStatement.setLong(5, clientList.get(i).zipcode);
                    preparedStatement.addBatch();
                    batchCount++;

                    if(batchCount==200 || i== clientList.size()-1)
                    {
                        preparedStatement.executeBatch();
                        dataBaseConnection.commit();
                        dataBaseConnection.setAutoCommit(true);
                        batchCount = 0;
                    }
                }
        }
    }
}
