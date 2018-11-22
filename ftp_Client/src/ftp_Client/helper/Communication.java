package ftp_Client.helper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adminGRIT
 */
public class Communication {

    public static void  ecriture(Socket connexion, String in) throws IOException{
            PrintWriter Wri =new PrintWriter(new BufferedWriter(new OutputStreamWriter(connexion.getOutputStream())),true);
            Wri.println(in);
            Wri.flush();
        }
    public static String  lecture (Socket connexion) throws IOException{
            BufferedReader in = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            return in.readLine();
    }
}