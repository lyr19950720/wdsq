package com.wjl.wdsq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class SensitiveService implements InitializingBean {

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public void afterPropertiesSet() throws Exception {
         try{
             InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SensitiveWords.txt");

         }catch (Exception e)
         {
             logger.error("读取敏感词文件失败"+e.getMessage());
         }
    }

    //增加关键词 abc
    private  void addWord(String lineText)
    {
         TrieNode tempNode = rootNode;
         for(int i=0;i<lineText.length();i++)
         {
             Character c  = lineText.charAt(i);
             TrieNode node = tempNode.getSubNode(c);
             if(node==null)
             {
                 node = new TrieNode();
                 tempNode.addSubNode(c,node);
             }
             tempNode = node;
             if(i==lineText.length()-1)
             {
                 tempNode.setkeywordEnd(true);
             }
         }
    }
    private class TrieNode{
        //是不是关键词的结尾
         private boolean end = false;

         //当前节点下的所有子节点 ab ac ad
         private Map<Character,TrieNode> subNodes = new HashMap<Character, TrieNode>();

         //
         public void addSubNode(Character key,TrieNode node)
         {
             subNodes.put(key,node);
         }

         TrieNode getSubNode(Character key)
         {
             return subNodes.get(key);
         }
        boolean  isKeyWordEnd()
        {
            return end;
        }
        void setkeywordEnd(boolean end)
        {
            this.end = end;
        }

    }
     private  TrieNode rootNode = new TrieNode();
}
