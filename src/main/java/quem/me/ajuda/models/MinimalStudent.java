package quem.me.ajuda.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MinimalStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private Boolean isTutor;
	
	public MinimalStudent(Student student) {
		this.name = student.getName();
		this.email = student.getEmail();
		this.isTutor = student.getTutorInfo() != null;
	}
}
