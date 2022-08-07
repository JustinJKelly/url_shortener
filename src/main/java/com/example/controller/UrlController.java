package com.example.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.example.model.Url;
import com.example.repository.UrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlController {
    
    @Autowired
    private UrlRepository urlRepository;

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/{urlHash}")
    @ResponseBody
    public void retrieveUrl(@PathVariable(value="urlHash") String urlHash, HttpServletResponse httpResponse) {
        Url url = urlRepository.getUrlByHash(urlHash);

        if (url != null) {
            try {
                httpResponse.sendRedirect(url.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    @PostMapping("/createURL")
    public ResponseEntity<String> createURL(Url url) {
        Url obj = new Url();
        obj.setHash(UUID.randomUUID().toString().replace("-", "").substring(0,16));
        obj.setUrl(url.getUrl());
        String result = urlRepository.saveUrl(obj);
        
        if (result != null) {
            return new ResponseEntity<String>(System.getenv("HOST") + "/" + result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("URL not Successfully Created", HttpStatus.BAD_REQUEST);
        }
    }
}
