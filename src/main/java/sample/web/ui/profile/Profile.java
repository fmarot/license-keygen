package sample.web.ui.profile;

import java.util.List;

import sample.web.ui.licence.Licence;

public interface Profile {
	void apply(List<Licence> licences);

	String getName();
}
