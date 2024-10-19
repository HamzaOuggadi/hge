package com.hamzaouggadi;

import org.lwjgl.Version;

public class Main {

    public static void main(String[] args) {
        System.out.println("LWJGL version : " + Version.getVersion());
        new TestBase2().run(800, 600);
    }

}