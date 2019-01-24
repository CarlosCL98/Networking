/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci;

import java.io.InputStreamReader;
import java.net.*;


/**
 *
 * @author 2125262
 */
public class URLDatos {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://campusvirtual.escuelaing.edu.co/moodle/course/view.php?id=304");
        //URL url = new URL("https://github.com/CarlosCL98/Networking/tree/master");
        //URL url = new URL("http://www.google.com/");

        System.out.println("Protocolo: " + url.getProtocol());
        System.out.println("Authority: " + url.getAuthority());
        System.out.println("Host: " + url.getHost());
        System.out.println("Port: " + url.getPort());
        System.out.println("Path: " + url.getPath());
        System.out.println("Query: " + url.getQuery());
        System.out.println("File: " + url.getFile());
        System.out.println("References: " + url.getRef());

    }

}
