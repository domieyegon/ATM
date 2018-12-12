/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnector;

import java.sql.*;


public class JavaConnect {
    public static Connection ConnectDB(){
       
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/madowdb","root","");
            if(conn!=null){
                System.out.println("Connected to DB");
            }else{
                System.out.println("Could not establish connection!");
            }return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
    
}
