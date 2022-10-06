/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Employe;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ysabatierm
 */
public class ConsulterEmployeSerialisation extends Serialisation{
    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Employe employe = (Employe)request.getAttribute("employe");
        
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        if(employe != null){
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("id", employe.getId());
            jsonEmploye.addProperty("genre", employe.getGenre());
            jsonEmploye.addProperty("telephone", employe.getNoTel());
            jsonEmploye.addProperty("agence", employe.getAgence().getNom());
            jsonEmploye.addProperty("kmCumule", employe.getKmCumule());
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("prenom", employe.getPrenom());
            jsonEmploye.addProperty("mail", employe.getMail());
            jsonEmploye.addProperty("dispo", employe.isDisponible());
            container.add("employe", jsonEmploye);
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
