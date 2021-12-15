package com.bolsadeideas.springboot.webflux.app.models.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "Feedback")

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

	@Id
	private String id;

	@NotNull
	@Pattern(regexp="\t^\\d+$\n")
	@Size(min = 1, max = 10 ,message = "the value has to be between 1 to 10 ")
	private int feedbackRating;

	@NotEmpty
	private String feedback;

	@Pattern(regexp="^[a-zA-Z]+$",message = " this field has to be alphabetic")
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

	@NotNull
	@Pattern(regexp=".+@.+\\.[a-z]+", message = "Please write a valid email")
	private String email;
}
