package model;

/**
 * Created on 12/28/14.
 *
 * @author Mike Sorokin
 */
public class IdDuplicateException extends RuntimeException {
    public IdDuplicateException(String message) {
        super(message);
    }
}
