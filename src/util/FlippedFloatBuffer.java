package util;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Created on 10/27/14.
 *
 * @author Mike Sorokin
 */
public class FlippedFloatBuffer {
    public static FloatBuffer asFloatBuffer(float... floats) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
        buffer.put(floats);
        buffer.flip();
        return buffer;
    }
}
