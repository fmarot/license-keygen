package sample.web.ui;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryLicenceRepository implements LicenceRepository {

	private static AtomicLong					counter		= new AtomicLong();

	private final ConcurrentMap<Long, Licence>	licences	= new ConcurrentHashMap<Long, Licence>();

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
