package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Tutorial;
import com.example.demo.repository.TutorialRepository;

@Service

public class TutorialServiceImpl implements TutorialService {
	
	private TutorialRepository tutorialRepository ;
	
	@Override
	public Tutorial CreateTutorial(Tutorial tutorial) {
		return tutorialRepository.save(tutorial);
	}
	
	@Override
	public Tutorial getTutorialById(Long id) {
		Optional<Tutorial> optionalTutorial = tutorialRepository.findById(id) ;
		return optionalTutorial.get();
	}
	@Override
	public List<Tutorial> getAllTutorials() {
		
		return tutorialRepository.findAll();
	}
	@Override
	public Tutorial updateTutorial(Tutorial tutorial) {
//		check tutorial exist 
		Long tutorialId = tutorial.getId();
		Tutorial existance = tutorialRepository.findById(tutorialId).get();
		// TODO add check if value not updated 
			existance.setTitle(tutorial.getTitle());
			existance.setDescription(tutorial.getDescription());
			existance.setPublished(false);
			Tutorial updated = tutorialRepository.save(existance);
	
		
		return null;
	}
	@Override
	public void deleteTutorial(Long id) {
		tutorialRepository.deleteById(id);
		
	}
	
	
}