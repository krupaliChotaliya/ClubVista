package com.springmvc.spring.mvc.dto;

import com.springmvc.spring.mvc.models.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {

    private int id;
    @NotEmpty(message = "title can not be empty")
    private String title;
    @NotEmpty(message = "photourl can not be empty")
    private String photourl;
    @NotEmpty(message = "content can not be empty")
    private String content;
    private User createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
