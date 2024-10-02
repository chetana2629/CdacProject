package com.nakshtra.interior.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "designs")
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Rooms_id", nullable = false)
    private Rooms Rooms;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image; 
    
    public Design() {
    }

  
    public Design(int id, Rooms Rooms, String name, String description, String image) {
        this.id = id;
        this.Rooms = Rooms;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rooms getRooms() {
        return Rooms;
    }

    public void setRooms(Rooms Rooms) {
        this.Rooms = Rooms;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Design [id=" + id + ", Rooms=" + Rooms + ", name=" + name + ", description=" + description + ", image=" + image + "]";
    }
}
