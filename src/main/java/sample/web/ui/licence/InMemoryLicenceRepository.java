package sample.web.ui.licence;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InMemoryLicenceRepository {

	private Licences licences;

	public InMemoryLicenceRepository() {
		Properties prop = new Properties();
		try (InputStream input = this.getClass().getResourceAsStream("/licences.properties")) {
			prop.load(input);
		} catch (IOException e) {
			log.error("", e);
		}
		// @formatter:off
		List<Licence> list = prop.entrySet()
				.stream()
				.map(elt -> new Licence((String) elt.getKey(), (String) elt.getValue()))
				.collect(Collectors.toList());
		// @formatter:on
		licences = new Licences(list);
	}

	public Licences findAll() {
		return licences;
	}
}