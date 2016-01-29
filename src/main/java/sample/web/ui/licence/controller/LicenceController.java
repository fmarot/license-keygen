package sample.web.ui.licence.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sample.web.ui.licence.Licence;
import sample.web.ui.licence.LicenceRepository;

@Controller
@RequestMapping("/")
public class LicenceController {

	private final LicenceRepository licenceRepository;

	@Autowired
	public LicenceController(LicenceRepository licenceRepository) {
		this.licenceRepository = licenceRepository;
	}

	@RequestMapping
	public ModelAndView list() {
		Iterable<Licence> licences = licenceRepository.findAll();
		return new ModelAndView("licences/list", "licences", licences);
	}

	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Licence licence) {
		return new ModelAndView("licences/view", "licence", licence);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Licence licence) {
		return "licences/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Licence licence, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("licences/form", "formErrors", result.getAllErrors());
		}
		licence = licenceRepository.save(licence);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new licence");
		return new ModelAndView("redirect:/{licence.id}", "licence.id", licence.getId());
	}

	@RequestMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		licenceRepository.deleteLicence(id);
		Iterable<Licence> licences = licenceRepository.findAll();
		return new ModelAndView("licences/list", "licences", licences);
	}

	/**
	 * The @PathVariable annotation need a Converter<String, Licence> to be registered
	 * in the Context to be able to convert a String (the {id}) to a Licence.
	 */
	@RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
	public ModelAndView modifyForm(@PathVariable("id") Licence licence) {
		return new ModelAndView("licences/form", "licence", licence);
	}

}
