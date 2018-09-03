package commands.commandImpl.utilCommands;

import commands.ICommand;
import manager.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 03.09.2018
 */
public class CommandGetPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getRequestURI();
        switch (page) {
            case "/createPeriodical":
                return Config.getInstance().getProperty(Config.CREATE_PERIODICAL);
            case "/updatePeriodical":
                return Config.getInstance().getProperty(Config.UPDATE_PERIODICAL);
            default:
                return Config.getInstance().getProperty(Config.HOME);
        }
    }
}
