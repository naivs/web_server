/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author ivan
 */
public class MyLogger implements Logger {

    private static java.util.logging.Logger logger;
    private static Logger instance;

    private MyLogger() {
        logger = java.util.logging.Logger.getAnonymousLogger().getParent();
        logger.removeHandler(logger.getHandlers()[0]);

        Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord arg0) {
                StringBuilder b = new StringBuilder();
                b.append(new Date());
                b.append(" ");
//                b.append(arg0.getSourceClassName());
//                b.append(" ");
//                b.append(arg0.getSourceMethodName());
//                b.append(" ");
                b.append(arg0.getLevel());
                b.append(" ");
                b.append(arg0.getMessage());
                b.append(System.getProperty("line.separator"));
                return b.toString();
            }
        };

        try {
            FileHandler fh = new FileHandler("server.log");
            fh.setFormatter(formatter);
            fh.setLevel(Level.ALL);
            logger.addHandler(fh);
        } catch (IOException e) {
            logger.log(Level.WARNING, "logging problem!", e);
        }
    }

    public static Logger instance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    @Override
    public void log(int LEVEL, String message) {
        logger.log(getLevel(LEVEL), message);
    }

    @Override
    public void log(int LEVEL, String message, Throwable t) {
        logger.log(getLevel(LEVEL), message, t);
    }

    @Override
    public void setLevel(int LEVEL) {
        logger.setLevel(getLevel(LEVEL));
    }

    private Level getLevel(int level) {
        switch (level) {
            case DEBUG:
                return Level.ALL;

            case INFO:
                return Level.INFO;

            case WARN:
                return Level.WARNING;

            case CRIT:
                return Level.SEVERE;

            default:
                return Level.WARNING;
        }
    }
}
