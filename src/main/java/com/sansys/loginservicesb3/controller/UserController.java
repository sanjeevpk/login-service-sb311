/**
 * © Copyright Sansys. All rights reserved;
 * @author - Sanjeev
 * @version - 1.0
 * @CreatedOn - 17-Jul-2023 8:07:27 pm
 * @Usage - 
 */

package com.sansys.loginservicesb3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class to handle the requests post login
 */

@RestController
public class UserController {

  @GetMapping("/")
  public String defaultLanding() {
    return "All can access!";
  }
 
  @GetMapping("/home")
  public String home() {
    return "In Home! Only users can access!";
  }
 
}
