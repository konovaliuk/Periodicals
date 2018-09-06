package manager;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Julia on 15.08.2018
 */
public class Config {

    private static Config config;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "config";
    public static final String HOME = "HOME";
    public static final String LOGIN = "LOGIN";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String PERIODICAL_INFO = "PERIODICAL_INFO";
    public static final String GET_USER_PERIODICALS = "GET_USER_PERIODICALS";
    public static final String CREATE_PERIODICAL = "CREATE_PERIODICAL";
    public static final String UPDATE_PERIODICAL = "UPDATE_PERIODICAL";
    public static final String CATALOG = "CATALOG";
    public static final String INFO = "INFO";


    public static Config getInstance() {
        if (config == null) {
            config = new Config();
            config.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return config;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
