package com.service.core;

public class UserServiceImpl implements IUserService{
    @Override
    public String check(String name) {
        System.out.println("Hello " + name);
        return "Hello " + name;
    }
}
