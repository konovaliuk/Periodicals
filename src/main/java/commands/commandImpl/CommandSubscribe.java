package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import manager.Info;
import persistence.entities.Account;
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
        }
        User user = (User) request.getSession().getAttribute("user");
        Periodical periodical = (Periodical) request.getSession().getAttribute("periodical");
        int term = Integer.valueOf(request.getParameter("term"));
        BigDecimal totalAmount;
        if (term != 0) {
            totalAmount = calculateTotalAmount(term, periodical);
        } else {
            term = periodical.getPeriodicalPeriod().getTerm();
            totalAmount = periodical.getPrice();
        }
        if (!enoughMoney(user.getAccount(), totalAmount)) {
            request.setAttribute("info", Info.getInstance().getProperty(Info.YOU_DONT_HAVE_ENOUGH_MONEY));
            return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
        }
        if (!SubscriptionService.subscribe(user, periodical, totalAmount, term)) {
            return Config.getInstance().getProperty(Config.ERROR);
        }
        return Config.getInstance().getProperty(Config.PERIODICAL_INFO);
        /*return "/WEB-INF/pages/userPeriodicals.jsp";*/
    }

    private BigDecimal calculateTotalAmount(int term, Periodical periodical) {
        return new BigDecimal(term / periodical.getPeriodicalPeriod().getTerm()).multiply(periodical.getPrice());
    }

    private boolean enoughMoney(Account account, BigDecimal totalPrice) {
        return (account.getAmount().subtract(totalPrice).compareTo(new BigDecimal(0)) >= 0);
    }
}
