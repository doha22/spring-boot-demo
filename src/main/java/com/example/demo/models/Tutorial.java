package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title",nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "published")
  private boolean published;

  public Tutorial() {

  }

  public Tutorial(String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }
 
  public void setTitle(String title) {
	this.title = title;
}
  public void setId(long id) {
	this.id = id;
}
  public void setDescription(String description) {
	this.description = description;
}
  public void setPublished(boolean published) {
	this.published = published;
}
  public boolean isPublished() {
	return published;
}
  
  public String getDescription() {
	return description;
}
  public long getId() {
	return id;
}
  public String getTitle() {
	return title;
}
  
  
  @Override
	public String toString() {
		// TODO Auto-generated method stub
	    return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
	}

  
}