package sample.web.ui;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Licence {

	private Long id;

	@NotEmpty(message = "Message is required.")
	private String text;

	@NotEmpty(message = "Summary is required.")
	private String summary;

	private final Calendar created = Calendar.getInstance();

}
