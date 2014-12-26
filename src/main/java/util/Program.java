package util;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Program {
    public static final Program DUMMY = new Program("shaders/dummy.vert", "shaders/dummy.frag");
    public final int program;
    private Shader vertex, fragment;

    public Program(String vertexShaderName, String fragmentShaderName) {
        program = glCreateProgram();
        try {
            vertex = new Shader(vertexShaderName, Shader.VERTEX);
            glAttachShader(program, vertex.getShader());
            fragment = new Shader(fragmentShaderName, Shader.FRAGMENT);
            glAttachShader(program, fragment.getShader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        glLinkProgram(program);
        glValidateProgram(program);
        IntBuffer buffer = BufferUtils.createIntBuffer(1);
        glGetProgram(program, GL_VALIDATE_STATUS, buffer);
        assert buffer.get() == GL11.GL_TRUE : "Program was not be able to compile correctly";
    }

    public static void useDummy() {
        DUMMY.use();
    }

    public void use() {
        glUseProgram(program);
    }

    public void delete() {
        glDeleteProgram(program);
        vertex.delete();
        fragment.delete();
    }

}
