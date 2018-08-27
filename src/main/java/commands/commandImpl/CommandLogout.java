package commands.commandImpl;

import commands.ICommand;
import manager.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 21.08.2018
 */
public class CommandLogout implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = (String) request.getSession().getAttribute("locale");
        request.getSession().invalidate();
        request.getSession().setAttribute("locale", locale);
        return Config.getInstance().getProperty(Config.HOME);
    }
}
