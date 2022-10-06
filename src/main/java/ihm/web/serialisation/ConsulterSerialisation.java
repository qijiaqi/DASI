/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ysabatierm
 */
public class ConsulterSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Client client = (Client)request.getAttribute("client");
        
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        if(client != null){
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", client.getId());
            jsonClient.addProperty("nom", client.getNom());
            jsonClient.addProperty("prenom", client.getPrenom());
            jsonClient.addProperty("mail", client.getMail());
            jsonClient.addProperty("telephone",client.getNoTel());
            jsonClient.addProperty("genre", client.getGenre());
            container.add("client", jsonClient);
        }   
            
        response.setContentType("application/json;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println(gson.toJson(container));
            out.close();
        }
        catch(IOException ex){
            System.out.println("Pas bien les erreurs"+ex);
        }
    }
}
