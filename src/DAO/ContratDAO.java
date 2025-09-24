package DAO;

import resources.DBConfig;

import java.sql.Connection;

public class ContratDAO {

    private  Connection conn;

    public ContratDAO(){
        this.conn = DBConfig.getInstance().getConnection();
    }







}
