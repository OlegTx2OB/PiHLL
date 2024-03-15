package com.example.papadoner.dao;

import com.example.papadoner.model.Doner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DonerDAO {
    private final List<Doner> donerList;

    {
        donerList = new ArrayList<>();
        donerList.add(new Doner("starter", 10.9, 550));
        donerList.add(new Doner("bomber", 9.9, 400));
        donerList.add(new Doner("cream_sour", 11.9, 550));
        donerList.add(new Doner("kraken", 8.9, 400));
        donerList.add(new Doner("chicken", 10.9, 650));
    }

    public Doner findByName(String name) {
        for (Doner doner : donerList) {
            if (doner.getName().equals(name)) {
                return doner;
            }
        }
        return null;
    }
}