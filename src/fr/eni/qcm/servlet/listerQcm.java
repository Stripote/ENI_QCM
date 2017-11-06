package fr.eni.qcm.servlet;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.dal.QcmDAO;

/**
 * Servlet implementation class listerQcm
 */
public class listerQcm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listerQcm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			
			ArrayList<Qcm> qcms = QcmDAO.rechercher();
		
			request.getSession().setAttribute("listeQcms", qcms);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/candidat/menuCandidat.jsp");
			dispatcher.forward(request,response);
							
			
		}catch (Exception e){
		
		}
		
	}
}
