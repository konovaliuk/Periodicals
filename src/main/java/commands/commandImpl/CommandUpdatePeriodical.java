package commands.commandImpl;

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
        Periodical periodical = (Periodical) request.getSession().getAttribute("periodical");
        int id = periodical.getId();
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String period = request.getParameter("period");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");

        if (!PeriodicalService.updatePeriodical(id, title, type, period, category, price, description)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.INCORRECT_DATA_TRY_AGAIN));
            return Config.getInstance().getProperty(Config.UPDATE_PERIODICAL);
        }
        Periodical updatedPeriodical = PeriodicalService.getPeriodical(id);
        request.setAttribute("info", Info.getInstance().getProperty(Info.DONE));
        request.getSession().setAttribute("periodical", updatedPeriodical);
        logger.info("Update periodical " + title);
        return Config.getInstance().getProperty(Config.UPDATE_PERIODICAL);
    }
}
