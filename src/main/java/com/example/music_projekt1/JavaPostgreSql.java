package com.example.music_projekt1;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class JavaPostgreSql {
    public static void Register(String userName, String userPassword1) throws NoSuchAlgorithmException {
        String url = "jdbc:postgresql://ep-purple-breeze-177741.eu-central-1.aws.neon.tech/neondb";
        String user = "GhostGapy";
        String password = "G4XZhDPTB0WC";

        String username = userName;
        String pass1 = PasswordHasher.hashPassword(userPassword1);

        String query="INSERT INTO users(username, password1) VALUES(?, ?)";

        try(Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)){

            pst.setString(1, username);
            pst.setString(2, pass1);
            pst.executeUpdate();
            System.out.println("Successfully created.");
        }
        catch (SQLException ex){

            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


}
