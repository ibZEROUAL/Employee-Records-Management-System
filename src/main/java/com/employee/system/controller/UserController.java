package com.employee.system.controller;

import com.employee.system.dto.UserDto;
import com.employee.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<UserDto> getUserId(@PathVariable String id){
        return ResponseEntity.ok(userService.getById(Long.valueOf(id)));
    }

    @GetMapping("/search")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<UserDto>> getUsersByRole(@RequestParam String role){
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto dto){
        return ResponseEntity.ok(userService.addUser(dto));
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto, @PathVariable String id){
        var userDto = userService.updateUser(Long.valueOf(id),dto);
        return ResponseEntity.ok(userDto);
    }

    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(Long.valueOf(id));
    }

}
