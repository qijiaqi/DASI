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
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Intervention;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ysabatierm
 */
public class InterventionEnCoursSerialisation extends Serialisation{

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employe emp = (Employe) request.getAttribute("employe");
        Intervention interv = Service.interventionEnCours(emp);
        System.out.println(emp);
        System.out.println(interv);
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        JsonObject jsonIntervention =null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if(interv != null){
                jsonIntervention= new JsonObject();
                jsonIntervention.addProperty("type", interv.getType());
                jsonIntervention.addProperty("date", sdf.format(interv.getDateDemande()));
                jsonIntervention.addProperty("commentaire", interv.getCommentaire());
                jsonIntervention.addProperty("description", interv.getDescription());
                jsonIntervention.addProperty("adresse", interv.getClient().getAdresse());
                jsonIntervention.addProperty("nbKm", interv.getNbKmParcourusEmploye());
                jsonIntervention.addProperty("statut",interv.getStatut());
               
        } 
        container.add("interventionEnCours",jsonIntervention);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(container));
        out.close(); 
    }
    
}
