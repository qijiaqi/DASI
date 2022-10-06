/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.action;

import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ysabatierm
 */
public class InscriptionAction extends Action {

    @Override
    public void executer(HttpServletRequest request){
        String genre = request.getParameter("sexe");
        String prenom = request.getParameter("prenom");
        String nomFamille = request.getParameter("nomDeFamille");
        String email = request.getParameter("email");
        String dateNaissance = request.getParameter("dateDeNaissance");
        String telephone = request.getParameter("telephone");
        String motDePasse = request.getParameter("motDePasse");
        String adresse = request.getParameter("adresse");
        //String nom, String prenom, String genre, String noTel, Date dateNaissance, String adresse, String mail, String motDePasse
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date ddn = null;
        try {
            ddn = sd.parse(dateNaissance);
        } catch (ParseException ex) {
            Logger.getLogger(InscriptionAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Client client = new Client(nomFamille, prenom, genre, telephone, ddn, adresse, email, motDePasse);
        Long idClient = Service.inscrireClient(client);
        
        if(idClient==null){
            request.setAttribute("inscrit",false);
            
        }
        else{
            request.setAttribute("inscrit", true);
            
        }   
        Client c = Service.chercherClientParId(idClient);
        request.setAttribute("client", c );
    }
}
