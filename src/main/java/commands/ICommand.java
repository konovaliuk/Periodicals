package commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julia on 15.08.2018
 */
public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response) ;
}
