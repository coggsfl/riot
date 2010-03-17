/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.riotfamily.forms2.client;

import java.util.Locale;

import org.riotfamily.common.util.FormatUtils;
import org.springframework.context.MessageSource;
import org.w3c.dom.Element;


public class Html extends DomBuilder {

	private MessageSource messageSource;

	private Locale locale;
	
	private IdGenerator idGenerator;
	
	public Html(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
	
	protected Html(Element child, Html parent) {
		super(child, parent);
		this.idGenerator = parent.idGenerator;
	}
	
	public void setMessageKeyPrefix(String prefix) {
		
	}

	private String message(String defaultText) {
		return message(FormatUtils.toCssClass(defaultText), defaultText);
	}
	
	private String message(String labelKey, String defaultText) {
		if (messageSource == null) {
			return defaultText;
		}
		return messageSource.getMessage(labelKey, null, defaultText, locale);
	}
	
	public Html messageText(String defaultText) {
		return text(message(defaultText));
	}
	
	public Html messageAttr(String name, String defaultText) {
		return attr(name, message(defaultText));
	}
	
	public Html div() {
		return elem("div");
	}
	
	public Html div(String cssClass) {
		return div().cssClass(cssClass);
	}
	
	public Html label(String defaultText) {
		return label(defaultText, idGenerator.next());
	}
	
	public Html labelPrev(String defaultText) {
		return label(defaultText, idGenerator.prev());
	}
	
	protected Html label(String defaultText, String inputId) {
		return elem("label")
				.attr("for", inputId)
				.messageText(defaultText);
	}
	
	public Html input(String type, String value) {
		return elem("input")
			.id(idGenerator.inputId())
			.cssClass(type)
			.attr("type", type)
			.attr("value", value);
	}
	
	public Html input(String type, String name, String value) {
		return input(type, value).attr("name", name);
	}
	
	public Html hiddenInput(String name, String value) {
		return elem("input")
			.attr("type", "hidden")
			.attr("name", name)
			.attr("value", value);
	}
	
	public Html button(String defaultText) {
		return input("button", message(defaultText));
	}
	
	public Html submit(String defaultText) {
		return input("submit", message(defaultText));
	}
	
	public Html multipartForm(String action) {
		return elem("form")
			.attr("action", action)
			.attr("method", "POST")
			.attr("enctype", "multipart/form-data");
	}
	
	public Html propagate(String event, String handler) {
		return propagate(event, handler, "$F(this)");
	}
	
	public Html propagate(String event, String handler, String exp) {
		return attr("on" + event, String.format("riot.form.submitEvent(this, '%s', %s)", handler, exp));
	}
	
	public Html script(String script, Object... args) {
		return elem("script").text(String.format(script, args));
	}
	
	public Html id(String value) {
		return attr("id", value);
	}
	
	public Html cssClass(String value) {
		return attr("class", value);
	}
	
	public Html addClass(String className) {
		String attr = element().getAttribute("class");
		if (!attr.matches("\\b" + className + "\\b")) {
			attr += " " + className;
		}
		return cssClass(attr.trim());
	}
	
	public Html style(String value) {
		return attr("style", value);
	}
	
	public Html style(String format, Object... args) {
		return style(String.format(format, args));
	}
	
	// -----------------------------------------------------------------------
	// Polymorphic returns
	// -----------------------------------------------------------------------
	
	@Override
	protected Html createNested(Element child) {
		return new Html(child, this);
	}
	
	@Override
	public Html attr(String name, String value) {
		super.attr(name, value);
		return this;
	}

	@Override
	public Html elem(String name) {
		return (Html) super.elem(name);
	}
	
	@Override
	public Html text(String value) {
		return (Html) super.text(value);
	}
	
	@Override
	public Html cdata(String value) {
		return (Html) super.cdata(value);
	}

	@Override
	public Html up() {
		return (Html) super.up();
	}

	@Override
	public Html up(int n) {
		return (Html) super.up(n);
	}

}