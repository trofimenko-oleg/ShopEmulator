package com.myshop.service;

import com.myshop.repository.DrinkRepository;
import com.myshop.util.PriceUtil;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{

    private final DrinkRepository drinkRepository;

    public PriceServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public double getMarkup() {
        PriceUtil.DAYTIME daytime = PriceUtil.getDaytime();

        if (daytime == PriceUtil.DAYTIME.EVENING){
            return 8;
        }
        else if (daytime == PriceUtil.DAYTIME.WEEKEND){
            return 15;
        }
        else
            return 10;
        }
}
