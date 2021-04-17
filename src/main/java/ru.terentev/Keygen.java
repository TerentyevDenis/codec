package ru.terentev;

import javafx.util.Pair;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;

public class Keygen {
    public static BigInteger randomPrime() {
        BigInteger number = new BigInteger(1024, new Random());
        while (!number.isProbablePrime(16)) {
            number = number.nextProbablePrime();
        }
        return number;
    }

    public static Pair<Key, Key> calculateKey() {
        BigInteger p = randomPrime();
        BigInteger q = randomPrime();
        BigInteger n = p.multiply(q);
        BigInteger f = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(65537);
        BigInteger d = WideGCB.modInverse(e, f);
        Key privateKey = new Key(d, n);
        Key publicKey = new Key(e, n);
        return new Pair<Key, Key>(privateKey, publicKey);
    }

    public static void generateKey() {
        Pair<Key, Key> keyPair = calculateKey();
        saveKey(keyPair.getKey(), "private.key");
        saveKey(keyPair.getValue(), "public.key");
        System.out.println("Keys generated");
    }

    private static void saveKey(Key key, String file) {
        try (ObjectOutputStream fos = new ObjectOutputStream(new FileOutputStream(file))) {
            fos.writeObject(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Key getKey(String file) {
        Key key = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            key = (Key) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }
}
