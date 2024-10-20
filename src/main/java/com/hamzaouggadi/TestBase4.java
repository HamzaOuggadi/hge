package com.hamzaouggadi;

import com.hamzaouggadi.core.Attribute;
import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;

import static org.lwjgl.opengl.GL40.*;

public class TestBase4 extends Base {

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
                "src/main/resources/shaders/Test_2_3.vert",
                "src/main/resources/shaders/Test_2_3.frag");

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
        triangleColorData = new float[] {
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f
        };

        Attribute trianglePositionAttr = new Attribute("vec3", triangleData);
        trianglePositionAttr.associateVariable(programRef, "position");

        Attribute triangleColorAttr = new Attribute("vec3", triangleColorData);
        triangleColorAttr.associateVariable(programRef, "vertexColor");


        // Quad
        vaoQuad = glGenVertexArrays();
        glBindVertexArray(vaoQuad);
        quadData = new float[] {
                -0.2f, -0.5f, 0.0f,
                0.7f, -0.5f, 0.0f,
                0.7f, 0.5f, 0.0f,
                -0.2f, 0.5f, 0.0f
        };
        quadColorData = new float[] {
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f,
                1.0f, 0.0f, 1.0f
        };

        Attribute quadPositionAttr = new Attribute("vec3", quadData);
        quadPositionAttr.associateVariable(programRef, "position");

        Attribute quadColorAttr = new Attribute("vec3", quadColorData);
        quadColorAttr.associateVariable(programRef, "vertexColor");

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
        glDrawArrays(GL_TRIANGLE_FAN, 0, 4);
    }
}
