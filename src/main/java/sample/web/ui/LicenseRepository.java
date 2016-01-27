package sample.web.ui;

public interface LicenseRepository {

	Iterable<License> findAll();

	License save(License message);

	License findLicense(Long id);

	void deleteLicense(Long id);

}
