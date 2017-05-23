package com.roshantest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	@ApiOperation(value = "Display Greeting")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Greeting.class),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Server Error") })
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		Greeting greeting = new Greeting(String.format(template, name));
		greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
		return greeting;
	}
	
	
	@ApiOperation(value = "Path Greeting")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Server Error")}) 
	@GetMapping("/greeting/{name}")
	public Greeting greetingPath(@PathVariable(value="name") String name) {
		Greeting greeting = new Greeting(String.format(template, name));
		greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
		return greeting;
	}

}
