package com.outrightwings.truly_custom_horse_tack.item;

import net.minecraft.world.item.Item;

public class TackPatternItem extends Item {
    private final String patternName;
    public TackPatternItem(String patternName, Item.Properties properties){
        super(properties);
        this.patternName = patternName;
    }
    public String getPatternName(){return patternName;}
}
