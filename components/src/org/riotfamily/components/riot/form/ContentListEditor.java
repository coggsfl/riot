/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * The Original Code is Riot.
 * 
 * The Initial Developer of the Original Code is
 * Neteye GmbH.
 * Portions created by the Initial Developer are Copyright (C) 2007
 * the Initial Developer. All Rights Reserved.
 * 
 * Contributor(s):
 *   Felix Gnass [fgnass at neteye dot de]
 * 
 * ***** END LICENSE BLOCK ***** */
package org.riotfamily.components.riot.form;

import org.riotfamily.components.model.ContentList;
import org.riotfamily.forms.element.collection.ListEditor;
import org.riotfamily.forms.element.collection.ListItem;

/**
 * @author Felix Gnass [fgnass at neteye dot de]
 * @since 7.0
 */
public class ContentListEditor extends ListEditor implements ContentEditor {

	public ContentListEditor() {
		setCollectionClass(ContentList.class);
	}
	
	protected ListItem createItem() {
		return new ContentListItem(this);
	}
	
}