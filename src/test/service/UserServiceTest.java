package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import persistence.dao.IUserDAO;
import persistence.dao.IUserRoleDAO;
import persistence.dao.daoFactory.DAOFactory;
import persistence.dao.daoFactory.MySqlDAOFactory;
import persistence.entities.User;
import persistence.entities.UserRole;


import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Julia on 9/5/2018
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DAOFactory.class})
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private MySqlDAOFactory mySqlDAOFactory;
    @Mock
    private IUserDAO iUser;
    @Mock
    private IUserRoleDAO iUserRole;


    private User user = new User();
    private UserRole userRole = new UserRole();


    @Before
    public void setUp() {

        user.setName("name");
        user.setLogin("login");
        user.setPassword("pass");
        userRole.setRole("reader");
        user.setUserRole(userRole);

        // MockitoAnnotations.initMocks(this);

        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getMySqlDAOFactory()).thenReturn(mySqlDAOFactory);

    }

    @Test
    public void login() throws SQLException {
        when(mySqlDAOFactory.getUserDAO()).thenReturn(iUser);
        when(iUser.findUserByLogin(user.getLogin())).thenReturn(user);
        assertNotNull(userService.login(user.getLogin(), user.getPassword()));
    }

    @Test
    public void loginFailedUserNull() throws SQLException {
        when(mySqlDAOFactory.getUserDAO()).thenReturn(iUser);
        when(iUser.findUserByLogin(user.getLogin())).thenReturn(null);
        assertNull(userService.login(user.getLogin(), user.getPassword()));
    }

    @Test
    public void loginFailedNotEqualsPassword() throws SQLException {
        when(mySqlDAOFactory.getUserDAO()).thenReturn(iUser);
        when(iUser.findUserByLogin(user.getLogin())).thenReturn(user);
        assertNull(userService.login(user.getLogin(), ""));
    }

    @Test
    public void getUserRole() throws SQLException {

        when(mySqlDAOFactory.getUserRoleDAO()).thenReturn(iUserRole);
        when(iUserRole.findUserRoleByRole(user.getUserRole().getRole())).thenReturn(userRole);
        assertNotNull(userService.getUserRole(user.getUserRole().getRole()));
    }

    @Test
    public void getUserRoleFailed() throws SQLException {

        when(mySqlDAOFactory.getUserRoleDAO()).thenReturn(iUserRole);
        when(iUserRole.findUserRoleByRole(user.getUserRole().getRole())).thenThrow(new SQLException());
        assertNull(userService.getUserRole(user.getUserRole().getRole()));
    }

    @Test
    public void getUserByLogin() throws SQLException {

        when(mySqlDAOFactory.getUserDAO()).thenReturn(iUser);
        when(iUser.findUserByLogin(user.getLogin())).thenReturn(user);
        assertNotNull(userService.getUserByLogin(user.getLogin()));
    }

    @Test
    public void getUserByLoginFailed() throws SQLException {

        when(mySqlDAOFactory.getUserDAO()).thenReturn(iUser);
        when(iUser.findUserByLogin(user.getLogin())).thenThrow(new SQLException());
        assertNull(userService.getUserByLogin(user.getLogin()));
    }
}

