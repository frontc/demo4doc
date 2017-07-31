package com.lefer.demo4doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lefer.demo4doc.common.CustomerJsonSerializer;
import com.lefer.demo4doc.common.JacksonJsonFilter;
import com.lefer.demo4doc.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo4docApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void jsonFilter() {
        Article a1 = new Article();
        a1.setId("1");
        a1.setTitle("1");
        a1.setContent("1");
        Article a2 = new Article();
        a2.setId("2");
        a2.setTitle("2");
        a2.setContent("2");
        List<Article> list = new ArrayList<Article>();
        list.add(a1);
        list.add(a2);
        try{
            CustomerJsonSerializer cjs= new CustomerJsonSerializer();
            // 设置转换 Article 类时，只包含 id, name
            cjs.filter(Article.class, "id,name", null);
            String include = cjs.toJson(a1);
            System.out.println("include: " + include);
            cjs = new CustomerJsonSerializer();
            // 设置转换 Article 类时，过滤掉 id, name
            cjs.filter(Article.class, null, "id,name");
            String filter = cjs.toJson(a2);
            System.out.println("filter: " + filter);
            cjs = new CustomerJsonSerializer();
            // 设置转换 Article 类时，过滤掉 id, name
            cjs.filter(Article.class, null, "id,name");
            String listFilter = cjs.toJson(list);
            System.out.println("filter: " + listFilter);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
