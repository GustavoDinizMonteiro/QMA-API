package quem.me.ajuda.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="tutor_info_id", referencedColumnName="id")
	private Set<Proeficiency> proeficiencies;
	
	public TutorInfo() {
		this.cash = 0;
		this.rating = 4;
		this.proeficiencies = new HashSet<>();
	}
}
	