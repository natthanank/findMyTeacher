package com.natthanan.findmyteacher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuArea;
import com.linecorp.bot.model.richmenu.RichMenuBounds;
import com.linecorp.bot.model.richmenu.RichMenuSize;
import com.linecorp.bot.model.richmenu.RichMenu.RichMenuBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.*;

public class MyRichMenu {

    private static RichMenu richMenu;
    private RichMenuSize richMenuSize;
    

    public static RichMenu getRichMenu() {
        if (richMenu == null) {
            richMenu = RichMenu.builder()
            .name("SecondRichMenu")
            .areas(Arrays.asList(createRichMenuArea("left"),
                    createRichMenuArea("right")))
            .chatBarText("SecondRichMenu")
            .selected(true)
            .size(RichMenuSize.FULL)
            .build();
        }
        return richMenu;
    }

    private static RichMenuArea createRichMenuArea(String corner) {
          
        if (corner.equals("left")) {
            return new RichMenuArea(new RichMenuBounds(0, 0, 1250, 1686), new MessageAction("0", "0"));
        } else {
            return new RichMenuArea(new RichMenuBounds(1250, 0, 1250, 1686), new MessageAction("1", "0"));
        }
    }
}