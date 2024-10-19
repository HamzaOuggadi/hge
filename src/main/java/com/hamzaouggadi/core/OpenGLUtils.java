package com.hamzaouggadi.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL40.*;

public class OpenGLUtils {

    static int[] status = new int[1];

    public static int initFromFiles(String vertexShaderFileName, String fragmentShaderFileName) {
        return initProgram(readFile(vertexShaderFileName), readFile(fragmentShaderFileName));
    }

    public static String readFile(String fileName) {
        String text = "";

        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text;
    }

    public static int initShader(String shaderCode, int shaderType) {

        int shaderRef = glCreateShader(shaderType);
        glShaderSource(shaderRef, shaderCode);
        glCompileShader(shaderRef);

        glGetShaderiv(shaderRef, GL_COMPILE_STATUS, status);

        if (status[0] == GL_FALSE) {
            // Retrieve error message
            String errorMessage = glGetShaderInfoLog(shaderRef);
            glDeleteShader(shaderRef);
            throw new RuntimeException(errorMessage);
        }

        return shaderRef;
    }

    public static int initProgram(String vertexShaderCode, String fragmentShaderCode) {

        int vertexShaderRef = initShader(vertexShaderCode, GL_VERTEX_SHADER);
        int fragmentShaderRef = initShader(fragmentShaderCode, GL_FRAGMENT_SHADER);

        int programRef = glCreateProgram();

        glAttachShader(programRef, vertexShaderRef);
        glAttachShader(programRef, fragmentShaderRef);

        glLinkProgram(programRef);

        glGetProgramiv(programRef, GL_LINK_STATUS, status);
        if (status[0] == GL_FALSE) {
            String errorMessage = glGetProgramInfoLog(programRef);
            glDeleteProgram(programRef);
            throw new RuntimeException(errorMessage);
        }

        return programRef;
    }

    public static void checkVersion() {
        System.out.println("Vendor : " + glGetString(GL_VENDOR));
        System.out.println("Renderer : " + glGetString(GL_RENDERER));
        System.out.println("OpenGL version supported : " + glGetString(GL_VERSION));
    }
}
