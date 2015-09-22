package ua.com.homebudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.homebudget.dto.SystemVersion;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Autowired
    SystemVersion version;

    @RequestMapping("/version")
    public SystemVersion getVersion() {
        return version;
    }
}
