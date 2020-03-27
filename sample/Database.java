package sample;
import java.sql.*;

public class Database {
    private Settings settings;
    private Connection connection;
    public Database(Settings settings){
        this.settings = settings;
        connect();
    }
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("JDBC:mysql://" +settings.getIp()+ "/"+settings.getTablename()+"?useUnicode=true&serverTimezone=UTC",settings.getUsername(),settings.getPassword());
            Statement ex = connection.createStatement();
            ex.executeUpdate("CREATE TABLE IF NOT EXISTS `adversities` (`id` INTEGER AUTO_INCREMENT PRIMARY KEY, `Name` VARCHAR(255) NOT NULL, `Place` VARCHAR(255) NOT NULL, `Price` LONG, `Delivery` BOOLEAN DEFAULT '0', `Url` VARCHAR(255) NOT NULL)");
            System.out.println("Successful connection!");
            ex.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed())
            connect();
        return  connection;
    }
    public boolean isListed(Adverstiment ad) throws SQLException {
        Statement ex = getConnection().createStatement();
        ResultSet dbAd = ex.executeQuery("SELECT * FROM `adversities` WHERE Url ='"+ad.getUrl()+"'");
        if(dbAd.next())
            return true;
        ex.close();
        return false;
    }
    public void saveAdverstiment(Adverstiment ad) throws SQLException {
        Statement ex = getConnection().createStatement();
        ex.executeUpdate("INSERT INTO `adversities` (Name, Place, Price, Url) VALUES('"+ad.getName()+"', '"+ad.getPlace()+"', '"+ ad.getPrice()+"', '"+ad.getUrl()+"')");
        ex.close();
    }
}
