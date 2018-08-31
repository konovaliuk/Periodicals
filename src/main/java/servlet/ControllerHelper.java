package servlet;


import commands.commandImpl.*;
import commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Julia on 15.08.2018
 */
public class ControllerHelper {
    private static ControllerHelper controllerHelper;
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("register", new CommandRegister());
        commands.put("login", new CommandLogin());
        commands.put("missing", new CommandMissing());
        commands.put("locale", new CommandLocale());
        commands.put("logout", new CommandLogout());
        commands.put("periodicalInfo", new CommandPeriodicalInfo());
        commands.put("subscribe", new CommandSubscribe());
        commands.put("getUserPeriodicals", new CommandGetUserPeriodicals());
        commands.put("createPeriodical", new CommandCreatePeriodical());
        commands.put("updatePeriodical", new CommandUpdatePeriodical());
        commands.put("getCatalog", new CommandGetCatalog());
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
