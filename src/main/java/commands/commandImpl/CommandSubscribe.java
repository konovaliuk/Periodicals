package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import manager.Info;
import persistence.entities.Periodical;
import persistence.entities.User;
import service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

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
            int term = Integer.valueOf(request.getParameter("term"));
            BigDecimal totalAmount = periodical.getPrice();
            if (term != 0) {
                totalAmount = calculateTotalAmount(term, periodical);
            }
            int g = user.getAccount().getAmount().subtract(totalAmount).compareTo(new BigDecimal(0));
            if (user.getAccount().getAmount().subtract(totalAmount).compareTo(new BigDecimal(0)) <= 0) {
                request.setAttribute("info", Info.getInstance().getProperty(Info.YOU_DONT_HAVE_ENOUGH_MONEY));
                return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
            }
            if (SubscriptionService.subscribe(user, periodical, totalAmount)) {
                return Config.getInstance().getProperty(Config.HOME);
            }
        }
        return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
    }

    private BigDecimal calculateTotalAmount(int term, Periodical periodical) {
        return new BigDecimal(term / periodical.getPeriodicalPeriod().getTerm()).multiply(periodical.getPrice());
    }
}
