package commands.commandImpl;

import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Info;
import org.apache.log4j.Logger;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by Julia on 27.08.2018
 */
public class CommandCreatePeriodical implements ICommand {
    private Logger logger = LoggerLoader.getLogger(CommandCreatePeriodical.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String period = request.getParameter("period");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");

        if (!PeriodicalService.createPeriodical(title, type, period, category, price, description)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.INCORRECT_DATA_TRY_AGAIN));
            return Config.getInstance().getProperty(Config.CREATE_PERIODICAL);
        }
        request.setAttribute("info", Info.getInstance().getProperty(Info.DONE));
        logger.info("Created  new periodical " + title);
        return Config.getInstance().getProperty(Config.CREATE_PERIODICAL);
    }
}
