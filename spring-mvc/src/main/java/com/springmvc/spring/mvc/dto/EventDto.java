package com.springmvc.spring.mvc.dto;

import com.springmvc.spring.mvc.models.club;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private int id;

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotNull(message = "start time can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @NotNull(message = "end time can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    @NotEmpty(message = "type can not be empty")
    private String type;

    @NotEmpty(message = "photourl can not be empty")
    private String photourl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private club club;

}
