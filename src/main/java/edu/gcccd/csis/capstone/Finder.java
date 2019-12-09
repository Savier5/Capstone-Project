package edu.gcccd.csis.capstone;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Finder {

    public static void main(final String[] args) throws IOException {
        final Path path = Paths.get("D:");
        final File ex = findExtremeFile(path);
        double format = ex.length() / 1e+9;
        System.out.println("Starting at: " + path.toAbsolutePath().toString() + ", the largest file was found here: \n" + ex.getAbsolutePath() + ",\nwith the size of " + format + " GB.");
    }
    static File extreme(final File f1, final File f2) {
        if (f2 == null) return f1;
        if (f1 == null) return f2;
        try{
            Path file1 = Paths.get(String.valueOf(f1));
            BasicFileAttributes attr1 = Files.readAttributes(file1, BasicFileAttributes.class);
            Path file2 = Paths.get(String.valueOf(f2));
            BasicFileAttributes attr2 = Files.readAttributes(file2, BasicFileAttributes.class);
            return attr1.size() > attr2.size() ? f1 : f2;
        }catch (IOException e){
            return null;
        }
    }

    static File findExtremeFile(final Path p) {
        File x = null;
        final File[] fa = p.toFile().listFiles();
        if (fa != null && 0<fa.length){
            for (final File f : fa){
                if (f.isFile()){
                    x = extreme(x,f);
                }else if (f.isDirectory()){
                    x = extreme(x, findExtremeFile(f.toPath()));

                }
            }
        }

        return x;
    }
}