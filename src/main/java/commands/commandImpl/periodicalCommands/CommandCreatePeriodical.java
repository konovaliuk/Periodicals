package commands.commandImpl.periodicalCommands;

import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Info;
import org.apache.log4j.Logger;
import persistence.entities.User;
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
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getUserRole().getRole().equals("admin")) {
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String period = request.getParameter("period");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");

        if (!PeriodicalService.getInstance().createPeriodical(title, type, period, category, price, description)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.INCORRECT_DATA_TRY_AGAIN));
            return Config.getInstance().getProperty(Config.CREATE_PERIODICAL);
        }
        request.setAttribute("info", Info.getInstance().getProperty(Info.DONE));
        logger.info("Created  new periodical " + title);
        return Config.getInstance().getProperty(Config.CREATE_PERIODICAL);
    }
}
