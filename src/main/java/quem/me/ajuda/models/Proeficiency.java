package quem.me.ajuda.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Lombok in probably creating gets/sets for internal properties of string
public class Proeficiency {
	@Id
	@JsonProperty(access = Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty
	private String discipline;

	@Max(value = 5)
	@Min(value = 1)
	@Column(nullable = false)
	private Integer level;
	
	@ManyToOne
	@JoinColumn(name = "tutor_info_id")
	private TutorInfo tutorInfo;
}
