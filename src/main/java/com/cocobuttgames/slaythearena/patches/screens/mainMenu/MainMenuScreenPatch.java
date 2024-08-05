package com.cocobuttgames.slaythearena.patches.screens.mainMenu;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;


public class MainMenuScreenPatch {

    @SpirePatch(clz = MainMenuScreen.class, method = "setMainMenuButtons")
    public static class ArenaButtonPatch {
        @SpirePostfixPatch
        public static void setArenaButton(MainMenuScreen __instance) {
            int numberOfOtherButtons = __instance.buttons.size();
            __instance.buttons.add(new MenuButton(MenuButtonPatch.ArenaClickResultPatch.ARENA, numberOfOtherButtons++));
        }
    }

}
