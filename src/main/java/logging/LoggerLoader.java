package logging;

import org.apache.log4j.Logger;

/**
 * Created by Julia on 16.08.2018
 */
public class LoggerLoader {
    public static Logger getLogger(Class clazz) {
        return Logger.getLogger(clazz);
    }
}
