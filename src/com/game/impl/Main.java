package com.game.impl;

import com.game.api.service.Service;
import com.game.impl.service.ServiceImpl;

public class Main {

    public static void main(String[] args) {
        Service service = new ServiceImpl();
        for (int i = 0; i < 2000; i++) {
            System.out.println("-----------step----------- " + i);
            service.play();
        }
    }
}
