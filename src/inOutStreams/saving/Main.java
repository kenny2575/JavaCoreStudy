package inOutStreams.saving;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    static void saveGame(GameProgress progress, String destination){
        try (FileOutputStream fos = new FileOutputStream(destination);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
             oos.writeObject(progress);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameProgress firstPlayer = new GameProgress(100, 3, 17, 284.43);
        GameProgress secondPlayer = new GameProgress(87, 7, 21, 123.54);
        GameProgress thirdPlayer = new GameProgress(20, 1, 1, 43);

        saveGame(firstPlayer, "D:\\Games\\savegames\\firstPlayerSave.dat");
        saveGame(secondPlayer, "D:\\Games\\savegames\\secondPlayerSave.dat");
        saveGame(thirdPlayer, "D:\\Games\\savegames\\thirdPlayerSave.dat");

        File saveFolder = new File("D:\\Games\\savegames");

        if (saveFolder.exists()){
            try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D:\\Games\\savegames\\saves.zip", true))) {
                for(
                File item :saveFolder.listFiles((x1, x2) -> !x2.equals("saves.zip"))){
                    try (FileInputStream fis = new FileInputStream(item)) {
                        ZipEntry entry = new ZipEntry(item.getName());
                        zout.putNextEntry(entry);
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        zout.write(buffer);
                        zout.closeEntry();
                        item.deleteOnExit();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
