package sample.web.ui.licence;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Licences {

	private List<Licence> licences = new ArrayList<>();

	public Licences(List<Licence> licences) {
		this.licences = licences;
	}

}
