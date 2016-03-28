package sample.web.ui.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import sample.web.ui.licence.Licence;

@Slf4j
public class LicenceRepository {

	private List<Licence> licences;

	public LicenceRepository() {
		Properties prop = new Properties();
		try (InputStream input = this.getClass().getResourceAsStream("/licences.properties")) {
			prop.load(input);
		} catch (IOException e) {
			log.error("", e);
		}
		// @formatter:off
		licences = prop.entrySet()
				.stream()
				.map(elt -> new Licence((String) elt.getKey(), (String) elt.getValue()))
				.collect(Collectors.toList());
		// @formatter:on
	}

	public List<Licence> findAll() {
		return licences;
	}
}