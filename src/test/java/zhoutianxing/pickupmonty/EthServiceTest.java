package zhoutianxing.pickupmonty;

import org.junit.jupiter.api.Test;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * @Author zhoutianxing
 * @Date 2021/6/17 16:31
 * @Description
 */
class EthServiceTest {

    //@Test
    void getEthBanlance() {
        EthService ethService = EthService.getInstance(1);
        System.out.println(ethService.getEthBanlance("0x627306090abaB3A6e1400e9345bC60c78a8BEf57"));
    }

    //@Test
    void transfer() throws CipherException, IOException, ExecutionException, InterruptedException {
        EthService ethService = EthService.getInstance(1);
        ethService.transfer("0x627306090abaB3A6e1400e9345bC60c78a8BEf57", "0x975795A00554Bb24247da9dAFE9a2d13C3e476Eb", BigInteger.valueOf(10000000000000000L), "c87509a1c067bbde78beb793e6fa76530b6382a4c0241e5e4a9ec0a0f44dc0d3", BigInteger.valueOf(40000000000L), BigInteger.valueOf(21000L), null);
    }
}