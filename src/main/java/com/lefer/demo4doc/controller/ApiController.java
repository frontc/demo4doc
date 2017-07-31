package com.lefer.demo4doc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lefer.demo4doc.common.CustomerJsonSerializer;
import com.lefer.demo4doc.common.Result;
import com.lefer.demo4doc.common.ResultGenerator;
import com.lefer.demo4doc.model.Article;
import com.lefer.demo4doc.model.View;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author fang
 * @creatdate 17-7-28
 */
@Api(value = "字符串处理服务",description = "简单的字符串处理，提供截取、替换、查询API")
@RestController
@RequestMapping(path = "/api")
public class ApiController {
    @ApiOperation(value = "截取字符串")
    @RequestMapping(path = "/substr",method = RequestMethod.POST)
    public Result subStr(@ApiParam(required = true,value = "待处理字符串") @RequestParam String str,
                         @ApiParam(required = true,value = "截取开始位置") @RequestParam int from,
                         @ApiParam(required = true,value = "截取结束位置") @RequestParam int to) {
        return ResultGenerator.genSuccessResult(str.substring(from,to));
    }

    @ApiOperation(value = "连接字符串")
    @RequestMapping(path = "/joinstr",method = RequestMethod.POST)
    public Result joinStr(@ApiParam(required = true,value = "待处理字符串") @RequestParam String str1,
                         @ApiParam(required = true,value = "需连接字符串") @RequestParam String str2) {
        return ResultGenerator.genSuccessResult(str1.concat(str2));
    }

    @ApiIgnore
    @RequestMapping(path="/giveback",method = RequestMethod.POST)
    public Object giveBack(){
        Article article = new Article();
        article.setId("123");
        article.setTitle("test");
        article.setContent("hahahatest");
        try{
            CustomerJsonSerializer cjs = new CustomerJsonSerializer();
            cjs.filter(Article.class,null,"title");
            String result = cjs.toJson(article);
            return result;
        }catch (Exception e){

        }
        return article;
    }

    @ApiIgnore
    @JsonView(View.Summary2.class)
    @RequestMapping(path="/test",method = RequestMethod.POST)
    public Result test(){
        Article article = new Article();
        article.setId("123");
        article.setTitle("test");
        article.setContent("hahahatest");
        return ResultGenerator.genSuccessResult(article);
    }
}
