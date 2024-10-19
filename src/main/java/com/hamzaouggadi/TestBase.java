package com.hamzaouggadi;

import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;

import static org.lwjgl.opengl.GL40.*;

public class TestBase extends Base {

    public int programRef;

    @Override
    public void initialize() {
        programRef = OpenGLUtils.initFromFiles("src/main/resources/shaders/Test_2_2.vert", "src/main/resources/shaders/Test_2_2.frag");

        int vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        glPointSize(20);
    }

    @Override
    public void update() {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        glUseProgram(programRef);

        glDrawArrays(GL_POINT, 0, 1);

    }
}
