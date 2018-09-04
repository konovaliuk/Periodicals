package servlet;

import commands.ICommand;
import logging.LoggerLoader;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 15.08.2018
 */

public class Controller extends HttpServlet {
    private Logger logger = LoggerLoader.getLogger(Controller.class);

    private ControllerHelper controllerHelper = ControllerHelper.getInstance();

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String page;

        ICommand command = controllerHelper.getCommand(request);
        page = command.execute(request, response);
        try {
            if (page.equals("/index.jsp")) {
                response.sendRedirect("/");
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
