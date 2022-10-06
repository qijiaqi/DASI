/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.web.action;

import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import fr.insalyon.dasi.test.mavenproject1.metier.service.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ysabatierm
 */

public class ConsulterClientAction extends Action{
    @Override
    public void executer(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        Long id = (Long)session.getAttribute("id");
        System.out.println(id);
        Client c = Service.chercherClientParId(id);
        request.setAttribute("client", c );
    }
}
