package util;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Program {
    private Shader vertex, fragment;
    public final int program;

    public Program(String shaderName) {
        program = glCreateProgram();
        try {
            vertex = new Shader(shaderName, Shader.VERTEX);
            glAttachShader(program, vertex.getShader());
            fragment = new Shader(shaderName, Shader.FRAGMENT);
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

    public static void useDefault() {
        glUseProgram(0);
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
