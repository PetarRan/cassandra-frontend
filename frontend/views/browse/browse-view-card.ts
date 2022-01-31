import { html, LitElement, customElement } from 'lit-element';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-ordered-layout/vaadin-vertical-layout';
import '@vaadin/vaadin-select';



@customElement('browse-view-card')
export class StubViewCard extends LitElement {
  createRenderRoot() {
    // Do not use a shadow root
    return this;
  }

  render() {
    return html`<li class="bg-contrast-5 flex flex-col items-start p-m rounded-l">
    <p class="my-m text-secondary" id="codePrimary"></p>
      <div
        class="bg-contrast flex items-center justify-center mb-m overflow-hidden rounded-m w-full"
        style="height: 160px;">
        <img id="image" class="w-full" />
      </div>
      <span class="text-xl font-semibold" id="header"></span>
      <span class="text-s text-secondary" id="continent"></span>
      <span class="text-s text-secondary" id="country"></span>
      <span class="text-s text-secondary" id="city"></span>
      <p class="my-m" id="text"></p>
      <vaadin-button id="badge3"></<vaadin-button>

    </li> `;
  }
}
