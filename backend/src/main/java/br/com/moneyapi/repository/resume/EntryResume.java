package br.com.moneyapi.repository.resume;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.moneyapi.model.EntryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntryResume {
 
    private Long id;
    private String description;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private EntryType type;
    private String category;
    private String person;
}
