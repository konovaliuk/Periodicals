package commands.commandImpl.userCommands;

import commands.ICommand;
import manager.Config;
import persistence.entities.Periodical;
import persistence.entities.User;
import service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Julia on 22.08.2018
 */
public class CommandUserPeriodicals implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        ArrayList<Periodical> periodicals = SubscriptionService.getInstance().getUserPeriodicals(user);
        request.setAttribute("userPeriodicals", periodicals);
        return Config.getInstance().getProperty(Config.GET_USER_PERIODICALS);
    }
}
