package org.proleesh.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanComponent {

    @Bean
    public String name(){
        return new String("Proleesh");
    }

//    @Bean(name = "p2", autowireCandidate = false)
    @Bean(name = "p2", autowireCandidate = true)
    public String name2(){
        return new String("Proleesh2");
    }
}
