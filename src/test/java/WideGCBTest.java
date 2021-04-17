import javafx.util.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.terentev.Encoder;
import ru.terentev.Key;
import ru.terentev.Keygen;
import ru.terentev.WideGCB;

import java.math.BigInteger;

import static ru.terentev.Keygen.calculateKey;

public class WideGCBTest {
    @Test
    void getInverse(){
        BigInteger p = Keygen.randomPrime();
        BigInteger q = Keygen.randomPrime();
        BigInteger invers = WideGCB.modInverse(p,q);
        Assert.assertEquals(invers.multiply(p).mod(q),BigInteger.ONE);
    }

    @Test
    void incript(){
        Pair<Key, Key> keyPair = calculateKey();
        BigInteger message = BigInteger.valueOf(12679098);
        byte[] criptogramm = Encoder.incriptDecript(message,keyPair.getKey());
        byte[] decripted = Encoder.incriptDecript(new BigInteger(criptogramm),keyPair.getValue());
        Assert.assertNotEquals(message,new BigInteger(criptogramm));
        Assert.assertEquals(message,new BigInteger(decripted));
    }

}
