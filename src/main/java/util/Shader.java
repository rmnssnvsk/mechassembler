package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL20.*;

class Shader {
    public static final int VERTEX = 0, FRAGMENT = 1;
    private int shader;

    public Shader(String name, int type) throws IOException {
        shader = glCreateShader(type == VERTEX ? GL_VERTEX_SHADER : GL_FRAGMENT_SHADER);
        StringBuilder source = new StringBuilder();


        BufferedReader reader = new BufferedReader(new FileReader(new ResourceLoader().getFile(name)));
        String line;
        while ((line = reader.readLine()) != null) {
            source.append(line).append('\n');
        }
        reader.close();
        glShaderSource(shader, source);
        glCompileShader(shader);
        // assert glGetShaderi(shader, GL_COMPILE_STATUS) == GL_TRUE : "Shader was not be able to be compile correctly.";
    }

    public int getShader() {
        return shader;
    }

    public void delete() {
        glDeleteShader(shader);
    }
}
