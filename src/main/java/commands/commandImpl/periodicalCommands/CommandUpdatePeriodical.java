package commands.commandImpl.periodicalCommands;

import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Info;
import org.apache.log4j.Logger;
import persistence.entities.Periodical;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by Julia on 31.08.2018
 */
public class CommandUpdatePeriodical implements ICommand {
    private Logger logger = LoggerLoader.getLogger(CommandUpdatePeriodical.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PeriodicalService periodicalService =PeriodicalService.getInstance();

        int periodicalId = ((Periodical) request.getSession().getAttribute("periodical")).getId();
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String period = request.getParameter("period");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");

        if (!periodicalService.updatePeriodical(periodicalId, title, type, period, category, price, description)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.INCORRECT_DATA_TRY_AGAIN));
            return Config.getInstance().getProperty(Config.UPDATE_PERIODICAL);
        }
        Periodical updatedPeriodical = periodicalService.getPeriodical(periodicalId);
        request.setAttribute("info", Info.getInstance().getProperty(Info.DONE));
        request.setAttribute("periodical", updatedPeriodical);
        logger.info("Updated periodical " + title);
        return Config.getInstance().getProperty(Config.UPDATE_PERIODICAL);
    }
}
