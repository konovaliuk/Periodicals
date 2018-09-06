package tags;


import persistence.entities.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Julia on 18.08.2018
 */
public class UserTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        User user = (User) pageContext.getSession().getAttribute("user");
        if (user != null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
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
