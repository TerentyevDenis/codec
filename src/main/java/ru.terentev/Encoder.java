package ru.terentev;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Encoder {
    public static byte[] incriptDecript(BigInteger input, Key key){
        return input.modPow(key.getKey(),key.getN()).toByteArray();
    }

    public static void encryptDecryptFile(String file, Key key){
        Path path = Paths.get(file);
        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
            data = incriptDecript(new BigInteger(data),key);
            Files.write(path,data);
            System.out.println("File processed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void signFile(String file, Key key){
        Path pathSign = Paths.get(file+".sign");
        Path path = Paths.get(file);
        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
            data = incriptDecript(BigInteger.valueOf(Arrays.hashCode(data)),key);
            Files.write(pathSign,data);
            System.out.println("File signed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkFile(String file, Key key){
        Path pathSign = Paths.get(file+".sign");
        Path path = Paths.get(file);
        byte[] data = null;
        try {
            BigInteger fileHash = BigInteger.valueOf(Arrays.hashCode(Files.readAllBytes(path)));
            data = incriptDecript(new BigInteger(Files.readAllBytes(pathSign)),key);
            BigInteger signHash = new BigInteger(data);
            if (signHash.compareTo(fileHash)==0){
                System.out.println("File is original");
            } else {
                System.out.println("ERROR: Sign is not origin!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
