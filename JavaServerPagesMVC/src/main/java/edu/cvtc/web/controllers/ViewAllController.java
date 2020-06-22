package edu.cvtc.web.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.cvtc.web.exception.PersonSearchException;
import edu.cvtc.web.model.Person;
import edu.cvtc.web.search.PersonSearch;
import edu.cvtc.web.search.impl.PersonSearchImpl;

/**
 * Servlet implementation class ViewAllController
 */
@WebServlet("/ViewAll")
public class ViewAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String target = null;
		
		try {
		
			final PersonSearch personSearch = new PersonSearchImpl();
			final String sortType = request.getParameter("sort");
			final List<Person> people = personSearch.retrievePeople(sortType);
			
			request.setAttribute("people", people);
			
			target = "view-all.jsp";
		
		} catch (PersonSearchException e) {
			e.printStackTrace();
			request.setAttribute("error", "Sorry, we were unable to complete your request.");
			target = "error.jsp";
		}
		
		request.getRequestDispatcher(target).forward(request, response);
		
		
	}

}
