package sample.web.ui.profile;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sample.web.ui.licence.Licence;

@Component
@EqualsAndHashCode
public class DefaultProfile implements Profile {
	@Getter
	private String name = "default";

	@Override
	public void apply(List<Licence> licences) {
		// do nothing
	}

}
