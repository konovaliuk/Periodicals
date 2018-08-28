package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import manager.Info;
import persistence.entities.User;
import persistence.entities.UserRole;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 16.08.2018
 */
public class CommandRegister implements ICommand {
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "confirmPassword";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
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
            page = Config.getInstance().getProperty(Config.REGISTRATION);
            return page;
        }

        UserRole userRole = UserService.getUserRole("reader");
        User user = new User(userRole, name, login, password);
        if (!UserService.register(user)) {
            request.setAttribute("error", Info.getInstance().getProperty(Info.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.REGISTRATION);
            return page;
        }

        request.getSession().setAttribute("user",user);

        return Config.getInstance().getProperty(Config.HOME);
    }

}
