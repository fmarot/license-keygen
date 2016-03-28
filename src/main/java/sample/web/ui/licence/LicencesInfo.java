package sample.web.ui.licence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import sample.web.ui.profile.Profile;

@Data
@NoArgsConstructor
public class LicencesInfo {

	private String lmid;

	private List<Licence> licences = new ArrayList<>();

	private List<Profile> profiles = new ArrayList<>();

	private Profile selectedProfile;

	public LicencesInfo(List<Licence> licences, List<Profile> profiles, Profile selectedProfile) {
		this.licences = licences;
		this.profiles = profiles;
		this.selectedProfile = selectedProfile;
		reOrder();
	}

	private void reOrder() {
		Collections.sort(licences, new Comparator<Licence>() {

			@Override
			public int compare(Licence o1, Licence o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
	}

}
