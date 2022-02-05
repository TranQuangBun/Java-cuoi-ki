import java.sql.*;

public class ConnectDB {
    Connection connection;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    public Connection connect_SQL(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://LAPTOP-BUN\\SQLEXPRESS:1433;database=Quanlythilop10;user=sa;password=123456";
            connection = DriverManager.getConnection(connectionURL);
//            System.out.println("Connected....");
        } catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public int excuteDB(String sql){
        int record = 0;
        try {
            connect_SQL();
            stmt = connection.createStatement();
            record = stmt.executeUpdate(sql);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                connection.close();
                stmt.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return record;
    }
    public ResultSet Danhsach(String sql){
        try {
            connect_SQL();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return rs;
    }

}