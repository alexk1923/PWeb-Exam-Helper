package pweb.examhelper.logger;

import lombok.Singular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingController {
    private static Logger logger = LoggerFactory.getLogger(LoggingController.class);

    private LoggingController() {}

    public static Logger getLogger() {
        return logger;
    }
}
