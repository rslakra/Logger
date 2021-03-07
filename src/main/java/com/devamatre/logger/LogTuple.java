package com.devamatre.logger;

/**
 * Holds the results of formatting done by {@link LogFormatter}.
 *
 * @author Rohtash Lakra (rslakra.work@gmail.com)
 * @version 1.0.0
 * @since Mar 06, 2021 20:43:32
 */
public class LogTuple {

    public static LogTuple NULL_TUPLE = new LogTuple(null);

    private final String message;
    private final Object[] arguments;
    private final Throwable throwable;

    /**
     * @param message
     */
    public LogTuple(final String message) {
        this(message, null, null);
    }

    /**
     * @param message
     * @param arguments
     * @param throwable
     */
    public LogTuple(String message, Object[] arguments, Throwable throwable) {
        this.message = message;
        this.arguments = arguments;
        this.throwable = throwable;
    }

    /**
     * @param message
     * @param arguments
     */
    public LogTuple(final String message, final Object[] arguments) {
        this(message, arguments, null);
    }

    /**
     * Returns the <code>message</code>.
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the <code>Object[]</code> object.
     *
     * @return
     */
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * Returns the <code>Throwable</code> object.
     *
     * @return
     */
    public Throwable getThrowable() {
        return throwable;
    }

}
