package zhoutianxing.pickupmonty;

import java.math.BigInteger;

/**
 * @author zhoutianxing
 */
public class PicUpMoneyServie {
    private static final String myPocketAddress = "0x975795A00554Bb24247da9dAFE9a2d13C3e476Eb";

    private static final String[] freeEthAddress = new String[] {
        "0x627306090abaB3A6e1400e9345bC60c78a8BEf57",
        "0xf17f52151EbEF6C7334FAD080c5704D77216b732"
    };
    private static final String[] freeEthPK = new String[] {
            "c87509a1c067bbde78beb793e6fa76530b6382a4c0241e5e4a9ec0a0f44dc0d3",
            "ae6ae8e5ccbfb04590405997ee2d52d2b330726137b875053c36d94e974d162f"
    };

    private static BigInteger gasPrice = BigInteger.valueOf(40000000000L);
    private static BigInteger gasLimit = BigInteger.valueOf(3000L);
    private static BigInteger gas = gasPrice.multiply(gasLimit);

    public static void picUpEth() {
        EthService ethService = new EthService();
        while (true) {
            for (int i = 0; i < freeEthAddress.length; i++) {
                String fromAddress = freeEthAddress[i];
                BigInteger balance = ethService.getEthBanlance(fromAddress);
                if (balance.compareTo(gas) > 0) {
                    System.out.println("开始捡钱：" + balance);
                    try {
                        ethService.transfer(fromAddress, myPocketAddress, balance, freeEthPK[i], gasPrice, gasLimit, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("钱包空空");
                }
            }
        }
    }
}
