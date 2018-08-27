package manager;

import java.util.ResourceBundle;

/**
 * Created by Julia on 15.08.2018
 */
public class Message {
    private static Message message;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "info";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String INCORRECT_LOGIN = "INCORRECT_LOGIN";
    public static final String INCORRECT_PASSWORD = "INCORRECT_PASSWORD";
    public static final String DONE = "DONE";

    public static Message getInstance() {
        if (message == null) {
            message = new Message();
            message.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return message;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
