package org.shop;

import java.util.Map;

import org.shop.api.ProposalService;
import org.shop.api.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
    private static ApplicationContext context;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {

        context = new ClassPathXmlApplicationContext(
                "application-context.xml");

        ProposalInitializer pInitializer = (ProposalInitializer)context.getBean("proposalInitializer");
        System.out.println(pInitializer.getClass());

        ProposalService repository = context.getBean(ProposalService.class);
        System.out.println(repository.getSellerService().getClass());

        Map sellers = context.getBean("sellers", Map.class);
        System.out.println(sellers);

        UserService userService = (UserService)context.getBean("user_service");
        System.out.print(userService.getUsers());
    }
}
