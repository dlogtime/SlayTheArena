package com.cocobuttgames.slaythearena.patches.core;

import java.util.ArrayList;

import com.cocobuttgames.slaythearena.dungeons.Arena;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class CardCrawlGamePatch {
    
    @SpirePatch(
        clz = CardCrawlGame.class,
        method = "getDungeon",
        paramtypez = {String.class, AbstractPlayer.class}
    )
    public static class GetArenaDungeonPatch {
        @SpirePrefixPatch
        public static SpireReturn<Arena> getArenaDungeon(CardCrawlGame __instance, String key, AbstractPlayer p) {
            ArrayList<String> emptyList = new ArrayList<>();
            if (key == "Arena") {
                return SpireReturn.Return(new Arena(p, emptyList));
            }
            return SpireReturn.Continue();
        } 
    }

}
