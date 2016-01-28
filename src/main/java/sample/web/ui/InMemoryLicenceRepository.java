package sample.web.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InMemoryLicenceRepository implements LicenceRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, Licence> licences = new ConcurrentHashMap<Long, Licence>();

	public InMemoryLicenceRepository() {
		Properties prop = new Properties();
		try (InputStream input = this.getClass().getResourceAsStream("/licences.properties")) {
			prop.load(input);
		} catch (IOException e) {
			log.error("", e);
		}
		createInitialModel(prop);
	}

	private void createInitialModel(Properties prop) {
		List<Licence> licences = prop.entrySet().stream().map(elt -> createDefaultLicence((String) elt.getKey(), (String) elt.getValue())).collect(Collectors.toList());
		for (Licence lic : licences) {
			this.licences.put(lic.getId(), lic);
		}
	}

	private Licence createDefaultLicence(String key, String cmsCode) {
		Long id = counter.incrementAndGet();
		return new Licence(id, key, cmsCode);
	}

	@Override
	public Iterable<Licence> findAll() {
		return licences.values();
	}

	@Override
	public Licence save(Licence licence) {
		Long id = licence.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			licence.setId(id);
		}
		licences.put(id, licence);
		return licence;
	}

	@Override
	public Licence findLicence(Long id) {
		return licences.get(id);
	}

	@Override
	public void deleteLicence(Long id) {
		licences.remove(id);
	}

}
