package com.first_springboot.demo.controller;


import com.first_springboot.demo.controller.vm.PlayerVM;
import com.first_springboot.demo.dto.PlayerDto;

import com.first_springboot.demo.model.Player;
import com.first_springboot.demo.service.PlayerService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController   // Controller use jason value


public class PlayerController {

    @Autowired
    private PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }





    @GetMapping("/players")
public List<PlayerVM> getAllPlayers(){

        return playerService.getAllPlayers();
}


@PostMapping("/players")

    public PlayerDto addPlayer(@RequestBody @Validated PlayerDto playerDto) throws SystemException {

      return   playerService.savePlayer(playerDto);
}


@PutMapping("/players")

    public PlayerDto updatePlayer(@RequestBody PlayerDto playerDto) throws SystemException {
        return playerService.updatePlayer(playerDto);
}


 //   http://localhost:8080/players?id=13
 /*@DeleteMapping("/players")
    public void deletePlayer(@RequestParam Long id){
     playerService.removePlayer(id);

 }*/


    //or

    //   http://localhost:8080/players/13
    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id){
     playerService.removePlayer(id);

 }




 @GetMapping("/players/{id}")

    public PlayerDto getPlayerById(@PathVariable Long id) throws SystemException {
       return playerService.getPlayerById(id);
 }


 @GetMapping("/players/name/{name}")

    public PlayerDto getPlayerByName(@PathVariable String name) throws SystemException {

        return playerService.getPlayerByName(name);

 }


    @GetMapping("/players/search/{name}")

    public List<PlayerDto>  searchName(@PathVariable String name) throws SystemException {

        return playerService. searchName(name);
}


}
