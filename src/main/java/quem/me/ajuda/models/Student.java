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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter @Setter
@EqualsAndHashCode(of = {"registration"})
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Lombok in probably creating gets/sets for internal properties of string
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String registration;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
}
