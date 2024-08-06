package com.cocobuttgames.slaythearena.dungeons;

import java.util.ArrayList;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Arena extends AbstractDungeon {

    protected void initializeLevelSpecificChances() {}

    protected ArrayList<String> generateExclusions() {
        return new ArrayList<>();
    }

    protected void generateMonsters();
    
    protected void generateWeakEnemies(int paramInt) {}
    
    protected void generateStrongEnemies(int paramInt) {}
    
    protected void generateElites(int paramInt) {}
    
    protected void initializeBoss() {}
    
    protected void initializeEventList() {}
    
    protected void initializeEventImg() {}
    
    protected void initializeShrineList() {}

}
