package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.powermock.modules.junit4.PowerMockRunner;
import persistence.dao.IUser;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.Periodical;
import persistence.entities.User;
import persistence.entities.UserRole;
import junit.framework.TestCase;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;

import static org.mockito.Mockito.when;


/**
 * Created by Julia on 9/4/2018
 */
@RunWith(PowerMockRunner.class)
public class UserServiceTest {

    @Mock
    private IUser userDAOMock;

    @InjectMocks
    private UserService userService = new UserService();

    private String login;
    private User user;


    @Before
    public void setUp() throws Exception {
        MySqlDAOFactory mySqlDAOFactory = DAOFactory.getMySqlDAOFactory();
        login = "admin";
        user = new User();

    }

    @Test
    public void login() throws SQLException {
        when(userDAOMock.findUserByLogin(login)).thenReturn(user);
    }

    @Test
    public void register() {
    }

    @Test
    public void getUserRole() {
    }

    @Test
    public void getUserByLogin() {
    }
}