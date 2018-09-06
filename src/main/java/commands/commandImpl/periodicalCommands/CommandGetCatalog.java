package commands.commandImpl.periodicalCommands;

import commands.ICommand;
import manager.Config;
import persistence.entities.Periodical;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Julia on 30.08.2018
 */
public class CommandGetCatalog implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PeriodicalService periodicalService =PeriodicalService.getInstance();

        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        ArrayList<Periodical> periodicals = periodicalService.getAllPeriodicals(currentPage, recordsPerPage);
        request.setAttribute("periodicals", periodicals);

        int rows = periodicalService.getNumberOfRows();
        int nOfPages = (int) Math.ceil((double) rows / (double) recordsPerPage);

        request.setAttribute("numberOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return Config.getInstance().getProperty(Config.CATALOG);
    }
}
