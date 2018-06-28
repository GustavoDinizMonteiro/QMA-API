package quem.me.ajuda.models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MinimalStudent {
	private String name;
	private String email;
	private Boolean isTutor;
	
	public MinimalStudent(Student student) {
		this.name = student.getName();
		this.email = student.getEmail();
		this.isTutor = student.getTutorInfo() != null;
	}
}
