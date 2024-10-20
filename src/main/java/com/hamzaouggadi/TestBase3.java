package com.hamzaouggadi;

import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;

import static org.lwjgl.opengl.GL40.*;

public class TestBase3 extends Base {

    private int programRef;
    private int vaoTri;
    private int vaoQuad;

    private float[] triangleData;
    private float[] triangleColorData;

    private float[] quadData;
    private float[] quadColorData;

    @Override
    public void initialize() {
        programRef = OpenGLUtils.initFromFiles(
                "src/main/resources/shaders/Test_2_2.vert",
                "src/main/resources/shaders/Test_2_2.frag");

        glLineWidth(4);
        glPointSize(5);

        // Triangle
        vaoTri = glGenVertexArrays();
        glBindVertexArray(vaoTri);
        triangleData = new float[]{
                -0.8f, -0.5f, 0.0f,
                0.2f, -0.5f, 0.0f,
                -0.3f, 0.5f, 0.0f
        };
        int triangleVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, triangleVBO);
        glBufferData(GL_ARRAY_BUFFER, triangleData, GL_STATIC_DRAW);

        int position = glGetAttribLocation(programRef, "position");

        if (position == -1) {
            throw new RuntimeException("Position or Color are equal to -1");
        }

        glVertexAttribPointer(position, 3, GL_FLOAT, false, 0, 0);

        glEnableVertexAttribArray(position);

        // Quad
        vaoQuad = glGenVertexArrays();
        glBindVertexArray(vaoQuad);
        quadData = new float[] {
                -0.2f, -0.5f, 0.0f,
                0.7f, -0.5f, 0.0f,
                0.7f, 0.5f, 0.0f,
                -0.2f, 0.5f, 0.0f
        };
        int quadVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, quadVBO);
        glBufferData(GL_ARRAY_BUFFER, quadData, GL_STATIC_DRAW);

        glVertexAttribPointer(position, 3, GL_FLOAT, false, 0, 0);

        glEnableVertexAttribArray(position);
    }

    @Override
    public void update() {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        glUseProgram(programRef);

        // Triangle
        glBindVertexArray(vaoTri);
        glDrawArrays(GL_LINE_LOOP, 0, 3);

        // Quad
        glBindVertexArray(vaoQuad);
        glDrawArrays(GL_LINE_LOOP, 0, 4);
    }
}
