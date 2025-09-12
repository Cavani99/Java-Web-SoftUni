package bg.softuni.spring_core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import services.AddressService;
import services.GreetingService;

@Component
public class Main implements CommandLineRunner {

    private GreetingService greetingService;
    private final ApplicationContext context;

    public Main(GreetingService greetingService, ApplicationContext context) {
        this.greetingService = greetingService;
        this.context = context;
    }


    @Override
    public void run(String... args) throws Exception {
        greetingService.getGreeting();
        System.out.println(context.getBean(MagicBean.class));
    }
}
