package ihm.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ihm.web.action.DemanderInterventionLivraisonAction;
import fr.insalyon.dasi.test.mavenproject1.dao.JpaUtil;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Client;
import fr.insalyon.dasi.test.mavenproject1.metier.modele.Employe;
import ihm.web.serialisation.Serialisation;
import ihm.web.serialisation.ClientSerialisation;
import ihm.web.action.Action;
import ihm.web.action.AuthentifierClientAction;
import ihm.web.action.AuthentifierEmployeAction;
import ihm.web.action.CloturerInterventionAction;
import ihm.web.action.ConsulterClientAction;
import ihm.web.action.ConsulterEmployeAction;
import ihm.web.action.DemanderInterventionAnimalAction;
import ihm.web.action.DemanderInterventionIncidentAction;
import ihm.web.action.HistoriqueClientAction;
import ihm.web.action.HistoriqueEmployeAction;
import ihm.web.action.InscriptionAction;
import ihm.web.action.RecupererInterventionEnCoursAction;
import ihm.web.action.RecupererInterventionsDuJourAction;
import ihm.web.action.RecupererInterventionsEnCoursAction;
import ihm.web.serialisation.ConsulterEmployeSerialisation;
import ihm.web.serialisation.ConsulterSerialisation;
import ihm.web.serialisation.DemanderInterventionSerialisation;
import ihm.web.serialisation.EmployeSerialisation;
import ihm.web.serialisation.HistoriqueClientSerialisation;
import ihm.web.serialisation.HistoriqueEmployeSerialisation;
import ihm.web.serialisation.InscriptionSerialisation;
import ihm.web.serialisation.InterventionEnCoursSerialisation;
import ihm.web.serialisation.InterventionsDuJourSerialisation;
import ihm.web.serialisation.InterventionsEnCoursSerialisation;
import ihm.web.serialisation.SerialisationVide;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ysabatierm
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      

        String todo = request.getParameter("todo");
        HttpSession session = request.getSession(true);
      
        switch(todo){
            case "connecterClient":        
                Action action = new AuthentifierClientAction();
                action.executer(request);
                Serialisation serialisation = new ClientSerialisation();
                serialisation.appliquer(request, response);
                if((boolean)request.getAttribute("connexion")){
                    session.setAttribute("user",request.getParameter("login"));
                    session.setAttribute("password", request.getParameter("password"));
                    session.setAttribute("id", ((Client)request.getAttribute("client")).getId());
                }
                break;
            case "connecterEmploye":        
                Action a2 = new AuthentifierEmployeAction();
                a2.executer(request);
                Serialisation se2 = new EmployeSerialisation();
                se2.appliquer(request, response);
                if((boolean)request.getAttribute("connexion")){
                    session.setAttribute("user",request.getParameter("login"));
                    session.setAttribute("password", request.getParameter("password"));
                    session.setAttribute("id", ((Employe)request.getAttribute("employe")).getId());
                }
                break;

            case "inscription":
                Action actionInscription = new InscriptionAction();
                actionInscription.executer(request);
                Serialisation serialInscription = new InscriptionSerialisation();
                serialInscription.appliquer(request, response);
                if((boolean)request.getAttribute("inscrit")){
                    session.setAttribute("user",request.getParameter("login"));
                    session.setAttribute("password", request.getParameter("password"));
                    session.setAttribute("id", ((Client)request.getAttribute("client")).getId());
                }
                break;
                
            case "consulterClient":
                Action actionConsulter = new ConsulterClientAction();
                actionConsulter.executer(request);
                Serialisation serialConsulter = new ConsulterSerialisation();
                serialConsulter.appliquer(request, response);
                

                break;
            case "consulterEmploye":
                Action actionConsulterE = new ConsulterEmployeAction();
                actionConsulterE.executer(request);
                Serialisation serialConsulterE = new ConsulterEmployeSerialisation();
                serialConsulterE.appliquer(request, response);
                break;
                
            case "demanderInterventionIncident":
                Action actionIncident = new DemanderInterventionIncidentAction();
                actionIncident.executer(request);
                Serialisation serialIncident = new DemanderInterventionSerialisation();
                serialIncident.appliquer(request, response);
                break;
                
            case "demanderInterventionAnimal":
                Action actionAnimal = new DemanderInterventionAnimalAction();
                actionAnimal.executer(request);
                Serialisation serialAnimal = new DemanderInterventionSerialisation();
                serialAnimal.appliquer(request, response);
                break;
                
            case "demanderInterventionLivraison":
                Action actionLivr = new DemanderInterventionLivraisonAction();
                actionLivr.executer(request);
                Serialisation serialLivr = new DemanderInterventionSerialisation();
                serialLivr.appliquer(request, response);
                break;
                
            case "cloturerIntervention":
                Action actionCloture = new CloturerInterventionAction();
                actionCloture.executer(request);
                Serialisation s = new SerialisationVide();
                s.appliquer(request, response);
                break;
                
            case "recupererHistoriqueEmploye":
                Action actionHistoEmp = new HistoriqueEmployeAction();
                actionHistoEmp.executer(request);
                Serialisation serialisationHistoEmp = new HistoriqueEmployeSerialisation();
                serialisationHistoEmp.appliquer(request, response);
                break;
                
            case "recupererInterventionEnCours":
                Action interEnCours = new RecupererInterventionEnCoursAction();
                interEnCours.executer(request);
                Serialisation serialisationEnCours1 = new InterventionEnCoursSerialisation();
                serialisationEnCours1.appliquer(request, response);
                break;
                
            case "recupererInterventionsEnCours":
                Action interEnCours2 = new RecupererInterventionsEnCoursAction();
                interEnCours2.executer(request);
                Serialisation serialisationEnCours2 = new InterventionsEnCoursSerialisation();
                serialisationEnCours2.appliquer(request, response);
                break;
                
            case "recupererHistoriqueClient":
                Action actionHistoClient = new HistoriqueClientAction();
                actionHistoClient.executer(request);
                Serialisation serialisationHistoClient = new HistoriqueClientSerialisation();
                serialisationHistoClient.appliquer(request, response);
                break;
                
            case "deconnexion":
                HttpSession sessionDeco = request.getSession(true);
                if(sessionDeco.getAttribute("user")!=null){
                    sessionDeco.removeAttribute("user");
                }
                if(sessionDeco.getAttribute("password")!=null){
                    sessionDeco.removeAttribute("password");
                }
                if(sessionDeco.getAttribute("id")!=null){
                    sessionDeco.removeAttribute("id");
                }
                sessionDeco.invalidate();
                Serialisation vide = new SerialisationVide();
                vide.appliquer(request, response);
                
                break;
                
            case "recupererInterventionsDuJour":
                Action act = new RecupererInterventionsDuJourAction();
                act.executer(request);
                Serialisation ser = new InterventionsDuJourSerialisation();
                ser.appliquer(request, response);
                break;
            default:
                break;
        }
                
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    @Override
    public void init() throws ServletException {
      super.init();
      JpaUtil.init();
    }

    @Override
    public void destroy() {
      JpaUtil.destroy();
      super.destroy();
    }  
}
