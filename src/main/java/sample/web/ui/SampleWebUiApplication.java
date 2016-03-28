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
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import sample.web.ui.repository.LicenceRepository;

@Slf4j
@SpringBootApplication
public class SampleWebUiApplication implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	/**
	 * as we implement ApplicationListener<EmbeddedServletContainerInitializedEvent>, method
	 * will be called automatically
	 */
	@Override
	public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {
		int port = event.getEmbeddedServletContainer().getPort();
		String url = "http://127.0.0.1:" + port;
		openWebBrowser(url);
	}

	@Bean
	public LicenceRepository licenceRepository() {
		return new LicenceRepository();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SampleWebUiApplication.class, args);
	}

	private static void openWebBrowser(String url) {
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
