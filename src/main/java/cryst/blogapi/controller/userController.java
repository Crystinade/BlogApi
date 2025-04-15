package cryst.blogapi.controller;

import cryst.blogapi.payloads.ApiResponse;
import cryst.blogapi.payloads.UserDto;
import cryst.blogapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class userController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto)
    {
      UserDto createUserDto = this.userService.createUser(userDto);
      return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto > updateUser( @RequestBody UserDto userDto, @PathVariable("userId") Integer uId){
      UserDto updatedUser =  this.userService.updateUser(userDto, uId);
      return  ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId){

        this.userService.deleteUser(uId);
      return   new ResponseEntity(new ApiResponse("user deleted", "success"), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUsers(){

        return ResponseEntity.ok(this.userService.getAll());
    }

}
