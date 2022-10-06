/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.action;

import fr.insalyon.dasi.test.mavenproject1.metier.modele.Employe;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ysabatierm
 */
public class HistoriqueEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Long id = (Long)session.getAttribute("id");
        Employe e = Service.chercherEmployeParId(id);
        request.setAttribute("employe", e );
    }
    
}
