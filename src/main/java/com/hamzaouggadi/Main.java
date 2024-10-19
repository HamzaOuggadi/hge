package com.hamzaouggadi;

import com.hamzaouggadi.utils.StartupHelper;
import org.lwjgl.Version;

public class Main {

    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;

        System.out.println("LWJGL version : " + Version.getVersion());

        new TestBase4().run(800, 600);
    }

}