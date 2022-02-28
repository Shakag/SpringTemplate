package com.shakag.service.impl;

import com.shakag.service.IKService;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class IKServiceImpl implements IKService {
    public void test() throws IOException{
        //调用分词
        List<String> list = IKAnalyzer("我是中国人,单词2");
        System.out.println("list = " + list); //list = [我是中国人, 单词2]
    }

    @Override
    public List<String> IKAnalyzer(String originMsg) throws IOException {
        //默认ik配置文件
        Configuration defaultConfig = DefaultConfig.getInstance();
        defaultConfig.setUseSmart(true); //开启ik_smart模式

        //ik执行器
        IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(originMsg),defaultConfig);

        //获取结果
        Lexeme lex=null;
        List<String> list=new ArrayList<>();
        while((lex=ikSegmenter.next())!=null){
            list.add(lex.getLexemeText());
        }
        return list;
    }
}
