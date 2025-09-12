package bg.softuni.spring_core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.AddressService;
import services.GreetingService;

@Configuration
public class AppConfig {

    @Bean
    public GreetingService greetingService() {
        return new GreetingService(addressService());
    }

    @Bean
    public AddressService addressService() {
        return new AddressService();
    }

    @Bean
    public MagicBean magicBean() {
        return new MagicBean();
    }
}
