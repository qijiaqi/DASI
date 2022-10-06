/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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
public class InterventionsDuJourSerialisation extends Serialisation{

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employe emp = (Employe) request.getAttribute("employe");
        List<Intervention> histo = Service.getInterventionsDuJour(emp);
        
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        JsonObject jsonKmCumul = new JsonObject();
        jsonKmCumul.addProperty("nbKmCumul",Service.getKmCumulJournee(emp));
        container.add("kmCumul", jsonKmCumul);
        
        JsonObject jsonNbIntervention = new JsonObject();
        jsonNbIntervention.addProperty("nbIntervention",histo.size());
        container.add("nbIntervention", jsonNbIntervention);
        
        JsonObject jsonPosAgence = new JsonObject();
        jsonPosAgence.addProperty("lat",emp.getAgence().getLatitude());
        jsonPosAgence.addProperty("long",emp.getAgence().getLongitude());
        container.add("posAgence", jsonPosAgence);
        
        JsonArray jsonHistorique = new JsonArray();
        JsonObject jsonIntervention;
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH'h'mm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if(histo != null){
            for(Intervention inter : histo){
                jsonIntervention= new JsonObject();
                jsonIntervention.addProperty("type", inter.getType());
                jsonIntervention.addProperty("lat", inter.getClient().getLatitude());
                jsonIntervention.addProperty("long", inter.getClient().getLongitude());
                jsonIntervention.addProperty("date", sdf.format(inter.getDateDemande()));
                jsonIntervention.addProperty("heure", sdf2.format(inter.getDateDemande()));
                jsonIntervention.addProperty("commentaire", inter.getCommentaire());
                jsonIntervention.addProperty("nbKm", inter.getNbKmParcourusEmploye());
                jsonIntervention.addProperty("statut",inter.getStatut());
                jsonIntervention.addProperty("adresse", inter.getClient().getAdresse());
                jsonIntervention.addProperty("prenomClient", inter.getClient().getPrenom());
                
                jsonHistorique.add(jsonIntervention);
     
            }
        } 
        container.add("interventionsDuJour",jsonHistorique);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
    }
    
}
