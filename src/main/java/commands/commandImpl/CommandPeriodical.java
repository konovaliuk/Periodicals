package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import persistence.entities.User;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 21.08.2018
 */
public class CommandPeriodical implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        request.getSession().setAttribute("periodical", PeriodicalService.getPeriodical(Integer.valueOf(id)));
        User user = (User) request.getSession().getAttribute("user");
        return Config.getInstance().getProperty(Config.PERIODICAL);
    }
}
