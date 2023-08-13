package com.poly.asm_nhom_6.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.poly.asm_nhom_6.service.AuthInterceptor;

@Configuration
public class InterConfig implements WebMvcConfigurer {

        @Autowired
        AuthInterceptor auth;

        @Autowired
        SecurityInterceptorAdmin admin;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(auth)
                                .addPathPatterns("/trangchu/**", "/user/**", "/user/like/**", "/staff/**")
                                .excludePathPatterns("/user/login");

        }

        private String clientId = "AbmnGfwUtVQRcdNsLDXqyuZJcaA3BYf9YKTYijiPs0uiyuzJ0sF-Zz2TwMK3bzgrEemgtv1CrzXq7Dxv";
        private String clientSecret = "EH0z4nTwQeyVtxRqIqszt0Noy4WW83PFzsTmgqsCMkwMwYt30swyc5P06T7hWRi5Us9nwt-kvKtFcaxH";
        private String mode = "sandbox";

        @Bean
        public Map<String, String> paypalSdkConfig() {
                Map<String, String> sdkConfig = new HashMap<>();
                sdkConfig.put("mode", mode);
                return sdkConfig;
        }

        @Bean
        public APIContext apiContext() throws PayPalRESTException {
                APIContext apiContext = new APIContext(clientId, clientSecret, mode);
                apiContext.setConfigurationMap(paypalSdkConfig());
                return apiContext;
        }
}
