package com.cocobuttgames.slaythearena;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;


@SpirePatch(
    clz=MainMenuScreen.class,
    method="setMainMenuButtons"
)
public class SetArenaButtonPatch {

    @SpirePostfixPatch
    public static void setArenaButton(MainMenuScreen __instance) {
        __instance.buttons.add(new MenuButton(MenuButton.ClickResult.INFO, 8));
    }

}
