package sample.web.ui.licence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import sample.web.ui.licence.InMemoryLicenceRepository;
import sample.web.ui.licence.LicencesInfo;

@Slf4j
@Controller
@RequestMapping("/")
public class LicenceController {

	private final InMemoryLicenceRepository licenceRepository;

	@Autowired
	public LicenceController(InMemoryLicenceRepository licenceRepository) {
		this.licenceRepository = licenceRepository;
	}

	@RequestMapping // GET by default
	public ModelAndView list() {
		LicencesInfo licences = licenceRepository.findAll();
		licences.reOrder();
		return new ModelAndView("licences/list", "licences", licences);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView generate(LicencesInfo licences, BindingResult result, RedirectAttributes redirect) {
		redirect.addFlashAttribute("globalMessage", "coucou toto");

		// List<Licence> licences = licenceInput.getLicences();
		log.warn("lmid = {}", licences.getLmid());
		licences.reOrder();
		return new ModelAndView("licences/list", "licences", licences);
	}

}
