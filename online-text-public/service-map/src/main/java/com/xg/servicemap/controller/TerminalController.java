package com.xg.servicemap.controller;

import com.xg.internalcommon.dto.ResponseResult;
import com.xg.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseResult add(String name){
        return terminalService.add(name);
    }

}
