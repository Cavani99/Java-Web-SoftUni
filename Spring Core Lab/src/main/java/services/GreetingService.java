package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    @Value("${user.username}")
    private String userName;
    private final AddressService addressService;

    @Autowired
    public GreetingService(AddressService addressService) {
        this.addressService = addressService;
    }

    public void getGreeting() {
        System.out.printf("Hello %s, welcome to the Spring Core Lab!", userName);
        addressService.getAddress();
    }

}
