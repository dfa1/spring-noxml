package com.humaorie.noxml.webapp;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String index(Principal principal) {
        return "index";
    }
}
