
package com.kopiitem.upload.exception;

/**
 *
 * @author donny.fm
 */
public class CustomException extends RuntimeException {

    /**
     * Creates a new instance of <code>CustomException</code> without detail
     * message.
     */
    public CustomException() {
    }

    /**
     * Constructs an instance of <code>CustomException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CustomException(String msg) {
        super(msg);
    }
}
