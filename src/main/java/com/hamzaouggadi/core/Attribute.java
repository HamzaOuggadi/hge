package com.hamzaouggadi.core;

import static org.lwjgl.opengl.GL40.*;

public class Attribute {

    private String dataType;

    public float[] dataArray;

    private int[] resultArray = new int[1];

    private int bufferRef;

    public Attribute(String dataType, float[] dataArray) {
        this.dataType = dataType;
        this.dataArray = dataArray;
        bufferRef = glGenBuffers();
        uploadData();
    }

    public void uploadData() {
        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);

        glBufferData(GL_ARRAY_BUFFER, dataArray, GL_STATIC_DRAW);
    }

    public void associateVariable(int programRef, String variableName) {
        int variableRef = glGetAttribLocation(programRef, variableName);
        if (variableRef == -1) {
            return;
        }
        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);

        switch (dataType) {
            case "int" -> glVertexAttribPointer(variableRef, 1, GL_INT,
                    false, 0, 0);
            case "float" -> glVertexAttribPointer(variableRef, 1, GL_FLOAT,
                    false, 0, 0);
            case "vec2" -> glVertexAttribPointer(variableRef, 2, GL_FLOAT,
                    false, 0, 0);
            case "vec3" -> glVertexAttribPointer(variableRef, 3, GL_FLOAT,
                    false, 0, 0);
            case "vec4" -> glVertexAttribPointer(variableRef, 4, GL_FLOAT,
                    false, 0, 0);
            default -> throw new RuntimeException(
                    " Attribute " + variableName +
                            " has unknown type " + dataType);
        }

        glEnableVertexAttribArray(variableRef);
    }
}
