/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

/**
 *
 * @author ivan
 */
public interface Logger {
    
    static int DEBUG = 0;
    static int INFO = 1;
    static int WARN = 2;
    static int CRIT = 3;
    
    abstract void log(int LEVEL, String message);

    abstract void log(int LEVEL, String message, Throwable t);

    abstract void setLevel(int LEVEL);
}
