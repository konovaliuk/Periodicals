package commands.commandImpl.utilCommands;

import commands.ICommand;
import manager.Config;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 15.08.2018
 */
public class CommandMissing implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
       /* if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
            return "/WEB-INF/pages" + request.getRequestURI() + ".jsp";
        }*/
        String path = request.getRequestURI();
        switch (path) {
            case "/login":
                return Config.getInstance().getProperty(Config.LOGIN);
            case "/register":
                return Config.getInstance().getProperty(Config.REGISTRATION);
            case "/catalog":
                return Config.getInstance().getProperty(Config.CATALOG);
            default:
                return Config.getInstance().getProperty(Config.LOGIN);
        }
    }
}
