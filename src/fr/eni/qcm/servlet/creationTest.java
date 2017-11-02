package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Qcm;
import fr.eni.jee.bo.Section;
import fr.eni.jee.dal.QcmDAO;

/**
 * Servlet implementation class creationTest
 */
public class creationTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public creationTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}
	
	
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		ArrayList<Section> lesSections = null;
		try {
			//Création du QCM
			Qcm qcm = QcmDAO.rechercher(Integer.parseInt(request.getAttribute("idQcm").toString()));
			request.setAttribute("qcm", qcm);
			dispatcher = getServletContext().getRequestDispatcher("/candidat/passageTest.jsp");
			dispatcher.forward(request, response);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
