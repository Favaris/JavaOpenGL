package pic.graphcore;

import glm.mat._3.Mat3;
import glm.mat._4.Mat4;
import glm.vec._2.Vec2;
import glm.vec._3.Vec3;
import glm.vec._4.Vec4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GLShader {
    public int ID;
    // constructor generates the shader on the fly
    // ------------------------------------------------------------------------


    public GLShader(String vertexPath, String fragmentPath, String geometryPath) {
        // 1. retrieve the vertex/fragment source code from filePath
        String vertexCode = null;
        String fragmentCode = null;
        String geometryCode = null;

        try (Scanner vShaderFile = new Scanner(new File(vertexPath));
            Scanner fShaderFile = new Scanner(new File(fragmentPath))) {

            // convert stream into string
            StringBuilder sb = new StringBuilder();
            while (vShaderFile.hasNextLine()) {
                sb.append(vShaderFile.nextLine());
            }
            vertexCode = sb.toString();
            sb = new StringBuilder();
            while (fShaderFile.hasNextLine()) {
                sb.append(fShaderFile.nextLine());
            }
            fragmentCode = sb.toString();
            // if geometry shader path is present, also load a geometry shader
            if (geometryPath != null) {
                try (Scanner qShaderFile = new Scanner(new File(geometryPath))) {
                    sb = new StringBuilder();
                    while (qShaderFile.hasNextLine()) {
                        sb.append(qShaderFile.nextLine());
                    }
                    geometryCode = sb.toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 2. compile shaders
        int vertex, fragment;
        // vertex shader
        vertex = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertex, vertexCode);
        glCompileShader(vertex);
        checkCompileErrors(vertex, "VERTEX");
        // fragment Shader
        fragment = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragment, fragmentCode);
        glCompileShader(fragment);
        checkCompileErrors(fragment, "FRAGMENT");
        // if geometry shader is given, compile geometry shader
        int geometry = 0;
        if (geometryPath != null) {
            geometry = glCreateShader(GL_GEOMETRY_SHADER);
            glShaderSource(geometry, geometryCode);
            glCompileShader(geometry);
            checkCompileErrors(geometry, "GEOMETRY");
        }
        // shader Program
        ID = glCreateProgram();
        glAttachShader(ID, vertex);
        glAttachShader(ID, fragment);
        if (geometryPath != null){
            glAttachShader(ID, geometry);
        }
        glLinkProgram(ID);
        checkCompileErrors(ID, "PROGRAM");
        // delete the shaders as they're linked into our program now and no longer necessery
        glDeleteShader(vertex);
        glDeleteShader(fragment);
        if (geometryPath != null){
            glDeleteShader(geometry);
        }
    }

    // activate the shader
    // ------------------------------------------------------------------------

    public void use() {
        glUseProgram(ID);
    }
    // utility uniform functions
    // ------------------------------------------------------------------------

    public void setBool(String name, boolean value) {
        glUniform1i(glGetUniformLocation(ID, name), value ? 1 : 0);
    }
    // ------------------------------------------------------------------------

    public void setInt(String name, int value) {
        glUniform1i(glGetUniformLocation(ID, name), value);
    }
    // ------------------------------------------------------------------------

    public void setFloat(String name, float value) {
        glUniform1f(glGetUniformLocation(ID, name), value);
    }
    // ------------------------------------------------------------------------

    public void setVec2(String name, Vec2 value) {
        float[] val = new float[1];
        val[0] = value.x;
        glUniform2fv(glGetUniformLocation(ID, name), val);
    }

    public void setVec2(String name, float x, float y) {
        glUniform2f(glGetUniformLocation(ID, name), x, y);
    }
    // ------------------------------------------------------------------------

    public void setVec3(String name, Vec3 value) {
        float[] val = new float[1];
        val[0] = value.x;
        glUniform3fv(glGetUniformLocation(ID, name), val);
    }

    public void setVec3(String name, float x, float y, float z) {
        glUniform3f(glGetUniformLocation(ID, name), x, y, z);
    }
    // ------------------------------------------------------------------------

    public void setVec4(String name, Vec4 value) {
        float[] val = new float[1];
        val[0] = value.x;
        glUniform4fv(glGetUniformLocation(ID, name), val);
    }

    public void setVec4(String name, float x, float y, float z, float w) {
        glUniform4f(glGetUniformLocation(ID, name), x, y, z, w);
    }
    // ------------------------------------------------------------------------


    public void setMat3(String name, Mat3 mat) {
        float[] val = new float[1];
        val[0] = mat.m00;
        glUniformMatrix3fv(glGetUniformLocation(ID, name), false, val);
    }
    // ------------------------------------------------------------------------

    public void setMat4(String name, Mat4 mat) {
        float[] val = new float[1];
        val[0] = mat.m00;
        glUniformMatrix4fv(glGetUniformLocation(ID, name), false, val);
    }


    // utility function for checking shader compilation/linking errors.
    // ------------------------------------------------------------------------

    private static void checkCompileErrors(int shader, String type) {
        int[] success = { 0 };
        String infoLog;
        if (!type.equals("PROGRAM")) {
            glGetShaderiv(shader, GL_COMPILE_STATUS, success);
            if (success[0] != 0) {
                infoLog = glGetShaderInfoLog(shader);
                System.out.println("ERROR::SHADER_COMPILATION_ERROR of type: " + type + "\n" + infoLog + "\n -- --------------------------------------------------- -- ");
            }
        }
        else {
            glGetProgramiv(shader, GL_LINK_STATUS, success);
            if (success[0] != 0) {
                infoLog = glGetShaderInfoLog(shader);
                System.out.println("ERROR::PROGRAM_LINKING_ERROR of type: " + type + "\n" + infoLog + "\n -- --------------------------------------------------- -- ");
            }
        }
    }
}
