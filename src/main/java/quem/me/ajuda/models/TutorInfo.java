package quem.me.ajuda.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tutor_info")
@Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Lombok in probably creating gets/sets for internal properties of string
public class TutorInfo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column
	@JsonProperty(access = Access.READ_ONLY)
	private Integer cash;
	
	@Column
	@JsonProperty(access = Access.READ_ONLY)
	private float rating;
	
	public TutorInfo() {
		this.cash = 0;
		this.rating = 4;
	}
}
	