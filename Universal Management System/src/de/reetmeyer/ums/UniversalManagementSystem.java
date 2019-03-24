package de.reetmeyer.ums;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
    Config parms:
    networkconfig               = networkconfigfilepath

*/

public class UniversalManagementSystem {

    String NETWORKCONFIGKEY = "networkconfig";

    int UNIVERSALMANAGEMENTSYSTEMLEVEL = 1;

    public UniversalManagementSystem (String configfilepath) {
        try {
            Properties config = new Properties();
            config.load(new FileInputStream(configfilepath));
            if (!config.containsKey(NETWORKCONFIGKEY)) {
                System.err.println("Configfile doesn't contains " + NETWORKCONFIGKEY);
                System.exit(1);
            }
            loadPlugins(UNIVERSALMANAGEMENTSYSTEMLEVEL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPlugins(int universalmanagementsystemlevel) {

    }


    static public void main(String[] args) {
        UniversalManagementSystem ums = new UniversalManagementSystem("");
    }

}
