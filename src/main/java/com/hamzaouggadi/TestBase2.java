package com.hamzaouggadi;

import com.hamzaouggadi.core.Attribute;
import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;

import static org.lwjgl.opengl.GL40.*;

public class TestBase2 extends Base {

    private int programRef;

    @Override
    public void initialize() {
        programRef = OpenGLUtils.initFromFiles("src/main/resources/shaders/Test_2_2.vert", "src/main/resources/shaders/Test_2_2.frag");

        glLineWidth(4);

        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positionData = {
                0.8f, 0.0f, 0.0f,
                0.4f, 0.6f, 0.0f,
                -0.4f, 0.6f, 0.0f,
                -0.8f, 0.0f, 0.0f,
                -0.4f, -0.6f, 0.0f,
                0.4f, -0.6f, 0.0f
        };

        Attribute positionAttribute = new Attribute("vec3", positionData);

        positionAttribute.associateVariable(programRef, "position");
    }

    @Override
    public void update() {

        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 1.0f, 1.0f);

        glUseProgram(programRef);

        glDrawArrays(GL_LINE_LOOP, 0, 6);
    }
}
