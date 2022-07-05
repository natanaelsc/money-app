package br.com.moneyapi.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EntryFilter {
    
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDateUntil;
}
