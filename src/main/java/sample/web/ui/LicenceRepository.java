package sample.web.ui;

public interface LicenceRepository {

	Iterable<Licence> findAll();

	Licence save(Licence message);

	Licence findLicence(Long id);

	void deleteLicence(Long id);

}
