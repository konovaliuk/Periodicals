package commands.commandImpl;

import commands.ICommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Julia on 15.08.2018
 */
public class CommandMissing implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/pages" + request.getRequestURI() + ".jsp";
    }
}
