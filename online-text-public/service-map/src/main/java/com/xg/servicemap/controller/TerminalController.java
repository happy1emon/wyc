package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @USER: XGGG
 * @DATE: 2023/4/3
 */
@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;
    /**
     * add terminal in service
     * @param name terminal name
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestParam String name, Long desc){
        return terminalService.add(name,desc);
    }


    @PostMapping("/aroundSearch")
    public ResponseResult aroudSearch(String center,String radius){
        return terminalService.aroundSearch(center,radius);
    }



}
