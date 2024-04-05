package com.druids.api;

import com.druids.entity.ChainLightning;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class ChainList {
    public ChainLightning chainLightning;
    public List<Entity> list ;
    public List<Entity> listhit = new ArrayList<>(List.of()) ;

    public ChainList( List<Entity> startingList){

        this.list = (startingList);
    }
}
