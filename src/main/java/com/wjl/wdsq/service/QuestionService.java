package com.wjl.wdsq.service;

import com.wjl.wdsq.dao.QuestionDAO;
import com.wjl.wdsq.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public  int addQuestion(Question question)
    {
        //html过滤
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));
       //敏感词过滤

        return questionDAO.addQuestion(question)>0?question.getId():0;
    }

    public List<Question> getLatestQuestions(int userId,int offset,int limit)
    {
        return questionDAO.selectLatestQuestions(userId, offset, limit);
    }
}
