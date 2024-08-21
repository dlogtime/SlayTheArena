package com.cocobuttgames.slaythearena.dungeons;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.TrueVictoryRoom;
import com.megacrit.cardcrawl.scenes.TheEndingScene;


public class Arena extends AbstractDungeon {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("TheEnding");
    
    public static final String[] TEXT = uiStrings.TEXT;
    
    public static final String NAME = TEXT[0];
    
    public static final String ID = "TheEnding";
    
    public Arena(AbstractPlayer p, ArrayList<String> theList) {
        super(NAME, "TheEnding", p, theList);
        if (scene != null)
            scene.dispose();
        scene = new TheEndingScene();
        fadeColor = Color.valueOf("140a1eff");
        sourceFadeColor = Color.valueOf("140a1eff");
        initializeLevelSpecificChances();
        mapRng = new Random(Long.valueOf(Settings.seed.longValue() + (AbstractDungeon.actNum * 300)));
        generateArenaMap();
        CardCrawlGame.music.changeBGM(id);
    }
    
    private void generateArenaMap() {
        long startTime = System.currentTimeMillis();
        map = new ArrayList<>();
        ArrayList<MapRoomNode> arenaNodeRow = new ArrayList<>();
        MapRoomNode arenaNode = new MapRoomNode(3, 0);
        arenaNode.room = new MonsterRoomBoss();
        MapRoomNode victoryNode = new MapRoomNode(3, 1);
        victoryNode.room = new TrueVictoryRoom();
        arenaNodeRow.add(new MapRoomNode(0, 0));
        arenaNodeRow.add(new MapRoomNode(1, 0));
        arenaNodeRow.add(new MapRoomNode(2, 0));
        arenaNodeRow.add(arenaNode);
        arenaNodeRow.add(new MapRoomNode(4, 0));
        arenaNodeRow.add(new MapRoomNode(5, 0));
        arenaNodeRow.add(new MapRoomNode(6, 0));
        ArrayList<MapRoomNode> victoryNodeRow = new ArrayList<>();
        victoryNodeRow.add(new MapRoomNode(0, 1));
        victoryNodeRow.add(new MapRoomNode(1, 1));
        victoryNodeRow.add(new MapRoomNode(2, 1));
        victoryNodeRow.add(victoryNode);
        victoryNodeRow.add(new MapRoomNode(4, 1));
        victoryNodeRow.add(new MapRoomNode(5, 1));
        victoryNodeRow.add(new MapRoomNode(6, 1));
        map.add(arenaNodeRow);
        map.add(victoryNodeRow);
        logger.info("Generated the following dungeon map:");
        logger.info(MapGenerator.toString(map, Boolean.valueOf(true)));
        logger.info("Game Seed: " + Settings.seed);
        logger.info("Map generation time: " + (System.currentTimeMillis() - startTime) + "ms");
        firstRoomChosen = false;
        fadeIn();
    }
    
    protected void initializeLevelSpecificChances() {
        shopRoomChance = 0.05F;
        restRoomChance = 0.12F;
        treasureRoomChance = 0.0F;
        eventRoomChance = 0.22F;
        eliteRoomChance = 0.08F;
        smallChestChance = 0;
        mediumChestChance = 100;
        largeChestChance = 0;
        commonRelicChance = 0;
        uncommonRelicChance = 100;
        rareRelicChance = 0;
        colorlessRareChance = 0.3F;
        if (AbstractDungeon.ascensionLevel >= 12) {
            cardUpgradedChance = 0.25F;
        } else {
            cardUpgradedChance = 0.5F;
        } 
    }
    
    protected void generateMonsters() {
        eliteMonsterList = new ArrayList<>();
        eliteMonsterList.add("Shield and Spear");
        eliteMonsterList.add("Shield and Spear");
        eliteMonsterList.add("Shield and Spear");
    }
    
    protected void generateWeakEnemies(int count) {}
    
    protected void generateStrongEnemies(int count) {}
    
    protected void generateElites(int count) {}
    
    protected ArrayList<String> generateExclusions() {
        return new ArrayList<>();
    }
    
    protected void initializeBoss() {
        bossList.add("The Heart");
    }
    
    protected void initializeEventList() {}
    
    protected void initializeEventImg() {}
    
    protected void initializeShrineList() {}

}
