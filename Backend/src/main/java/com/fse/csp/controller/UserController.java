package com.fse.csp.controller;
import com.fse.csp.exception.ResourceNotFoundException;
import com.fse.csp.model.ServiceRequest;
import com.fse.csp.model.User;
import com.fse.csp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    //Get All Users
    @GetMapping("AllUsers")
    public List<User> getAllUsers(){
        //Get All Users
        return userRepository.findAll(); }

    @PostMapping("NewUser")
    public User createUser(@RequestBody User user) {
        // save the new service request to the database
        return userRepository.save(user);
    }


    @GetMapping("GetUser/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String email) throws ResourceNotFoundException {
        User user = userRepository.findById(email).orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + email));
        return ResponseEntity.ok().body(user);}


    //Update User Details
    @PutMapping("UpdateUser/{email}")
    public ResponseEntity<User> updateServiceRequest(@PathVariable(value = "email") String email, @RequestBody User userDetails) throws ResourceNotFoundException {
        // find the service request by ID
        User user = userRepository.findById(email).orElseThrow(() -> new ResourceNotFoundException("Service Request not found for this email :: " + email));

        // update the service request with new details
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setContactNo(userDetails.getContactNo());
        user.setContactPreference(userDetails.getContactPreference());
        user.setPassword(userDetails.getPassword());
        user.setUser_role(userDetails.getUser_role());
        // save the updated service request to the database
        final User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("DeleteUser/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "email") String email) throws ResourceNotFoundException {
        User user = userRepository.findById(email).orElseThrow(() -> new ResourceNotFoundException("User not found for this userId :: " + email));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}