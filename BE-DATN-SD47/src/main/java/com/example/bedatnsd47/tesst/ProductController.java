//package com.example.bedatnsd47.tesst;
//
//import com.example.bedatnsd47.entity.ChiTietSanPham;
//import com.example.bedatnsd47.entity.TaiKhoan;
//import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
//import com.example.bedatnsd47.service.TaiKhoanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    TaiKhoanService service;
//
//    @Autowired
//    ChiTietSanPhamSerivce chiTietSanPhamSerivce;
//
//
//
//
//
////    @GetMapping("/welcome")
////    public String welcome() {
////        return "Welcome this endpoint is not secure";
////    }
//
//    @PostMapping("/new")
//    public String addNewUser(@RequestBody TaiKhoan userInfo) {
//        return service.addUser(userInfo);
//    }
//
//
//    @GetMapping("/welcome")
//    public ResponseEntity<?> welcome() {
//        List<ChiTietSanPham> list=   chiTietSanPhamSerivce.getAll();
////        System.out.println(list.size());
//        return ResponseEntity.ok(list);
//    }
//
//}
