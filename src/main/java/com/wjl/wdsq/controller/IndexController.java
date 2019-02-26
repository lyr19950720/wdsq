package com.wjl.wdsq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    /*@RequestMapping(path={"/","/index"})
    @ResponseBody
    public String index()
    {
        return "hello NowCoder";
    }*/
    @RequestMapping(value = "/profile/{groupId}/{userId}")
    @ResponseBody//返回是一个文本，不是模板
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", defaultValue = "nowcoder") String key) {
        return String.format("{%s},{%d},{%d},{%s}", groupId, userId, type, key);
    }

    @RequestMapping(path={"/vm"},method = {RequestMethod.GET})
    public String template(Model model){
        model.addAttribute("value1","vvvv");
        return "home";
    }

}
