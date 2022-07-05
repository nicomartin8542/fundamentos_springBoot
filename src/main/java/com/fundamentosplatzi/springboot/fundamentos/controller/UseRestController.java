package com.fundamentosplatzi.springboot.fundamentos.controller;


import com.fundamentosplatzi.springboot.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.PutUser;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UseRestController {

    private GetUser getUser;

    private CreateUser createUser;

    private DeleteUser deleteUser;

    private PutUser putUser;

    private UserService userService;

    public UseRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, PutUser putUser, UserService userService) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.putUser = putUser;
        this.userService = userService;
    }

    //Metdodo GET
    @GetMapping("/")
    List<User> get() {
        return getUser.getAll();
    }


    //Metodo POST
    @PostMapping("/")
    ResponseEntity<User> newUser (@RequestBody User user) {
        return new ResponseEntity<>(createUser.save(user), HttpStatus.CREATED);
    }

    //Meotod DELETE
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
       deleteUser.delete(id);
       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    //Metodo Put
    @PutMapping("/{id}")
    ResponseEntity<User> updateUser (@RequestBody User user, @PathVariable Long id) {
        return new ResponseEntity<>(putUser.update(user, id), HttpStatus.OK);
    }




}
