package com.nakshtra.interior.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  @ManyToOne
   @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "designer_id", referencedColumnName = "id")
    private User designer;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Column(name = "started_date")
    private Date startedDate;

   
    public Project() {
        super();
    }


	public Project(int id, User client, User designer, String name, String description, ProjectStatus status,
			Date startedDate) {
		super();
		this.id = id;
		this.client = client;
		this.designer = designer;
		this.name = name;
		this.description = description;
		this.status = status;
		this.startedDate = startedDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getClient() {
		return client;
	}


	public void setClient(User client) {
		this.client = client;
	}


	public User getDesigner() {
		return designer;
	}


	public void setDesigner(User designer) {
		this.designer = designer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ProjectStatus getStatus() {
		return status;
	}


	public void setStatus(ProjectStatus status) {
		this.status = status;
	}


	public Date getStartedDate() {
		return startedDate;
	}


	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}


	@Override
	public String toString() {
		return "Project [id=" + id + ", client=" + client + ", designer=" + designer + ", name=" + name
				+ ", description=" + description + ", status=" + status + ", startedDate=" + startedDate + "]";
	}
    

    
    
}
