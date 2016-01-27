/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licenced under the Apache Licence, Version 2.0 (the "Licence");
 * you may not use this file except in compliance with the Licence.
 * You may obtain a copy of the Licence at
 *
 *      http://www.apache.org/licences/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */

package sample.web.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

@SpringBootApplication
public class SampleWebUiApplication {

	@Bean
	public MessageRepository messageRepository() {
		return new InMemoryMessageRepository();
	}

	@Bean
	public Converter<String, Message> messageConverter() {
		return new Converter<String, Message>() {

			@Override
			public Message convert(String id) {
				Message findMessage = messageRepository().findMessage(Long.valueOf(id));
				return findMessage;
			}
		};
	}

	@Bean
	public LicenceRepository licenceRepository() {
		return new InMemoryLicenceRepository();
	}

	@Bean
	public Converter<String, Licence> licenceConverter() {
		return new Converter<String, Licence>() {

			@Override
			public Licence convert(String id) {
				Licence findMessage = licenceRepository().findLicence(Long.valueOf(id));
				return findMessage;
			}
		};
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);
	}

}
