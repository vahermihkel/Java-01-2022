package ee.mihkel.webshop.controller;

import ee.mihkel.webshop.model.EveryPayData;
import ee.mihkel.webshop.model.EveryPayLink;
import ee.mihkel.webshop.model.EveryPayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class PaymentController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("payment")
    public EveryPayLink startPayment() {
//        {
//            "api_username": "92ddcfab96e34a5f",
//                "account_name": "EUR3D1",
//                "amount": 156.99,
//                "order_reference": "912911",
//                "nonce": "a9b7f7e7312asdasdb9902",
//                "timestamp": "2022-01-10T19:20:15+02:00",
//                "email": "user@example.com",
//                "customer_url": "https://www.delfi.ee"
//        }
        String everyPayUrl = "https://igw-demo.every-pay.com/api/v4/payments/oneoff";
        EveryPayData everyPayData = new EveryPayData();
        everyPayData.setApi_username("92ddcfab96e34a5f");
        everyPayData.setAccount_name("EUR3D1");
        everyPayData.setAmount(1555.99);
        everyPayData.setOrder_reference(9129121L);
        everyPayData.setTimestamp(new Date());
        everyPayData.setNonce("92ddcfab96e34a5f" + new Date());
        everyPayData.setCustomer_url("https://www.delfi.ee");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic OTJkZGNmYWI5NmUzNGE1Zjo4Y2QxOWU5OWU5YzJjMjA4ZWU1NjNhYmY3ZDBlNGRhZA==");

        HttpEntity<EveryPayData> httpEntity = new HttpEntity<>(everyPayData, headers);

        ResponseEntity<EveryPayResponse> res = restTemplate.exchange(everyPayUrl, HttpMethod.POST,httpEntity, EveryPayResponse.class);

        EveryPayLink link = new EveryPayLink();

        link.setLink(res.getBody().getPayment_link());

        return link;
    }
}
