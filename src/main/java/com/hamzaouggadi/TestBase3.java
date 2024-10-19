package com.hamzaouggadi;

import com.hamzaouggadi.core.Base;
import com.hamzaouggadi.core.OpenGLUtils;

public class TestBase3 extends Base {

    private int programRef;
    private int vaoTri;
    private int vaoQuad;

    private float[] triangleData;
    private float[] quadData;

    @Override
    public void initialize() {
        programRef = OpenGLUtils.initFromFiles("src/main/resources/shaders/Test_2_2.vert", "src/main/resources/shaders/Test_2_2.frag");

    }

    @Override
    public void update() {

    }
}
