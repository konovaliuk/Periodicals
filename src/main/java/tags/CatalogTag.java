package tags;

import persistence.entities.Periodical;
import service.PeriodicalService;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;

/**
 * Created by Julia on 22.08.2018
 */
public class CatalogTag extends TagSupport {
    @Override
    public int doStartTag() {
        ServletRequest request = pageContext.getRequest();

        PeriodicalService periodicalService = PeriodicalService.getInstance();
        ArrayList<Periodical> periodicals = periodicalService.getAllPeriodicals(1, 3);
        request.setAttribute("periodicals", periodicals);

        return SKIP_BODY;
    }
}
