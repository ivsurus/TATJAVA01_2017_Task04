package com.epam.catalog.dao.example_db;


import java.sql.*;

public class ConnectToDBExample {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String table;
    private String column;
    private String value;

    public ResultSet getRs(){
        return rs;
    }
    public void setTable(String table){
        this.table = table;
    }
    public void setColumn(String column){
        this.column = column;
    }
    public void setValue(String value){
        this.value = table;
    }

    public void find(String serchParameter) throws SQLException {
       String sqlQuery = "SELECT * FROM" +  table +  "where" + column + "=" + value;
        try { /*Объект Connection представляет собой соединение с БД. Сессия соединения
        включает в себя выполняемые SQL-запросы и возвращаемые через соединение
        результаты.*/

           Class.forName("org.gjt.mm.mysql.Driver");
          // con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/testing", "root", "root");
            con = DriverManager.getConnection("jdbc:mysql://localhost/catalog?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci");
            System.out.println("Соединение установлено.");

            st = con.createStatement();
            rs = st.executeQuery(sqlQuery);
            /* while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) +  " " + rs.getString(3) +" "+ rs.getString(4));
            }
            int countRows = st.executeUpdate("INSERT INTO book (idbook, author) VALUES (10,\"222\")");
             System.out.println(countRows);*/

            /*String sql = "INSERT INTO book(idbook, title, author,year) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(2, "Кукушнин");
            ps.setInt(1, 15);
            ps.setString(3, "Кукуш");
            ps.setString(4, "2011");
            ps.executeUpdate();*/

            /*st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM book where idbook = 15");
             while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) +  " " + rs.getString(3) +" "+ rs.getString(4));
            }*/

       } catch (ClassNotFoundException e) {
             System.out.println("!!!!!!11");
        } catch (SQLException e) {
            System.out.println("!!!!!!22");
        } finally {
            try {
                if (con != null) {con.close();}
            } catch (SQLException e) {
                System.out.println("!!!!!!33");
            }
        }
    }
}
