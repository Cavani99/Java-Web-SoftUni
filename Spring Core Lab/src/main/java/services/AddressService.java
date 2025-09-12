package services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Value("${user.address}")
    private String address;

    public AddressService() {
    }

    public void getAddress() {
        System.out.printf("My address is %s.\n", address);
    }
}
