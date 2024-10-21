package com.hamzaouggadi.core;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.opengl.GL40.*;

public class Uniform<T> {

    // Data type in GLSL
    // int | bool | float | vec2 | vec3 | vec4
    private String dataType;

    // Data to be sent to the uniform variable
    public T data;

    // Store the results of generating buffers
    private int[] resultArray = new int[1];

    // Reference for variable location
    private int variableRef;

    public Uniform(String dataType, T data) {
        this.dataType = dataType;
        this.data = data;
    }

    public void locateVariable(int programRef, String variableName) {
        variableRef = glGetUniformLocation(programRef, variableName);
    }

    public void uploadData() {
        if (variableRef == -1) {
            throw new RuntimeException("variableRef is -1");
        }

        switch (dataType) {
            case "int" -> glUniform1i(variableRef, (Integer) data);
            case "float" -> glUniform1f(variableRef, (Float) data);
            case "bool" -> glUniform1i(variableRef, (Integer) data);
            case "vec2" -> {
                Vector2f v = (Vector2f) data;
                glUniform2f(variableRef, v.x, v.y);
            }
            case "vec3" -> {
                Vector3f v = (Vector3f) data;
                glUniform3f(variableRef, v.x, v.y, v.z);
            }
            case "vec4" -> {
                Vector4f v = (Vector4f) data;
                glUniform4f(variableRef, v.x, v.y, v.z, v.w);
            }
        }
    }
}
