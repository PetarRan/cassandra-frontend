import { html, LitElement, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-ordered-layout/vaadin-vertical-layout';
import '@vaadin/vaadin-select';
// @ts-ignore
import { applyTheme } from '../../generated/theme';



@customElement('history-view')
export class HistoryView extends LitElement {
  connectedCallback() {
    super.connectedCallback();
    // Apply the theme manually because of https://github.com/vaadin/flow/issues/11160
    applyTheme(this.renderRoot);
  }

  render() {
    return html`
      <main class="max-w-screen-lg mx-auto pb-l px-l">
        <vaadin-horizontal-layout class="items-center justify-between">
          <vaadin-vertical-layout>
            <h2 class="mb-0 mt-xl text-3xl">Welcome!</h2>
            <p class="mb-xl mt-0 text-secondary">Manage your Listings</p>
          </vaadin-vertical-layout>
          <vaadin-select label="Continent" id="searchPrimary1"></vaadin-select>
          <vaadin-select label="Country" id="searchSecondary1"></vaadin-select>
          <vaadin-text-field label="City" id="searchTernary1"></vaadin-text-field>
          <vaadin-button id="searchButton1"></vaadin-button>
        </vaadin-horizontal-layout>
        <ol class="gap-m grid list-none m-0 p-0" id="itemList1">
          <slot></slot>
        </ol>
      </main>
    `;
  }
}
