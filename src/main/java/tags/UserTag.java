package tags;


import persistence.entities.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Julia on 18.08.2018
 */
public class UserTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute("user");
        if (user != null) {
            String login = "Hello, " + user.getName() + "!";
            JspWriter out = pageContext.getOut();
            try {
                out.write(login);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return SKIP_BODY;
    }
}
