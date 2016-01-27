package sample.web.ui;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryLicenseRepository implements LicenseRepository {

	private static AtomicLong					counter		= new AtomicLong();

	private final ConcurrentMap<Long, License>	licenses	= new ConcurrentHashMap<Long, License>();

	@Override
	public Iterable<License> findAll() {
		return licenses.values();
	}

	@Override
	public License save(License license) {
		Long id = license.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			license.setId(id);
		}
		licenses.put(id, license);
		return license;
	}

	@Override
	public License findLicense(Long id) {
		return licenses.get(id);
	}

	@Override
	public void deleteLicense(Long id) {
		licenses.remove(id);
	}

}
