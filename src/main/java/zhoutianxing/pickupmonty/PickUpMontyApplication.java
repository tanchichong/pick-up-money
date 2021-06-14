package zhoutianxing.pickupmonty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PickUpMontyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PickUpMontyApplication.class, args);

        PicUpMoneyServie.picUpEth();
    }

}
