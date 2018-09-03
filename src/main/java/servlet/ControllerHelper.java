package servlet;


import commands.ICommand;
import commands.commandImpl.periodicalCommands.*;
import commands.commandImpl.subscriptionCommands.CommandSubscriptionsInfo;
import commands.commandImpl.subscriptionCommands.CommandSubscribe;
import commands.commandImpl.userCommands.CommandUserPeriodicals;
import commands.commandImpl.userCommands.CommandLogin;
import commands.commandImpl.userCommands.CommandLogout;
import commands.commandImpl.userCommands.CommandRegister;
import commands.commandImpl.utilCommands.CommandGetPage;
import commands.commandImpl.utilCommands.CommandLocale;
import commands.commandImpl.utilCommands.CommandMissing;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Julia on 15.08.2018
 */
public class ControllerHelper {
    private static ControllerHelper controllerHelper;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("logout", new CommandLogout());
        commands.put("register", new CommandRegister());
        commands.put("getUserPeriodicals", new CommandUserPeriodicals());

        commands.put("createPeriodical", new CommandCreatePeriodical());
        commands.put("deletePeriodical", new CommandDeletePeriodical());
        commands.put("getCatalog", new CommandGetCatalog());
        commands.put("periodicalInfo", new CommandPeriodicalInfo());
        commands.put("updatePeriodical", new CommandUpdatePeriodical());

        commands.put("subscribe", new CommandSubscribe());
        commands.put("subscriptionsInfo", new CommandSubscriptionsInfo());

        commands.put("getPage", new CommandGetPage());
        commands.put("locale", new CommandLocale());
        commands.put("missing", new CommandMissing());

    }

    public static ControllerHelper getInstance() {
        if (controllerHelper == null) {
            controllerHelper = new ControllerHelper();
        }
        return controllerHelper;
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }
}
