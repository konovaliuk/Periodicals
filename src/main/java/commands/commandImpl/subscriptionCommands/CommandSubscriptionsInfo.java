package commands.commandImpl.subscriptionCommands;

import commands.ICommand;
import manager.Config;
import persistence.entities.Subscription;
import service.SubscriptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Julia on 31.08.2018
 */
public class CommandSubscriptionsInfo implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Subscription> subscriptions = SubscriptionService.getAllSubscriptions();
        request.setAttribute("subscriptions", subscriptions);
        return Config.getInstance().getProperty(Config.INFO);
    }
}
