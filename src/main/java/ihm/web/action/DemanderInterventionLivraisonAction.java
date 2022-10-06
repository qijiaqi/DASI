/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.action;

import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Intervention;
import static fr.insalyon.dasi.test.mavenproject1.metier.modele.InterventionAnimal_.animal;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.InterventionLivraison;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import ihm.web.action.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ysabatierm
 */
public class DemanderInterventionLivraisonAction extends Action {
    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Long id = (Long)session.getAttribute("id");
        Client client = Service.chercherClientParId(id);
        
        String desc = request.getParameter("description");
        String objet = request.getParameter("objet");
        String entreprise = request.getParameter("entreprise");
        Intervention interv = new InterventionLivraison(objet, entreprise, client, desc);
        
        Long idInterv = Service.creerIntervention(interv, client);
        if(idInterv == null){
            request.setAttribute("demandeEffectuee", false);
        }
        else{
            request.setAttribute("demandeEffectuee", true);
        }
    
    }
}
