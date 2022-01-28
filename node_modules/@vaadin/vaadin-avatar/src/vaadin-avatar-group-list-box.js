import { ListBoxElement } from '@vaadin/vaadin-list-box/src/vaadin-list-box.js';
/**
 * The vaadin-avatar-group-list-box element.
 *
 * @extends PolymerElement
 */
class AvatarGroupListBoxElement extends ListBoxElement {
  static get is() {
    return 'vaadin-avatar-group-list-box';
  }
}

customElements.define(AvatarGroupListBoxElement.is, AvatarGroupListBoxElement);
