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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;

import lombok.extern.slf4j.Slf4j;
import sample.web.ui.licence.InMemoryLicenceRepository;
import sample.web.ui.message.InMemoryMessageRepository;
import sample.web.ui.message.Message;
import sample.web.ui.message.MessageRepository;

@Slf4j
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
	public InMemoryLicenceRepository licenceRepository() {
		return new InMemoryLicenceRepository();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);
		String url = "http://127.0.0.1:8080/";
		openWebBrowser(url);
	}

	private static void openWebBrowser(String url) throws IOException {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI(url));
			} else {
				Runtime.getRuntime().exec("xdg-open " + url);
			}
			log.info("Url {} opened in your browser", url);
		} catch (Exception e) {
			log.info("Unable to Open web page in browser", e);
		}
	}

}
