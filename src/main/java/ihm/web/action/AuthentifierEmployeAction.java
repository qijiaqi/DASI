package ihm.web.action;



import fr.insalyon.dasi.test.mavenproject1.metier.modele.Employe;
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
public class AuthentifierEmployeAction extends Action {


    @Override
    public void executer(HttpServletRequest request) {
        String password = request.getParameter("password");
        String login = request.getParameter("login");
        Employe emp = Service.authentifierEmploye(login, password);
        if(emp == null){
            request.setAttribute("connexion", false);
        }
        else{
            request.setAttribute("employe", emp);
            request.setAttribute("connexion", true);
        }
    }
    
}
