package sample.web.ui.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import sample.web.ui.profile.DefaultProfile;
import sample.web.ui.profile.Profile;

@Slf4j
@Repository
public class ProfileRepository {

	@Autowired
	private List<Profile> profiles;

	public Profile getProfileByName(String profileName) {
		for (Profile p : profiles) {
			if (p.getName().equals(profileName)) {
				return p;
			}
		}
		log.error("unknow Profile {}", profileName);
		return new DefaultProfile();
	}

	public List<Profile> fetchAllProfiles() {
		return profiles;
	}

	public Profile getDefaultProfile() {
		return new DefaultProfile();
	}
}
