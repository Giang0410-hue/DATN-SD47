//package com.example.bedatnsd47.tesst;
//
//import com.example.bedatnsd47.entity.TaiKhoan;
//import com.example.bedatnsd47.service.TaiKhoanService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class FormLoginContrller {
//    @Autowired
//    TaiKhoanService service;
//
//    @GetMapping("/login")
//    public  String formLogin(){
////        principal.getName();
//        return  "dang-nhap";
//    }
//
//    @GetMapping("/register")
//    public  String dangKis(){
////        principal.getName();
//        return  "dang-ky";
//    }
//    @PostMapping("/saveTaiKhoan")
//    public String addNewUser(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model, HttpServletRequest request) {
//// System.out.println(user);
//        String url = request.getRequestURL().toString();
//
//        System.out.println(url);
//        // http://localhost:8080/saveUser
//        url = url.replace(request.getServletPath(), "");
//
//        System.out.println(url);
//        // //http://localhost:8080/verify?code=3453sdfsdcsadcscd
//        TaiKhoan userInfo = new TaiKhoan();
//        userInfo.setTenTaiKhoan(username);
//        userInfo.setEmail(email);
//        userInfo.setMatKhau(password);
//        TaiKhoan u = service.saveUser(userInfo, url);
//
//        if (u != null) { // System.out.println("save sucess");
//            session.setAttribute("msg", "Register successfully");
//
//        } else { // System.out.println("error in server");
//            session.setAttribute("msg", "Something wrong server");
//        }
//
//        return "redirect:/register";
//
////        return service.addUser(userInfo);
//    }
//
//    @GetMapping("/verify")
//    public String verifyAccount(@Param("code") String code, Model m) {
//        boolean f = service.verifyAccount(code);
//
//        if (f) {
//            m.addAttribute("msg", "Sucessfully your account is verified");
//        } else {
//            m.addAttribute("msg", "may be your vefication code is incorrect or already veified ");
//        }
//
//        return "message";
//    }
//}
