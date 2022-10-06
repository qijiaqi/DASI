/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.action;

import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Intervention;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.InterventionAnimal;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ysabatierm
 */
public class DemanderInterventionAnimalAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Long id = (Long)session.getAttribute("id");
        Client client = Service.chercherClientParId(id);
        
        String desc = request.getParameter("description");
        String animal = request.getParameter("animal");
        Intervention interv = new InterventionAnimal(animal, client, desc);
        
        Long idInterv = Service.creerIntervention(interv, client);
        if(idInterv == null){
            request.setAttribute("demandeEffectuee", false);
        }
        else{
            request.setAttribute("demandeEffectuee", true);
        }
    
    }
    
}
