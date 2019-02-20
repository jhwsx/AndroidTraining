package com.jetpack.demos;

/**
 * @author wzc
 * @date 2019/2/20
 */
public class WebBank {
    boolean loggedIn = false;

    public WebBank(boolean logStatus) {
        loggedIn = logStatus;
    }

    void logIn() {
        loggedIn = true;
    }

    void logOut() {
        loggedIn = false;
    }

    protected void finalize() throws Throwable {
		super.finalize();
        System.out.println(this);
        if (loggedIn) {
            System.out.println("Error: still logged in");
        }
    }

    public static void main(String[] args) {
        WebBank webBank1 = new WebBank(true);
        webBank1.logOut();
        new WebBank(true);
//        System.out.println("Try 1:");
//        System.runFinalization();
//        System.out.println("Try 2:");
//        Runtime.getRuntime().runFinalization();
//        System.out.println("Try 3:");
        System.gc();
//        System.out.println("Try 4:");
//        System.runFinalizersOnExit(true);
    }
}
