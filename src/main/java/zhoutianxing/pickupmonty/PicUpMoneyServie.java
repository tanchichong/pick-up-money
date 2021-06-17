package zhoutianxing.pickupmonty;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoutianxing
 */
public class PicUpMoneyServie {
    private static final String myPocketAddress = "0x975795A00554Bb24247da9dAFE9a2d13C3e476Eb";

    private static Map<String, String> freePockets = new HashMap<>();
    static {
        freePockets.put("0x627306090abaB3A6e1400e9345bC60c78a8BEf57", "c87509a1c067bbde78beb793e6fa76530b6382a4c0241e5e4a9ec0a0f44dc0d3");
        freePockets.put("0xf17f52151EbEF6C7334FAD080c5704D77216b732", "ae6ae8e5ccbfb04590405997ee2d52d2b330726137b875053c36d94e974d162f");
        freePockets.put("0xc5fdf4076b8f3a5357c5e395ab970b5b54098fef", "0dbbe8e4ae425a6d2687f1a7e3ba17bc98c673636790f1b8ad91193c05875ef1");
        freePockets.put("0x821aea9a577a9b44299b9c15c88cf3087f3b5544", "c88b703fb08cbea894b6aeff5a544fb92e78a18e19814cd85da83b71f772aa6c");
        freePockets.put("0x0d1d4e623d10f9fba5db95830f7d3839406c6af2", "388c684f0ba1ef5017716adb5d21a053ea8e90277d0868337519f97bede61418");
        freePockets.put("0x2932b7a2355d6fecc4b5c0b6bd44cc31df247a2e", "659cbb0e2411a44db63778987b1e22153c086a95eb6b18bdf89de078917abc63");
        freePockets.put("0x2191ef87e392377ec08e7c08eb105ef5448eced5", "82d052c865f5763aad42add438569276c00d3d88a2d062d36b2bae914d58b8c8");
        freePockets.put("0x0f4f2ac550a1b4e2280d04c21cea7ebd822934b5", "aa3680d5d48a8283413f7a108367c7299ca73f553735860a87b08f39395618b7");
        freePockets.put("0x6330a553fc93768f612722bb8c2ec78ac90b3bbc", "0f62d96d6675f32685bbdb8ac13cda7c23436f63efbb9d07700d8669ff12b7c4");
        freePockets.put("0x5aeda56215b167893e80b4fe645ba6d5bab767de", "8d5366123cb560bb606379f90a0bfd4769eecc0557f1b362dcae9012b548b1e5");
    }

    private static BigInteger gasPrice = BigInteger.valueOf(40000000000L);
    private static BigInteger gasLimit = BigInteger.valueOf(21000L);
    private static BigInteger gas = gasPrice.multiply(gasLimit);

    public static void picUpEth() {
        while (true) {
            LocalDateTime now = LocalDateTime.now();
            EthService ethService = EthService.getInstance(now.getHour());
            freePockets.forEach((fromAddress, priKey) -> {
                BigInteger balance = ethService.getEthBanlance(fromAddress);
                if (balance.compareTo(gas) > 0) {
                    BigInteger pickUpMoney = balance.subtract(gas);
                    System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 开始捡钱，应该能从:" + fromAddress + " 捡到：" + pickUpMoney);
                    try {
                        ethService.transfer(fromAddress, myPocketAddress, pickUpMoney, priKey, gasPrice, gasLimit, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (now.getSecond() == 0) {
                        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 钱包空空");
                    }
                }
            });
        }
    }
}
