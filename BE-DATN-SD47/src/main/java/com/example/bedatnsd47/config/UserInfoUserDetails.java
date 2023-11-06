//package com.example.bedatnsd47.config;
//
//import com.example.bedatnsd47.entity.TaiKhoan;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//import java.util.Arrays;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class UserInfoUserDetails implements UserDetails {
//
//
//
//    private String ten_tai_khoan;
//    private String matKhau;
//    private List<GrantedAuthority> authorities;
//
//    public UserInfoUserDetails(TaiKhoan userInfo) {
//        ten_tai_khoan = userInfo.getTenTaiKhoan();
//        matKhau = userInfo.getMatKhau();
//        authorities = Arrays.stream(userInfo.getVaiTro().getTen().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return matKhau;
//    }
//
//    @Override
//    public String getUsername() {
//        return ten_tai_khoan;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
