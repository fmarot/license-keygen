package sample.web.ui.licence;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Licence {

	private Long id;

	@NotEmpty(message = "key is required.")
	private String key;

	@NotEmpty(message = "cmsCode is required.")
	private String cmsCode;

	private final Calendar created = Calendar.getInstance();

	private boolean floating = false;

	private boolean research = false;

	public Licence(Long id, String key, String cmsCode) {
		this.id = id;
		this.key = key;
		this.cmsCode = cmsCode;
	}

}
