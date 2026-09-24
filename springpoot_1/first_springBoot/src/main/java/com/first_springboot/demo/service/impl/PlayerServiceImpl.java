package com.first_springboot.demo.service.impl;

import com.first_springboot.demo.controller.vm.PlayerVM;
import com.first_springboot.demo.dto.PlayerDto;

import com.first_springboot.demo.model.Player;
import com.first_springboot.demo.repo.PlayerRepo;
import com.first_springboot.demo.service.PlayerService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepo playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepo playerRepo){

        this.playerRepo=playerRepo;
    }


    @Override
    public PlayerDto savePlayer(PlayerDto playerDto) throws SystemException {

        //this check for id should be null
        if(Objects.nonNull(playerDto.getId())){
            throw  new SystemException("id mast be null");
        }

        //this check for salary should be found
        if(Objects.isNull(playerDto.getSalary())){
            throw  new SystemException("salary mast be found");
        }

        //this check for number should be found
        if(Objects.isNull(playerDto.getNumber())){
            throw  new SystemException("number mast be found");
        }
         //this check for name should be found
        if(Objects.isNull(playerDto.getName())){
            throw  new SystemException("name mast be found");
        }

        //this check for name not be doublicated name
        Optional<Player> playerOptional = playerRepo.findByName(playerDto.getName());
        if (playerOptional.isPresent()){
            throw new SystemException("userName exsist by name" + playerDto.getName());
        }

        Player player = new Player(
                playerDto.getName(),
                playerDto.getNumber(),
                playerDto.getSalary()

        );
        return new PlayerDto().toDto(playerRepo.save(player));
    }


    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto) throws SystemException {
        //this check for id should be found
        if(Objects.isNull(playerDto.getId())){
            throw  new SystemException("id mast be not null");
        }


        Optional<Player> playerOptional = playerRepo.findById(playerDto.getId());
        if (playerOptional.isEmpty()){
            throw  new SystemException("id mast be not null");
        }

        Player player = new Player(
                playerDto.getId(),
                playerDto.getName(),
                playerDto.getNumber(),
                playerDto.getSalary()
        );

        if (playerOptional.get().getName().equals(playerDto.getName())){
             playerRepo.save(player);

            return playerDto ;
        }

        playerOptional = playerRepo.findByName(playerDto.getName());
        if (playerOptional.isPresent()){
            throw new SystemException("userName exsist by name" + playerDto.getName());
        }


        playerRepo.save(player);
        return playerDto;
    }


    @Override
    public List<PlayerVM> getAllPlayers() {

        //toDto

// List<PlayerDto> playerDtos = playerRepo.findAll().stream().map(player ->
//                new PlayerDto().toDto(player)).collect(Collectors.toList());
//
//        long count = playerRepo.count();
//        playerDtos.stream().forEach(playerDto -> {
//            playerDto.setDetails(playerDto.getName()+""+playerDto.getId());
//            playerDto.setCount(count);
//
//        });
//
//        return playerDtos;



        //toVM


       return playerRepo.findAll().stream().map(player ->
                new PlayerVM(player.getId(),player.getName())).collect(Collectors.toList());




//        players.stream().forEach(player -> player.setDetails(player.getName() +  player.getId()));
//
//        return players;
    }

    @Override
    public void removePlayer(Long id) {
        playerRepo.deleteById(id);
    }

    @Override
    public PlayerDto getPlayerById(Long id) throws SystemException {
//       Optional<Player> player= playerRepo.findById(id);
//
//        return player.get();

        //=====
   Player player= playerRepo.findById(id).get();

   return new PlayerDto().toDto(player);

    }

    @Override
    public PlayerDto getPlayerByName(String name) throws SystemException  {

        Player player= playerRepo.findByName(name).get();
        return new PlayerDto().toDto(player);

    }

    @Override
    public List<PlayerDto> searchName(String name) throws SystemException {
       return playerRepo.findByNameContainingIgnoreCase(name).get().stream().map(player ->
               new PlayerDto().toDto(player)).collect(Collectors.toList());

    }


}
