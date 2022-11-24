import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.*;

public class JDBC_connection
{
    @Test
    public void openConnection_closeConnection()
    {
        Connection connection;

        String url = "jdbc:mysql://mysql11.unoeuro.com:3306/matechsolutions_dk_db";
        String userName = "matechsolutions_dk";
        String password = "cenyktwx";


        try {
            connection = DriverManager.getConnection(url,userName,password);
            Assertions.assertEquals(true,connection.isValid(1));
            connection.close();
            Assertions.assertEquals(false,connection.isValid(1));
        } catch (SQLException e) {

        }


    }
}
