
import java.sql.*;
import java.util.Scanner;

public class CreateUser_DB {



    public static void createUser() {

        Connection conn;
        String url = "jdbc:mysql://mysql11.unoeuro.com:3306/matechsolutions_dk_db";
        String userName = "matechsolutions_dk";
        String password = "cenyktwx";

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a username for your user: ");
        String user_username = scan.nextLine();

        System.out.println("Enter a password for your user: ");
        String user_password = scan.nextLine();

        try {
            conn = DriverManager.getConnection(url, userName, password);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO users(username,password) VALUES("+"'"+user_username+"'"+","+"'"+user_password+"'"+")");
            System.out.println("user created");
            UserLogin_DB.run();


        } catch (SQLException e) {

        }

    }
}
