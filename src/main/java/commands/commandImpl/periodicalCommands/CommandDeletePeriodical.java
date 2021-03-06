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

/**
 * Created by Julia on 31.08.2018
 */
public class CommandDeletePeriodical implements ICommand {
    private Logger logger = LoggerLoader.getLogger(CommandDeletePeriodical.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PeriodicalService periodicalService =PeriodicalService.getInstance();
        Periodical periodical = (Periodical) request.getSession().getAttribute("periodical");
        String title = periodical.getTitle();
        if (!periodicalService.deletePeriodical(periodical)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.ERROR));
            return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
        }
        logger.info("Deleted periodical " + title);
        return Config.getInstance().getProperty(Config.HOME);
    }
}
