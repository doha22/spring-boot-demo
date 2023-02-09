package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Tutorial;


public interface TutorialService{
	
	Tutorial CreateTutorial(Tutorial tutorial);
	Tutorial getTutorialById(Long id);
	List<Tutorial> getAllTutorials();
	Tutorial updateTutorial(Tutorial tutorial);
	void deleteTutorial(Long id);
	
}