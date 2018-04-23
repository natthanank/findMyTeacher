package com.natthanan.findmyteacher.controller;

import java.util.List;

import com.natthanan.findmyteacher.model.HotLine;
import com.natthanan.findmyteacher.repository.HotLineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HotLineController {

    @Autowired
    private HotLineRepository hotLineRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hotlines/{category}")
    public @ResponseBody Iterable<HotLine> getHotlineByCategory(@PathVariable int category) {
        List<HotLine> hotLines = hotLineRepository.findHotLineByCategory(category);
        return hotLines;
    }
}
