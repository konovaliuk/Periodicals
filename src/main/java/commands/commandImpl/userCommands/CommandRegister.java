package commands.commandImpl.userCommands;

import commands.ICommand;
import logging.LoggerLoader;
import manager.Config;
import manager.Info;
import org.apache.log4j.Logger;
import persistence.entities.User;
import persistence.entities.UserRole;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 16.08.2018
 */
public class CommandRegister implements ICommand {
    private Logger logger = LoggerLoader.getLogger(CommandRegister.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserService.getInstance();
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.INCORRECT_PASSWORD));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }

        if (userService.getUserByLogin(login) != null) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.INCORRECT_LOGIN));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }

        if (!userService.register(name, login, password)) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }
        User user = userService.getUserByLogin(login);
        request.getSession().setAttribute("user", user);
        logger.info("Sign up new user " + user.getLogin());
        return Config.getInstance().getProperty(Config.HOME);
    }

}
