package inOutStreams.loading;

import inOutStreams.saving.GameProgress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    static void openZip(String source, String destination){
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(source))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(destination + "\\" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    static void loadAndPrintResults(File file){
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);

    }
    public static void main(String[] args) {
        openZip("D:\\Games\\savegames\\saves.zip", "D:\\Games\\savegames\\");

        File savedDirectory = new File("D:\\Games\\savegames\\");
        if (savedDirectory.exists()){
            for(File item : savedDirectory.listFiles((x1, x2) -> !x2.equals("saves.zip"))){
                loadAndPrintResults(item);
            }
        }


    }
}
