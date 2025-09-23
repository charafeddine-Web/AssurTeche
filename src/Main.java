import  resources.DBConfig;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        DBConfig db=DBConfig.getInstance();
        Connection conn = db.getConnection();

        if(conn !=  null){
            System.out.println("✅ Connexion réussie !");
        } else {
            System.out.println("❌ Connexion échouée !");
        }
    }
}