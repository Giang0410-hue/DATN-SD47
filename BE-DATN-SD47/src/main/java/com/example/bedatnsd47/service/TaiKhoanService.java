package com.example.bedatnsd47.service;


import com.example.bedatnsd47.entity.TaiKhoan;

public interface TaiKhoanService {
    public String addUser(TaiKhoan userInfo);

    public void sendEmail(TaiKhoan taiKhoan, String path);

    public boolean verifyAccount(String verificationPassWord);

    public TaiKhoan saveUser(TaiKhoan user,String url);
}
