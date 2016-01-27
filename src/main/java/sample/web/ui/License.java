package sample.web.ui;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class License {

	private Long		id;

	@NotEmpty(message = "Message is required.")
	private String		text;

	@NotEmpty(message = "Summary is required.")
	private String		summary;

	private Calendar	created	= Calendar.getInstance();

}
