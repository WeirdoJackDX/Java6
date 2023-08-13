package com.poly.asm_nhom_6.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    AccountDAO accountDao;

    @Autowired
    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountDao.findById(username).get();
            String password = account.getPassword();
            String[] roles = account.getAuthorities().stream()
                    .map(au -> au.getRole().getId())
                    .collect(Collectors.toList()).toArray(new String[0]);
            return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(username + "không tồn tại");
        }
    }

    // public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
    // String email = oauth2.getPrincipal().getAttribute("email");
    // String password = Long.toHexString(System.currentTimeMillis());
    // UserDetails user = User.withUsername(email)
    // .password(pe.encode(password)).roles("GUEST").build();
    // Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
    // user.getAuthorities());
    // SecurityContextHolder.getContext().setAuthentication(auth);
    // }

}
