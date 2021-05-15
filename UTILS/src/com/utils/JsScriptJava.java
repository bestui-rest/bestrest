package com.utils;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.List;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
public class JsScriptJava {
    public static void main(String[] args) throws Exception {
       ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        List<ScriptEngineFactory> engineFactories = scriptEngineManager.getEngineFactories();
        if(engineFactories.size() == 0) {
            System.out.println("��JVM�в�֧���κνű�����");
            return;
        }
         
        System.out.println("��JVM֧�ֵĽű�������:");
        for(ScriptEngineFactory engineFactory : engineFactories) {
            System.out.println("��������:" + engineFactory.getEngineName());
            System.out.println("\t�ɱ�ScriptEngineManagerʶ�������:" + engineFactory.getNames());
            System.out.println("\t������֧�ֵĽű���������:" + engineFactory.getLanguageName());
            System.out.println("\t�Ƿ��̰߳�ȫ:" + engineFactory.getParameter("THREADING"));
        }
    	
    }
}