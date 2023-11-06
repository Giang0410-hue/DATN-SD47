//package com.example.bedatnsd47.tesst;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/products")
//public class LoginContrller {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String getAllTheProducts() {
//        return "admin";
//    }
//
//    @GetMapping("/user")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public String getProductById() {
//        return "user";
//    }
//
//
//
//
////    @PostMapping("/login")
////    public String loginThanhCong(@RequestParam("username") String username, @RequestParam("password") String password) {
////        // Here, you need to authenticate the user
////        // Use the AuthenticationManager to authenticate the user
////
////        // Assuming you have an AuthenticationManager autowired
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(username, password)
////        );
////
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        // Redirect users based on their roles after successful login
////        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
////            return "redirect:/products/admin";
////        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
////            return "redirect:/products/user";
////        } else {
////            // Handle other roles or scenarios if needed
////            return "redirect:/someDefaultPath";
////        }
////    }
//
//
//
//}
