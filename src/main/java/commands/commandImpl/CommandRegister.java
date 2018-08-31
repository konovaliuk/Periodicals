package commands.commandImpl;

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

    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.INCORRECT_PASSWORD));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }

        if (UserService.getUserByLogin(login) != null) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.INCORRECT_LOGIN));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }

        UserRole userRole = UserService.getUserRole("reader");
        User user = new User(userRole, null, name, login, password);
        if (!UserService.register(user)) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
            return Config.getInstance().getProperty(Config.REGISTRATION);
        }

        request.getSession().setAttribute("user", user);
        logger.info("Sign up new user " + user.getLogin());
        return Config.getInstance().getProperty(Config.HOME);
    }

}
