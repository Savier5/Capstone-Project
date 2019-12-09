package edu.gcccd.csis.capstone;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FinderTest {

    public static void main(String[] args) throws IOException {
        String path = "D:";
        final File dir = new File(path);
        final File result = new File(FinderTest.find(dir, null));
        System.out.println(result);
    }

    private static String find(final File dir, String result) throws IOException {
        final List fileList = new ArrayList();
        fileList.add(dir);
        String path;
        String store;
        String pathTest;
        long biggestBytes = 0;
        long biggestBytes2 = 0;

        while(!fileList.isEmpty()){
            final File f = (File) fileList.remove(0);
            store = f.toString();
            path = store;
            Path file2 = Paths.get(path);
            BasicFileAttributes attr2 = Files.readAttributes(file2, BasicFileAttributes.class);
            if (f.isFile() && attr2.size() > biggestBytes){
                pathTest = f.getPath();
                biggestBytes = attr2.size();
                if (biggestBytes > biggestBytes2) {
                    biggestBytes2 = biggestBytes;
                }
                double format = biggestBytes2 / 1e+9;
                result = "Starting at: " + dir + ", the largest file was found here: \n" + pathTest + ",\nwith the size of " + format + " GB.";
            }else if (f.isDirectory()){
                final File[] fa = f.listFiles();
                if (fa != null){
                    fileList.addAll(Arrays.asList(fa));
                }
            }
        }
        return result ;
    }
}
