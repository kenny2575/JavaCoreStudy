package inOutStreams.instalation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

    static final long startTime = System.currentTimeMillis();

    static String getTime(){
        return (double)(System.currentTimeMillis() - startTime)/1000 + " ";
    }

    static String mkDirWithLog(File file, String name){
        if (file.exists()){
            return getTime() + "Folder " + name + " already exists\n";
        } else {
            if (file.mkdir()) {
                return getTime() + "Folder " + name + " created!\n";
            } else {
                return getTime() + "Folder " + name + " didn't create!\n";
            }
        }
    }

    static String mkFileWithLog(File file, String name){
        if (file.exists()){
            return getTime() + "File " + name + " already exists\n";
        } else {
            try {
                if (file.createNewFile())
                    return getTime() + "File " + name + " created!\n";
                else
                    return getTime() + "File " + name + " didn't create!\n";
            } catch (Exception e){
                return getTime() + "File " + name + " didn't create!\n" + e + "\n";
            }
        }
    }

    public static void main(String[] args) {

        File rootDir = new File("D://Games");
        StringBuilder logString = new StringBuilder();
        logString.append("Time  Description\n");
        if(rootDir.exists()){
            logString.append(getTime() + "Root folder exists!\n");
            File srcFolder = new File(rootDir, "src");
            File resFolder = new File(rootDir, "res");
            File saveGames = new File(rootDir, "savegames");
            File tmpFolder = new File(rootDir, "temp");

            logString.append(mkDirWithLog(srcFolder, "src"));
            logString.append(mkDirWithLog(resFolder, "res"));
            logString.append(mkDirWithLog(saveGames, "savegames"));
            logString.append(mkDirWithLog(tmpFolder, "temp"));

            if (srcFolder.exists()) {
                File mainFolder = new File(srcFolder, "main");
                File testFolder = new File(srcFolder, "test");

                logString.append(mkDirWithLog(mainFolder, "main"));
                logString.append(mkDirWithLog(testFolder, "test"));

                if (mainFolder.exists()) {
                    File mainFile = new File(mainFolder, "Main.java");
                    File utilsFile = new File(mainFolder, "Utils.java");
                    logString.append(mkFileWithLog(mainFile, "Main.java"));
                    logString.append(mkFileWithLog(utilsFile, "Utils.java"));
                } else {
                    logString.append("main folder doesn't exist!\n");
                }
            } else {
                logString.append("src folder doesn't exist!\n");
            }

            if (resFolder.exists()){
                File drawablesFolder = new File(resFolder, "drawables");
                File vectorsFolder = new File(resFolder, "vectors");
                File iconsFolder = new File(resFolder, "icons");
                logString.append(mkDirWithLog(drawablesFolder, "drawables"));
                logString.append(mkDirWithLog(vectorsFolder, "vectors"));
                logString.append(mkDirWithLog(iconsFolder, "icons"));
            } else {
                logString.append("res folder doesn't exist!\n");
            }

            if (tmpFolder.exists()) {
                File tmpFile = new File(tmpFolder, "temp.txt");
                logString.append(mkFileWithLog(tmpFile, "temp.txt"));
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFile))){
                    bw.write(logString.toString());
                }catch (Exception e){
                    logString.append(e);
                    System.out.println(logString);
                }
            }
        }else{
            logString.append("Root folder doesn't exist!\n");
            System.out.println(logString);
        }
    }
}

