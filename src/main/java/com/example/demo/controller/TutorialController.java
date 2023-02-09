package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Tutorial;
import com.example.demo.repository.TutorialRepository;
import com.example.demo.response.ResponseHandler;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class TutorialController{
	
	@Autowired
	TutorialRepository tutorialRepository ;
	
	@GetMapping("/tutorials")
	public ResponseEntity<Object> getAllTutorials(@RequestParam(required = false) String title) {
		
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				// find by word (title)
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				  return ResponseHandler.generateResponse("No data!", HttpStatus.NO_CONTENT, null);
			}
			  return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, tutorials);
		} catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

		}
	}
	
	
	@PostMapping("/postTutorial")
	public ResponseEntity<Object> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
		     return  ResponseHandler.generateResponse("Successfully Added!", HttpStatus.OK, _tutorial);

		} catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);

		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Object> getTutorialById(@PathVariable("id") long id) {
		java.util.Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			  return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, tutorialData.get());

		} else {
			  return ResponseHandler.generateResponse("No data!", HttpStatus.NO_CONTENT, null);
		}
	}
	
	@PutMapping("/updateTutorials/{id}")
	public ResponseEntity<Object> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		java.util.Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
		     return  ResponseHandler.generateResponse("Updated!", HttpStatus.OK, tutorialRepository.save(_tutorial));

		} else {

		     return  ResponseHandler.generateResponse("Tutorial ID Not Found!", HttpStatus.NOT_FOUND, null);
		}
	}
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<Object> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			  return ResponseHandler.generateResponse("No data!", HttpStatus.NO_CONTENT, null);
		} catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}
	
	
	
}