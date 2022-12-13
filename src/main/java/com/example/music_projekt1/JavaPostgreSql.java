package com.example.music_projekt1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSql {
    public static void WriteToDatabase(String userName, String userPassword1, String userPassword2){
        String url = "jdbc:postgresql://ep-purple-breeze-177741.eu-central-1.aws.neon.tech/neondb";
        String user = "GhostGapy";
        String password = "G4XZhDPTB0WC";

        String username = userName;
        String pass1 = userPassword1;
        String pass2 = userPassword2;

        String query="INSERT INTO users(username, password1, password2) VALUES(?, ?, ?)";

        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)){

            pst.setString(1, username);
            pst.setString(2, pass1);
            pst.setString(3, pass2);
            pst.executeUpdate();
            System.out.println("Successfully created.");
        }
        catch (SQLException ex){

            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
