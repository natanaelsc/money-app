package br.com.moneyapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "entry")
public class Entry {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String description;

	@NotNull
	@Column(name = "due_date")
	private LocalDate dueDate;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;

	@NotNull
	private BigDecimal amount;

	private String note;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EntryType type;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
}
