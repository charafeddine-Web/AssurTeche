package resources;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/AssurTeche";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static DBConfig instence;
    private Connection connection;


    private DBConfig(){
        try{
            Class.forName(DRIVER);
            this.connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public static  DBConfig getInstance(){
        if (instence == null){
            return new DBConfig();
        }
        return instence;
    }

    public Connection getConnection() {
        return connection;
    }

}
