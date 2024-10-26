package com.hamzaouggadi.core;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public abstract class Base {

    private int windowWidth;
    private int windowHeight;

    private long window;

    private boolean running;

    public float time;
    public float deltaTime;

    private long previousTime;
    private long currentTime;

    public Base() {

    };

    public void startup() {

        // Setup an error callback. The default implementation
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new RuntimeException("Unable to initialize GLFW !");
        }

        // Create window and associated OpenGL context

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(windowWidth, windowHeight, "Hamza's Game Engine", 0, 0);

        if (window == 0) {
            throw new RuntimeException("Failed to create a GLFW window !");
        }

        running = true;

        time = 0;
        deltaTime = 1/60f;
        currentTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();

        // Make all OpenGL function calls
        // Apply to this context instance
        glfwMakeContextCurrent(window);

        // Specify number of screen updates
        // to wait before swapping buffers
        // setting to 1 synchronizes application frame rate
        // with display refresh rate
        // prevents visual screen tearing
        glfwSwapInterval(1);

        // detect current context and makes OpenGL bindings available for use
        GL.createCapabilities();
        OpenGLUtils.checkVersion();
    }

    public abstract void initialize();

    public abstract void update();

    public void run(int windowWidth, int windowHeight) {

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        startup();

        // Application specific startup code
        initialize();

        // Main loop
        while (running) {
            // Check for user interaction events
            glfwPollEvents();

            // recalculate time variables
            currentTime = System.currentTimeMillis();
            deltaTime = (currentTime - previousTime) / 1000f;
            time += deltaTime;
            previousTime = currentTime;

            // Check if window close icon is clicked
            if (glfwWindowShouldClose(window)) {
                running = false;
            }
            // Application specific update code
            update();
            // Swap the color buffers
            // to display the rendered graphics on the screen
            glfwSwapBuffers(window);
        }

        shutdown();
    }

    public void shutdown() {

        glfwFreeCallbacks(window);

        glfwDestroyWindow(window);

        glfwTerminate();

        glfwSetErrorCallback(null).free();
    }
}
