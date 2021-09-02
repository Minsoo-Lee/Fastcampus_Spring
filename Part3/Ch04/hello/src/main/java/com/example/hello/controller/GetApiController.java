package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")    // http://localhost:9090/api/get/hello
    public String hello() {
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)   // get http://localhost:9090/api/get/hi
    public String hi() {
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable{name}

    @GetMapping("/path-variable/{name}") // {name}속 name과 아래 줄의 name이 같아야 함
    public String pathVariable(@PathVariable String name) {
        // == (@PathVariable(name = "name") String pathName 으로도 사용가능(왼쪽 name은 고정변수 - 바꾸면 안 됨)
        System.out.println("PathVariable: " + name);
        return name;
    }

    // search?q = intellij
    // &oq = intellij
    // &aqs = chrome.0.69i59l2j0i512j0i433i512j69i60j69i61j69i60j69i61.1951j0j7
    // &sourceid = chrome
    // &ie = UTF-8

    // ?key=value&key2=value2&key3=value3.....

    // http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name  + " " + email + " " + age;
    }

    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());
        return userRequest.toString();
    }
}