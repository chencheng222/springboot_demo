package com.cc.study.springboot_demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author chenc
 * @date 2020/06/03
 **/
public class Test {

    @org.junit.Test
    public void jackSonTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode node = objectMapper.createObjectNode();
        node.put("name", "张三");
        node.put("age", 22);

        String jsonStr = node.toString();
        JsonNode tree = objectMapper.readTree(jsonStr);
        boolean has = tree.has("age");

        JsonNode ageNode = tree.get("age");
//        System.out.println(ageNode.toString());


        Map<String, Object> map = new HashMap<>();
        HashMap hashMap = objectMapper.readValue(jsonStr, HashMap.class);
//        System.out.println(hashMap);

        Object body = "{\"body\":\"bX3HBsmc2eTTsnCHReutjR2ZU7IW43yTk6eAGtGj4IKLgESPv2Xhjy8sqsIH5AgJCq/pxH9GV71LP5q25KZXktQjpnmjtNCgT0piK767EPYJhnF979NNrzVapYSKEY7aFw1JhLbbncHkQA9Iwosu9Q==\"}";
        String s = objectMapper.writeValueAsString(body);
        System.out.println(s);
        System.out.println(s.equals(body.toString()));
        System.out.println(body instanceof String);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("body", "bX3HBsmc2eTTsnCHReutjR2ZU7IW43yTk6eAGtGj4IKLgESPv2Xhjy8sqsIH5AgJCq");
        System.out.println(objectNode.asText());
    }

    @org.junit.Test
    public void jacksonArray() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String array = "[\n" +
                "    {\n" +
                "        \"id\": \"855d4627800147d0b96ad8e470df47d9\",\n" +
                "        \"remark\": \"图片1备注\"  \n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"8f5a8d6a2a714d2e85362e2bec1a8731\",\n" +
                "        \"remark\": \"图片2备注\" \n" +
                "    }]\n";
        JsonNode arryNode = objectMapper.readTree(array);
        if (arryNode.isArray()) {
            for (int i = 0; i < arryNode.size(); i++) {
                JsonNode node = arryNode.get(i);
                System.out.println(node.toString());
            }
        }

    }
}
