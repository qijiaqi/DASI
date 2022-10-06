package ihm.web.action;



import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ysabatierm
 */
public class AuthentifierClientAction extends Action {
    


    @Override
    public void executer(HttpServletRequest request) {
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        Client client = Service.authentifierClient(login, password);
        if(client == null){
            request.setAttribute("connexion", false);
        }
        else{
            request.setAttribute("client", client);
            request.setAttribute("connexion", true);
        }
    }
    
}
