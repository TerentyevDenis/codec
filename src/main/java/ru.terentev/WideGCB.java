package ru.terentev;

import java.math.BigInteger;

public class WideGCB {

    bigStore gcbWide(bigStore big){
        if (big.a.compareTo(BigInteger.ZERO)==0){
            big.x=BigInteger.ZERO;
            big.y=BigInteger.ONE;
            return big;
        }
        BigInteger temp;
        bigStore d = gcbWide(new bigStore(big.b.mod(big.a),big.a,big.x,big.y));
        temp = d.x;
        d.x = d.y.subtract(big.b.divide(big.a).multiply(d.x));
        d.y=temp;
        return d;
    }

    public static BigInteger modInverse(BigInteger a, BigInteger b){
        bigStore triplBig= new WideGCB().gcbWide(new bigStore(a,b,BigInteger.ONE,BigInteger.ONE));
        BigInteger x = triplBig.x;
        if (triplBig.b.compareTo(BigInteger.ONE)!=0){
            throw new RuntimeException("No result");
        }

        return x;
    }
}

class bigStore
{
    bigStore(BigInteger a, BigInteger b, BigInteger x, BigInteger y)
    {
        this.a = a;
        this.b = b;
        this.x=x;
        this.y=y;
    }

    BigInteger a;
    BigInteger b;
    BigInteger x;
    BigInteger y;
}