package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import persistence.entities.Periodical;
import persistence.entities.User;
import service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 22.08.2018
 */
public class CommandSubscribe implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("user") == null) {
            return Config.getInstance().getProperty(Config.LOGIN);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Periodical periodical = (Periodical) request.getSession().getAttribute("periodical");
           /* String period = request.getParameter("period");*/
            if (SubscriptionService.subscribe(user, periodical)) {
                return Config.getInstance().getProperty(Config.USER_PERIODICALS);
            }
        }
        return Config.getInstance().getProperty(Config.HOME);
    }
}
