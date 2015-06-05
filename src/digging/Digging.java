package digging;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;
import javax.swing.filechooser.*;
import java.nio.file.StandardCopyOption.*;
import java.util.Locale;

public class Digging {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException, InterruptedException {
        AbsoloutePath.createAndShowGUI();
        GUI.createAndShowGUI();
        List<File> files = Arrays.asList(File.listRoots());
        File f1;
        String s1, filename;
        GUI.setText("What file would you like to view?");
        GUI.newLine();
        GUI.Hold();
        filename = GUI.text;
        GUI.newLine();
        for (File f : files) {
            s1 = FileSystemView.getFileSystemView().getSystemDisplayName(f);
            f1 = FileSystemView.getFileSystemView().getDefaultDirectory();
            File[] f2 = FileSystemView.getFileSystemView().getFiles(f, true);
            deeper(f2, filename);
        }
        System.out.println("Search Complete");
        GUI.setText("Search Complete");
        GUI.newLine();
    }

    public static void deeper(File[] f2, String filename) throws IOException {
        File[] listFiles;
        Character ch;
        {
            if (f2 != null) {
                for (File f21 : f2) {
                    listFiles = f21.listFiles();
                    if (listFiles != null) {
                        for (File listFile : listFiles) {
                            AbsoloutePath.setText(listFile.getAbsolutePath());
                            if (listFile.getName().contains(filename)) {
                                File source = new File(listFile.getAbsolutePath());
                                File dest = new File("H:\\profile\\desktop\\" + listFile.getName() + ".copy");
                                dupe(source, dest);
                                TextFile data = new TextFile(dest.getAbsolutePath());
                                GUI.setText("{===== " + listFile.getName() + " =====}");
                                GUI.newLine();
                                System.out.println("{===== " + listFile.getName() + " =====}");
                                for (int i = 0; i < data.size(); i++) {
                                    for (int y = 0; y < data.get(i).length(); y++) {
                                        ch = data.get(i).charAt(y);
                                        if (!ch.equals('�')) {
                                            GUI.setText(ch.toString());
                                            System.out.print(ch);
                                        } else {
                                        }
                                    }
                                    GUI.newLine();
                                    System.out.println();
                                }
                                dest.delete();
                            }
                        }
                    }
                    deeper(listFiles, filename);
                }
            }
        }
    }

    private static void dupe(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (Exception e) {
        } finally {
            if (inputChannel != null) {
                inputChannel.close();
            }
            if (outputChannel != null) {
                outputChannel.close();
            }
        }
    }
}
