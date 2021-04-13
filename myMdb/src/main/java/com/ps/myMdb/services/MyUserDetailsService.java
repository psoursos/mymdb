//package com.ps.myMdb.services;
//
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//                   ////////////////////////////////////////////////////////////
//                   /   https://www.baeldung.com/spring_redirect_after_login   /
//                   ////////////////////////////////////////////////////////////
//
//@Service
//public class MyUserDetailsService implements UserDetailsService{
//	
//    private Map<String, User> roles = new HashMap<>();
//
//    @PostConstruct
//    public void init() {
//        roles.put("admin2", new User("admin", "{noop}admin1", getAuthority("ROLE_ADMIN")));
//        roles.put("user2", new User("user", "{noop}user1", getAuthority("ROLE_USER")));
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        return roles.get(username);
//    }
//
//    private List<GrantedAuthority> getAuthority(String role) {
//        return Collections.singletonList(new SimpleGrantedAuthority(role));
//    }
//}


