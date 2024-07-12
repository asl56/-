package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.Contact;
import com.Tzj.lost_found_system.pojo.PageBean;
import com.Tzj.lost_found_system.pojo.Result;
import com.Tzj.lost_found_system.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @GetMapping("/getContactAll")
    public Result getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer count){
        PageBean contacts = contactService.getAll(page,count);
        return Result.success(contacts);
    }
    @GetMapping("/getContact")
    public Result getContact(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer count, Integer id,String content,String contactTime,Integer lostID,Integer userID,Integer itemsUserID){
        PageBean contacts = contactService.getContact(page,count,id,content,contactTime,lostID,userID,itemsUserID);
        return Result.success(contacts);
    }
    @GetMapping("/getFoundContact")
    public Result getFoundContact(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer count, Integer id,String content,String contactTime,Integer userID,Integer foundID,Integer itemsUserID){
        PageBean contacts = contactService.getFoundContact(page,count,id,content,contactTime,userID,foundID,itemsUserID);
        return Result.success(contacts);
    }
    @PostMapping("/editContact")
    public int editContact(Contact contact){
        return contactService.editContact(contact);
    }
    @PostMapping("/addContact")
    public int addContact(@RequestBody Contact contact){
        return contactService.addContact(contact);
    }
    @GetMapping("/deleteContact") 
    public int deleteContact(Integer id){
        return contactService.deleteContact(id);
    }

}
