package ru.terentev;

import java.io.Serializable;
import java.math.BigInteger;

public class Key implements Serializable {
    private BigInteger key;
    private BigInteger n;

    public Key(BigInteger key, BigInteger n) {
        this.key = key;
        this.n = n;
    }

    public BigInteger getKey() {
        return key;
    }

    public BigInteger getN() {
        return n;
    }
}
