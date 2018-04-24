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
            .size(new RichMenuSize(1200, 810))
            .build();
        }
        return richMenu;
    }

    private static RichMenuArea createRichMenuArea(String corner) {
          
        switch (corner) {
            case "6":
                return new RichMenuArea(new RichMenuBounds(0, 0, 400, 405), new URIAction("Facebook", "https://www.facebook.com"));
            case "7":
                return new RichMenuArea(new RichMenuBounds(400, 0, 400, 405), new URIAction("OreMange", "http://www.oremanga.com/"));
            case "8":
                return new RichMenuArea(new RichMenuBounds(800, 0, 400, 405), new URIAction("Line", "https://scdn.line-apps.com/n/line_official_v2/img/MdFRM/type_richmenu_guide/1200x810/01.png"));
            case "9":
                return new RichMenuArea(new RichMenuBounds(400, 405, 400, 405), new URIAction("HotLine", "https://hilight.kapook.com/view/84563"));
            case "10":
                return new RichMenuArea(new RichMenuBounds(800, 405, 400, 405), new URIAction("MRT", "http://www.bangkokmetro.co.th/FareRate.aspx?Lang=Th"));
            default:
                return new RichMenuArea(new RichMenuBounds(0, 405, 400, 405), new MessageAction("Back", "Back"));
        }
    }
}