package com.cocobuttgames.slaythearena.patches.screens.mainMenu;

import com.cocobuttgames.slaythearena.patches.screens.mainMenu.MenuButtonPatch.ArenaClickResultPatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.CharacterManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.helpers.SeedHelper;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.EmptyRoom;
import com.megacrit.cardcrawl.screens.DungeonTransitionScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import com.megacrit.cardcrawl.screens.mainMenu.MenuPanelScreen;


public class MenuButtonPatch {

    public static class ArenaClickResultPatch {
        @SpireEnum
        public static MenuButton.ClickResult ARENA;
    }

    @SpirePatch(clz = MenuButton.class, method = "setLabel")
    public static class ArenaLabelPatch {
        @SpirePostfixPatch
        public static void setArenaLabel(MenuButton __instance, @ByRef String[] ___label) {
            if (__instance.result == ArenaClickResultPatch.ARENA) {
                ___label[0] = "Arena";
            }
        }
    }

    @SpirePatch(clz = MenuButton.class, method = "buttonEffect")
    public static class ArenaButtonEffectPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> setArenaButtonEffect(MenuButton __instance) {
            if (__instance.result == ArenaClickResultPatch.ARENA) {
                startArena();
            }
            return SpireReturn.Continue();
        }
    }

    public static void startArena() {
        CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.NONE;
        CardCrawlGame.mainMenuScreen.hideMenuButtons();
        CardCrawlGame.mainMenuScreen.darken();
        CardCrawlGame.chosenCharacter = AbstractPlayer.PlayerClass.IRONCLAD;
        CardCrawlGame.mainMenuScreen.isFadingOut = true;
        CardCrawlGame.mainMenuScreen.fadeOutMusic();
        CardCrawlGame.nextDungeon = "TheEnding";     // TODO: Make this load an Arena Dungeon instead
        CardCrawlGame.dungeonTransitionScreen = new DungeonTransitionScreen("TheEnding");
        CardCrawlGame.mode = CardCrawlGame.GameMode.GAMEPLAY;
        Settings.isTrial = true;
        Settings.isDailyRun = false;
        Settings.isEndless = false;
        AbstractDungeon.isAscensionMode = false;
        AbstractDungeon.ascensionLevel = 0;
        AbstractDungeon.player = CardCrawlGame.characterManager.recreateCharacter(AbstractPlayer.PlayerClass.IRONCLAD);
        AbstractDungeon.currMapNode = new MapRoomNode(0, -1);
        AbstractDungeon.currMapNode.room = new EmptyRoom();
        long sourceTime = System.nanoTime();
        Random rng = new Random(Long.valueOf(sourceTime));
        Settings.seed = Long.valueOf(SeedHelper.generateUnoffensiveSeed(rng));
        AbstractDungeon.generateSeeds();
    }

}
