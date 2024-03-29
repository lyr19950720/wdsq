package com.wjl.wdsq.controller;


import com.wjl.wdsq.model.HostHolder;
import com.wjl.wdsq.model.Question;
import com.wjl.wdsq.service.QuestionService;
import com.wjl.wdsq.service.UserService;
import com.wjl.wdsq.util.WendaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class QuestionController {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

   @Autowired
    QuestionService questionService;

   @Autowired
    HostHolder hostHolder;

    @RequestMapping(value = "/question/add", method = {RequestMethod.POST})
    @ResponseBody
    public String addQuestion(@RequestParam("title")String title,@RequestParam("content")String content)
    {
        try{
            Question question = new Question();
            question.setContent(content);
            question.setTitle(title);
            question.setCreatedDate(new Date());
            question.setCommentCount(0);
            if(hostHolder.getUser()==null)
            {
                //question.setUserId(WendaUtil.ANONYMOUS_USERID);
                return WendaUtil.getJSONString(999);
            }
           else {
                question.setUserId(hostHolder.getUser().getId());
            }
            if(questionService.addQuestion(question)>0){
                       return  WendaUtil.getJSONString(0);
            }
        }catch (Exception e)
        {
            logger.error("增加题目失败"+e.getMessage());
        }
        return  WendaUtil.getJSONString(1,"失败");
    }
}
