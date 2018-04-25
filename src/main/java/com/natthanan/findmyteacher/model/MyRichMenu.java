package com.natthanan.findmyteacher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.URIAction;
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
            .areas(Arrays.asList(createRichMenuArea("6"),
                    createRichMenuArea("7"),
                    createRichMenuArea("8"),
                    createRichMenuArea("9"),
                    createRichMenuArea("10"),
                    createRichMenuArea("<-")))
            .chatBarText("SecondRichMenu")
            .selected(true)
            .size(RichMenuSize.FULL)
            .build();
        }
        return richMenu;
    }

    private static RichMenuArea createRichMenuArea(String corner) {
          
        switch (corner) {
            case "6":
                return new RichMenuArea(new RichMenuBounds(0, 0, 833, 843), new URIAction("Facebook", "https://www.facebook.com"));
            case "7":
                return new RichMenuArea(new RichMenuBounds(833, 0, 833, 843), new URIAction("OreMange", "http://www.oremanga.com/"));
            case "8":
                return new RichMenuArea(new RichMenuBounds(1666, 0, 834, 843), new URIAction("Line", "https://scdn.line-apps.com/n/line_official_v2/img/MdFRM/type_richmenu_guide/1200x810/01.png"));
            case "9":
                return new RichMenuArea(new RichMenuBounds(833, 843, 833, 843), new URIAction("HotLine", "https://hotline-km.herokuapp.com/"));
            case "10":
                return new RichMenuArea(new RichMenuBounds(1666, 843, 834, 843), new URIAction("MRT", "http://www.bangkokmetro.co.th/FareRate.aspx?Lang=Th"));
            default:
                return new RichMenuArea(new RichMenuBounds(0, 843, 833, 843), new MessageAction("Back", "Back"));
        }
    }
}