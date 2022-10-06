package ihm.web.serialisation;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Employe;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Intervention;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ysabatierm
 */
public class EmployeSerialisation extends Serialisation{

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employe emp = (Employe)request.getAttribute("employe");
        
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        container.addProperty("connexion", (Boolean)request.getAttribute("connexion"));
        if(emp != null){
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("id", emp.getId());
            jsonEmploye.addProperty("nom", emp.getNom());
            jsonEmploye.addProperty("prenom", emp.getPrenom());
            jsonEmploye.addProperty("mail", emp.getMail());
            jsonEmploye.addProperty("dispo",emp.isDisponible());
            container.add("employe", jsonEmploye);
        }
        
            
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(container));
        out.close();
       
    }
    
}
