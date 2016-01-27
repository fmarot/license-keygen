/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package sample.web.ui.mvc;

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

import sample.web.ui.LicenseRepository;
import sample.web.ui.License;

@Controller
@RequestMapping("/")
public class LicenceController {

	private final LicenseRepository	licenseRepository;

	@Autowired
	public LicenceController(LicenseRepository licenceRepository) {
		this.licenseRepository = licenceRepository;
	}

	@RequestMapping
	public ModelAndView list() {
		Iterable<License> licences = licenseRepository.findAll();
		return new ModelAndView("licences/list", "licences", licences);
	}

	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") License licence) {
		return new ModelAndView("licences/view", "licence", licence);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(@ModelAttribute License license) {
		return "licenses/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid License license, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("licenses/form", "formErrors", result.getAllErrors());
		}
		license = licenseRepository.save(license);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new license");
		return new ModelAndView("redirect:/{license.id}", "license.id", license.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@RequestMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		licenseRepository.deleteLicense(id);
		Iterable<License> licenses = licenseRepository.findAll();
		return new ModelAndView("licenses/list", "licenses", licenses);
	}

	@RequestMapping(value = "modify/{id}", method = RequestMethod.GET)
	public ModelAndView modifyForm(@PathVariable("id") License license) {
		return new ModelAndView("licenses/form", "license", license);
	}

}
