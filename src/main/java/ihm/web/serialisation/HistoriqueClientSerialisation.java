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
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
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
public class HistoriqueClientSerialisation extends Serialisation{

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Client c = (Client) request.getAttribute("client");
        List<Intervention> histo = Service.recupererHistoriqueClient(c);
        
        JsonObject container = new JsonObject();
        Gson gson= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        
        JsonArray jsonHistorique = new JsonArray();
        JsonObject jsonIntervention;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if(histo != null){
            for(Intervention inter : histo){
                jsonIntervention= new JsonObject();
                jsonIntervention.addProperty("type", inter.getType());
                jsonIntervention.addProperty("date", sdf.format(inter.getDateDemande()));
                jsonIntervention.addProperty("statut", inter.getStatut());
                jsonIntervention.addProperty("description", inter.getDescription());
                jsonIntervention.addProperty("commentaire", inter.getCommentaire());
                jsonIntervention.addProperty("employe", inter.getEmployeAssocie().getPrenom());
                jsonHistorique.add(jsonIntervention);
            }
        } 
        container.add("historique",jsonHistorique);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(container));
        out.close();
        
    }
    
}