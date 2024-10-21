package com.hamzaouggadi;

import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;
import com.hamzaouggadi.core.Uniform;
import org.joml.Vector3f;

import java.awt.*;

import static org.lwjgl.opengl.GL40.*;

public class TestBase5 extends Base {

    public int programRef;

    public int vaoRef;

    public Uniform<Vector3f> translation1;
    public Uniform<Vector3f> translation2;
    public Uniform<Vector3f> baseColor1;
    public Uniform<Vector3f> baseColor2;

    @Override
    public void initialize() {
        programRef = OpenGLUtils.initFromFiles(
                "src/main/resources/shaders/Test3.vert",
                "src/main/resources/shaders/Test3.frag");

        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positionData = new float[] {
                0.0f, 0.2f, 0.0f,
                0.2f, -0.2f, 0.0f,
                -0.2f, -0.2f, 0.0f
        };

        int vboRef = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboRef);
        glBufferData(GL_ARRAY_BUFFER, positionData, GL_STATIC_DRAW);

        int vertShaderVariableRef = glGetAttribLocation(programRef, "position");
        glVertexAttribPointer(vertShaderVariableRef, 3, GL_FLOAT, false, 0, 0);

        glEnableVertexAttribArray(vertShaderVariableRef);

        translation1 = new Uniform<>("vec3", new Vector3f(-0.5f, 0.0f, 0.0f));
        translation1.locateVariable(programRef, "translation");

        translation2 = new Uniform<>("vec3", new Vector3f(0.5f, 0.0f, 0.0f));
        translation2.locateVariable(programRef, "translation");

        baseColor1 = new Uniform<>("vec3", new Vector3f(1.0f, 0.0f, 0.0f));
        baseColor1.locateVariable(programRef, "baseColor");

        baseColor2 = new Uniform<>("vec3", new Vector3f(0.0f, 0.0f, 1.0f));
        baseColor2.locateVariable(programRef, "baseColor");

    }

    @Override
    public void update() {
        glUseProgram(programRef);
        glBindVertexArray(vaoRef);

        // Draw first triangle
        translation1.uploadData();
        baseColor1.uploadData();
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // Draw second triangle
        translation2.uploadData();
        baseColor2.uploadData();
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }
}
