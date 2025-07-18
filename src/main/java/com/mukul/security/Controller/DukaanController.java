package com.mukul.security.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mukul.security.Model.DukaanItem;
import com.mukul.security.Service.DukaanService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api")
public class DukaanController {

    private final DukaanService service;
    DukaanController(DukaanService service){
        this.service = service;
    }

    @GetMapping("/items")
    public List<DukaanItem> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("items/{id}")
    public DukaanItem getItem(@PathVariable int id) {
        return service.getItem(id);
    }
    
    @PostMapping("/items")
    public String postMethodName(@RequestBody DukaanItem item) {
        return service.AddItem(item);        
    }
}
