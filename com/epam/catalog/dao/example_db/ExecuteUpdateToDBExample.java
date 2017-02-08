package com.epam.catalog.dao.example_db;

import java.sql.*;

public class ExecuteUpdateToDBExample {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
          ///  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/catalog“,"root", "root");
                    st = con.createStatement();
            int countRows = st.executeUpdate("INSERT INTO book (name, id_group) VALUES (\"Баба-Яга\",123456)");

            System.out.println(countRows);
        } catch (ClassNotFoundException | SQLException e){

        }
    }
}
