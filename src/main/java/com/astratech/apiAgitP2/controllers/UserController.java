package com.astratech.apiAgitP2.controllers;

import com.astratech.apiAgitP2.DTOS.UserDTO;
import com.astratech.apiAgitP2.models.UserModel;
import com.astratech.apiAgitP2.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    private UserModel ConvertUserToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserModel.class);
    }

    private UserDTO ConvertUserToDTO(UserModel userModel){
        return modelMapper.map(userModel, UserDTO.class);
    }

    @Operation(summary = "Insert Data User", description = "Insert User Data User")
    @PostMapping("user/create")
    public Map<String, Object> createUser (@RequestBody UserDTO userDTO){
        Map<String, Object> mapResult = new HashMap();
        UserModel user = ConvertUserToEntity(userDTO);
        user.setName(userDTO.getName());
        user.setGender(userDTO.getGender());

        mapResult.put("Message", "Data berhasil di input");
        mapResult.put("Data", userRepository.save(user));
        return  mapResult;
    }

    @GetMapping("user/all")
    public Map<String, Object> getAll(){
        Map<String, Object> mapResult = new HashMap<>();
        List<UserDTO> listUserResult = new ArrayList<>();
        for(UserModel user : userRepository.findAll()){
            UserDTO userDTO = ConvertUserToDTO(user);
            listUserResult.add(userDTO);
        }

        String message;
        if(listUserResult.isEmpty()){
            message="Data Kosong";
        }else{
            message="Show All Data";
        }
        mapResult.put("Message", message);
        mapResult.put("data", listUserResult);
        mapResult.put("Total", listUserResult.size());
        return mapResult;
    }

    @PutMapping("user/update")
    public Map<String, Object> updateUser(@RequestParam(value = "userId") Long id,
                                          @RequestBody UserDTO userDTO){
        Map<String, Object> mapResult = new HashMap<>();
        UserModel user = userRepository.findById(id).orElse(null);

        user.setName(userDTO.getName());
        user.setName(userDTO.getGender());

        mapResult.put("Message", "Data berhasil di update");
        mapResult.put("Data", userRepository.save(user));
        return  mapResult;
    }

    @DeleteMapping("user/delete/{userId}")
    public Map<String, Object> DeleteUser(@PathVariable(value = "userId") Long Id){
        Map<String, Object> mapResult = new HashMap<>();
        UserModel user = userRepository.findById(Id).orElse(null);
        userRepository.delete(user);
        mapResult.put("Message", "Data berhasil dihapus");
        return mapResult;
    }
}
