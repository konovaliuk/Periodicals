package tags;

import persistence.entities.Periodical;
import service.PeriodicalService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Julia on 22.08.2018
 */
public class CatalogTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        PeriodicalService periodicalService = new PeriodicalService();
        ArrayList<Periodical> periodicals = periodicalService.getPeriodicals();
        pageContext.getSession().setAttribute("periodicals", periodicals);

        return SKIP_BODY;
    }
}
