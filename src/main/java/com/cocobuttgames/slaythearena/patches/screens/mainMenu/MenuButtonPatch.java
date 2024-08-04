package com.cocobuttgames.slaythearena.patches.screens.mainMenu;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import com.megacrit.cardcrawl.screens.mainMenu.MenuPanelScreen;


public class MenuButtonPatch {

    public static class ArenaClickResultPatch {
        @SpireEnum
        public static MenuButton.ClickResult ARENA;
    }

    @SpirePatch(clz = MenuButton.class, method = "setLabel")
    public static class ArenaLabelPatch {
        public static void Postfix(MenuButton __instance, @ByRef String[] ___label) {
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
                CardCrawlGame.mainMenuScreen.panelScreen.open(MenuPanelScreen.PanelScreen.COMPENDIUM);
            }
            return SpireReturn.Continue();
        }
    }

}
