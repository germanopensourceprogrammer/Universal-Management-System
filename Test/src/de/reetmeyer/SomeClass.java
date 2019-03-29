package de.reetmeyer;

import de.reetmeyer.ums.Module;
import de.reetmeyer.ums.UniversalManagementSystem;

public class SomeClass extends Module {

    @Override
    public String getName() {
        UniversalManagementSystem m = getMaster();

        return "SomeModule";

    }



    public static void run() {
        System.out.println("RUN");
    }
    public void OnLoad(){
        System.out.println("Good");
    }

}
