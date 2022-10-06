/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ysabatierm
 */
public class DemanderInterventionSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        container.addProperty("demandeEffectuee", (Boolean)request.getAttribute("demandeEffectuee"));
        
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
