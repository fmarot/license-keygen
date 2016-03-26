package sample.web.ui.licence;

import java.util.Calendar;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Licence {

	private boolean selected;

	private String key;

	private String cmsCode;

	private final Calendar created = Calendar.getInstance();

	private boolean floating = false;

	private boolean research = false;

	public Licence(String key, String cmsCode) {
		this.key = key;
		this.cmsCode = cmsCode;
	}

}
