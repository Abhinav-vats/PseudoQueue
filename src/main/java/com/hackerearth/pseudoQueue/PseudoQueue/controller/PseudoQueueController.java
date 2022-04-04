package com.hackerearth.pseudoQueue.PseudoQueue.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerearth.pseudoQueue.PseudoQueue.entity.Account;
import com.hackerearth.pseudoQueue.PseudoQueue.entity.FinalResponse;

@RestController
@SuppressWarnings({ "unchecked", "rawtypes" })
@RequestMapping("/pseudo-queue")
public class PseudoQueueController {
	
	final List<Account> accountQueue = new ArrayList<Account>();
	
	
	
	@GetMapping("/getAccountInfo")
	public List<Account> getAccountInfo() {
		return accountQueue;
	}
	
	
	@PostMapping("/addAccount")
	public ResponseEntity addAccount(@RequestBody final String account) {
		System.out.println("Entered: PseudoQueueController.addAccount");
		final String decryptedJsonBody = new String(Base64.getMimeDecoder().decode(account));
		final ObjectMapper jsonObj =  new ObjectMapper();
		Account accountObj;
		try {
			accountObj = jsonObj.readValue(decryptedJsonBody, Account.class);
			accountQueue.add(accountObj);
			System.out.println("Exited: PseudoQueueController.addAccount");
			return new ResponseEntity(new FinalResponse("OK", 200, "Added Successfully"), HttpStatus.OK);
		} catch (JsonProcessingException  e ) {
			System.out.println(e.getLocalizedMessage());
			System.out.println("Exited: PseudoQueueController.addAccount");
			return new ResponseEntity(new FinalResponse("BAD REQUEST", 400, "Not Added Successfully"), HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
