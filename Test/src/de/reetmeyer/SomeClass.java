package de.reetmeyer;

import de.reetmeyer.ums.Module;

public class SomeClass extends Module {

    @Override
    public String getName() {
        return "SomeModule";
    }

    public static void run() {
        System.out.println("RUN");
    }
    public void OnLoad(){
        System.out.println("Good");
    }

}
