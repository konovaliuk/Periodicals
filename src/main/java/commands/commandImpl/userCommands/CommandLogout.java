package commands.commandImpl.userCommands;

import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import org.apache.log4j.Logger;
import persistence.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 21.08.2018
 */
public class CommandLogout implements ICommand {
    private Logger logger = LoggerLoader.getLogger(CommandLogout.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = (String) request.getSession().getAttribute("locale");
        User user = ((User) request.getSession().getAttribute("user"));
        request.getSession().invalidate();
        request.getSession().setAttribute("locale", locale);
        logger.info("Sign out user " + user.getLogin());
        return Config.getInstance().getProperty(Config.HOME);
    }
}
