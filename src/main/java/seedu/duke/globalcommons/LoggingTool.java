package seedu.duke.globalcommons;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class representing logging tool to log into text file without standard IO.
 */
public class LoggingTool {
    Logger logger;
    FileHandler fh;

    public LoggingTool(String loggerName, FileHandler fh) throws IOException {
        logger = Logger.getLogger(loggerName);
        this.fh = fh;
    }

    /**
     * Sets up logger to log at destination file and disables conventional IO logging.
     * Note: Only logging of INFO and above is recorded.
     *
     * @return logger is configured
     */
    public Logger initialize() {
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.INFO);
        return logger;
    }
}
