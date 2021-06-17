package zhoutianxing.pickupmonty;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * @author zhoutianxing
 */
public class EthService {
    private static EthService ethService1 = new EthService("https://mainnet.infura.io/v3/f0c42246a97b4f0086dfc9ec92dddf17");
    private static EthService ethService2 = new EthService("https://mainnet.infura.io/v3/566a88e392c74fa995afb271acda7a26");
    private static EthService ethService3 = new EthService("https://mainnet.infura.io/v3/b48b789fcf1f43c6b53785e86648fa31");
    private static EthService ethService4 = new EthService("https://mainnet.infura.io/v3/4f3ce2c1a14349c182c826ca3833d542");
    private static EthService ethService5 = new EthService("https://mainnet.infura.io/v3/c0c2cf08e717493d994fa58eec05bf92");
    private static EthService ethService6 = new EthService("https://mainnet.infura.io/v3/c0a18b88537b4eae9f81623182ef948a");
    private static EthService ethService7 = new EthService("https://mainnet.infura.io/v3/0e5df4a46aad4c41af7866d508b86b10");
    private static EthService ethService8 = new EthService("https://mainnet.infura.io/v3/78bcb3e6442f4639b8265a9e31919da5");
    private static EthService ethService9 = new EthService("https://mainnet.infura.io/v3/2c2486bf71974903a5200d7f9af4c944");
    private static EthService ethService10 = new EthService("https://mainnet.infura.io/v3/024c8de522c34e21939f31a1c164e136");
    private static EthService ethService11 = new EthService("https://mainnet.infura.io/v3/f6b8f6222b914fd6b8f7463017fbd415");
    private static EthService ethService12 = new EthService("https://mainnet.infura.io/v3/011f9db12a1d480fbf5fc93117380c01");

    private Web3j web3j;
    private EthService(String url) {
        web3j = Web3j.build(new HttpService(url));
    }

    public static EthService getInstance(int hour) {
        EthService ethService = null;
        switch (hour) {
            case 1: ethService = ethService1;break;
            case 2: ethService = ethService2;break;
            case 3: ethService = ethService3;break;
            case 4: ethService = ethService4;break;
            case 5: ethService = ethService5;break;
            case 6: ethService = ethService6;break;
            case 7: ethService = ethService7;break;
            case 8: ethService = ethService8;break;
            case 9: ethService = ethService9;break;
            case 10: ethService = ethService10;break;
            case 11: ethService = ethService11;break;
            case 12: ethService = ethService12;break;
            case 13: ethService = ethService1;break;
            case 14: ethService = ethService2;break;
            case 15: ethService = ethService3;break;
            case 16: ethService = ethService4;break;
            case 17: ethService = ethService5;break;
            case 18: ethService = ethService6;break;
            case 19: ethService = ethService7;break;
            case 20: ethService = ethService8;break;
            case 21: ethService = ethService9;break;
            case 22: ethService = ethService10;break;
            case 23: ethService = ethService11;break;
            case 0: ethService = ethService12;break;
            default:break;
        }
        return ethService;
    }

    /**
     * 查询以太币账户余额
     * @throws IOException
     */
    public BigInteger getEthBanlance(String userAddress) {
        //获取指定钱包的以太币余额
        try {
            return web3j.ethGetBalance(userAddress, DefaultBlockParameterName.LATEST).send().getBalance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(0L);
    }

    /**
     * 发起一笔交易（自定义参数）
     *
     * @param from       发起人钱包地址
     * @param to         转入的钱包地址
     * @param value      转账金额，单位是wei
     * @param privateKey 钱包私钥
     * @param gasPrice   转账费用
     * @param gasLimit
     * @param data       备注的信息
     * @throws IOException
     * @throws CipherException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public EthSendTransaction transfer(String from,
                                       String to,
                                       BigInteger value,
                                       String privateKey,
                                       BigInteger gasPrice,
                                       BigInteger gasLimit,
                                       String data) throws IOException, CipherException, ExecutionException, InterruptedException {


        //加载转账所需的凭证，用私钥
        Credentials credentials = Credentials.create(privateKey);
        //获取nonce，交易笔数
        BigInteger nonce = getNonce(from);
        //创建RawTransaction交易对象
        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value);
        //签名Transaction，这里要对交易做签名
        byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signMessage);
        //发送交易
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        return ethSendTransaction;
    }

    /**
     * 获取nonce，交易笔数
     *
     * @param from
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private BigInteger getNonce(String from) throws ExecutionException, InterruptedException {
        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = transactionCount.getTransactionCount();
        return nonce;
    }
}
