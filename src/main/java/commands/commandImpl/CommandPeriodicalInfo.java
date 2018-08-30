package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import persistence.entities.Period;
import persistence.entities.Periodical;
import persistence.entities.User;
import service.PeriodicalService;
import service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Julia on 21.08.2018
 */
public class CommandPeriodicalInfo implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Periodical periodical = PeriodicalService.getPeriodical(Integer.valueOf(id));
        request.getSession().setAttribute("periodical", periodical);
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null) {
            ArrayList<Periodical> periodicals = SubscriptionService.getUserPeriodicals(user);
            for (Periodical tempPeriodical : periodicals) {
                if (tempPeriodical.equals(periodical)) {
                    request.getSession().setAttribute("isSubscribe", "true");
                    return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
                }
            }
        }
        request.getSession().setAttribute("isSubscribe", "false");
        return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
    }
}
