package sample.web.ui.profile;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sample.web.ui.licence.Licence;

@Component
@EqualsAndHashCode
public class V2_4Profile implements Profile {

	@Getter
	private String name = "v 2.4";

	@Override
	public void apply(List<Licence> licences) {
		for (Licence l : licences) { // TODO FAKE !
			if (l.getKey().contains("4")) // TODO FAKE !
				l.setResearch(true); // TODO FAKE !
		}
	}

}
