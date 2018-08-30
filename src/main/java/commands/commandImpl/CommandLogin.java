package commands.commandImpl;


import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Info;
import persistence.entities.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Created by Julia on 15.08.2018
 */
public class CommandLogin implements ICommand {
    Logger logger = LoggerLoader.getLogger(CommandLogin.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = UserService.login(login, password);
        if (user == null) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        request.getSession().setAttribute("user", user);
        logger.info("Login new user " + user.getLogin());
        return Config.getInstance().getProperty(Config.HOME);
    }
}
