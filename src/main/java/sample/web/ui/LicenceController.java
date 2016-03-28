package sample.web.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import sample.web.ui.licence.Licence;
import sample.web.ui.licence.LicencesInfo;
import sample.web.ui.profile.Profile;
import sample.web.ui.repository.LicenceRepository;
import sample.web.ui.repository.ProfileRepository;

@Slf4j
@Controller
@RequestMapping("/")
public class LicenceController {

	private final LicenceRepository licenceRepository;

	private final ProfileRepository profileRepository;

	@Autowired
	public LicenceController(LicenceRepository licenceRepository, ProfileRepository profileRepository) {
		this.licenceRepository = licenceRepository;
		this.profileRepository = profileRepository;
	}

	@RequestMapping // GET by default
	public ModelAndView list() {
		List<Licence> licences = licenceRepository.findAll();
		List<Profile> profiles = profileRepository.fetchAllProfiles();
		Profile defaultProfile = profileRepository.getDefaultProfile();
		LicencesInfo info = new LicencesInfo(licences, profiles, defaultProfile);
		return new ModelAndView("licences/list", "licences", info);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView generate(LicencesInfo licences, BindingResult result, RedirectAttributes redirect) {
		// List<Licence> licences = licenceInput.getLicences();
		log.warn("lmid = {}", licences.getLmid());
		//

		List<Profile> profiles = licences.getProfiles();
		log.warn("profiles={}", profiles);

		Profile selectedProfile = licences.getSelectedProfile();
		log.warn("selectedProfile={}", selectedProfile);
		// log.warn("profile={}", profile);

		return new ModelAndView("licences/list", "licences", licences);
	}

}
