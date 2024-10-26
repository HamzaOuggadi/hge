package com.hamzaouggadi;

import com.hamzaouggadi.core.Attribute;
import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;
import com.hamzaouggadi.core.Uniform;
import org.joml.Math;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class TestBase7 extends Base {

    private int programRef;

    private int vaoRef;

    private Uniform<Vector3f> translation;

    private Uniform<Vector3f> baseColor;

    @Override
    public void initialize() {
        programRef = OpenGLUtils.initFromFiles(
                "src/main/resources/shaders/Test3.vert",
                "src/main/resources/shaders/Test3.frag"
        );

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positionData = {
                0.0f, 0.2f, 0.0f,
                0.2f, -0.2f, 0.0f,
                -0.2f, -0.2f, 0.0f
        };

        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(programRef, "position");

        translation = new Uniform<>("vec3", new Vector3f(-0.5f, 0.0f, 0.0f));
        translation.locateVariable(programRef, "translation");

        baseColor = new Uniform<>("vec3", new Vector3f(1.0f, 0.0f, 0.0f));
        baseColor.locateVariable(programRef, "baseColor");

    }

    @Override
    public void update() {

        translation.data.x = (float) (0.75 * Math.cos(time));
        translation.data.y = (float) (0.75 * Math.sin(time));

        glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(programRef);

        glBindVertexArray(vaoRef);

        translation.uploadData();
        baseColor.uploadData();

        glDrawArrays(GL_TRIANGLES, 0, 3);
    }
}
