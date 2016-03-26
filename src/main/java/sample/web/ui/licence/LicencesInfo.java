package sample.web.ui.licence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LicencesInfo {

	private String lmid;

	private List<Licence> licences = new ArrayList<>();

	private List<String> profiles = Arrays.asList("eee", "zzzz");

	private String selectedProfile;

	public LicencesInfo(List<Licence> licences) {
		this.licences = licences;
	}

	public void reOrder() {
		Collections.sort(licences, new Comparator<Licence>() {

			@Override
			public int compare(Licence o1, Licence o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
	}

}
