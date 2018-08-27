package commands.commandImpl;

import commands.ICommand;
import manager.Config;
import manager.Message;
import service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by Julia on 27.08.2018
 */
public class CommandCreatePeriodical implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String period = request.getParameter("period");
        String category = request.getParameter("category");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String description = request.getParameter("description");

        if (!PeriodicalService.createPeriodical(title, type, period, category, price, description)) {

        }
        request.setAttribute("info", Message.getInstance().getProperty(Message.DONE));
        return Config.getInstance().getProperty(Config.CREATE_PERIODICAL);
    }
}
