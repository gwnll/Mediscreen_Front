package com.mediscreen.front.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Note {

    @Id
    private String id;

    private Integer patientId;

    @NotNull(message = "observations cannot be null")
    public String observations;

}
