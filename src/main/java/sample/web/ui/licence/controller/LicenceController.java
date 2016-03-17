package sample.web.ui.licence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sample.web.ui.licence.InMemoryLicenceRepository;
import sample.web.ui.licence.Licences;

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
		Licences licences = licenceRepository.findAll();
		return new ModelAndView("licences/list", "licences", licences);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView generate(Licences licences, BindingResult result, RedirectAttributes redirect) {
		// if (result.hasErrors()) {
		// return new ModelAndView("licences/form", "formErrors", result.getAllErrors());
		// }
		// licence = licenceRepository.save(licence);
		// Iterable<Licence> licences = licenceRepository.findAll();
		redirect.addFlashAttribute("globalMessage", "coucou toto");
		return new ModelAndView("licences/list", "licences", licences);
	}

}
