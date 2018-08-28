package manager;

import java.util.ResourceBundle;

/**
 * Created by Julia on 15.08.2018
 */
public class Info {
    private static Info message;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "info";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    public static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
    public static final String INCORRECT_DATA_TRY_AGAIN = "INCORRECT_DATA_TRY_AGAIN";
    public static final String YOU_DONT_HAVE_ENOUGH_MONEY = "YOU DON\'T HAVE ENOUGH MONEY";
    public static final String DONE = "DONE";

    public static Info getInstance() {
        if (message == null) {
            message = new Info();
            message.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return message;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
