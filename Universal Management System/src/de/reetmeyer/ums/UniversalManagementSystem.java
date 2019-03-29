package de.reetmeyer.ums;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class UniversalManagementSystem {

    public ArrayList<Module> modules = new ArrayList<>();

    public UniversalManagementSystem() {


    }

    private void loadModules(String modulefolder) {

        File f = new File(modulefolder);
        String[] modulefolders = f.list();
        for (String s :
                modulefolders) {
            loadModule(modulefolder + "\\" + s, s);
        }

        //loadModule("E:\\Ums\\modules\\Test");

    }

    private void loadModule(String folderpath, String modulename) {
        System.out.println("Loading Module from " + folderpath);
        String mainclass = null;
        String jar = null;
        if (!new File(folderpath + "\\config").exists()) {
            System.err.println("Module \"" + modulename + "\" has no Config");
            return;
        }
        try {
            String[] lines = Files.readAllLines(Paths.get(folderpath + "\\config")).toArray(new String[0]);
            for (String line :
                    lines) {
                if (line.contains("mainclass")) mainclass = line.replace("mainclass:", "");
                if (line.contains("jar")) jar = line.replace("jar:", "");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mainclass == null || jar == null) {
            System.err.println("Mainclass or Jar is not identified in Config");
            return;
        }
        System.out.println("Starting Load");
        loadJar(folderpath + "\\" + jar, mainclass);
    }

    private void loadJar(String myjar, String myclass) {
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{new URL("file:\\\\\\" + myjar)}, this.getClass().getClassLoader());
            Class classtoload = Class.forName(myclass, true, cl);
            Method met = classtoload.getDeclaredMethod("run");
            Module instance = (Module) classtoload.newInstance();
            met.invoke(null);
            instance.OnLoad();
            System.out.println("Loaded Module:");
            System.out.println("Name: " + instance.getName());
            System.out.println("END");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        UniversalManagementSystem ums = new UniversalManagementSystem();
        ums.loadModules("E:\\Ums\\modules");
    }


}