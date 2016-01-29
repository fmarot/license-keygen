/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licenced under the Apache Licence, Version 2.0 (the "Licence"); you may not use this file except in compliance with
 * the Licence. You may obtain a copy of the Licence at
 *
 *      http://www.apache.org/licences/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the Licence is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */

package sample.web.ui.message;

/**
 * @author Rob Winch
 */
public interface MessageRepository {

	Iterable<Message> findAll();

	Message save(Message message);

	Message findMessage(Long id);

	void deleteMessage(Long id);

}
