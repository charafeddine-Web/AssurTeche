package resources;

public class DBConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/AssurTeche";
    private static final String USERNAME = "root";
    private static final String Password = "root";
    private static  final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static String getUsername(){
        return USERNAME;
    }
    public static String getUrl(){
        return URL;
    }
    public static String getPassword(){
        return Password;
    }
    public static String getDriver(){
        return DRIVER;
    }


}
