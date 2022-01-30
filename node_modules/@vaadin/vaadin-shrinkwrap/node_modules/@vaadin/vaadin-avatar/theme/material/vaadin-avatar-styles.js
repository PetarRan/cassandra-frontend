import '@vaadin/vaadin-material-styles/color.js';
import '@vaadin/vaadin-material-styles/typography.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';

const $_documentContainer = html`<dom-module id="material-avatar" theme-for="vaadin-avatar">
  <template>
    <style>
      :host {
        --vaadin-avatar-size: 2.25rem;
        color: var(--material-secondary-text-color);
        background-color: var(--material-secondary-background-color);
        border-radius: 50%;
        cursor: default;
        outline: none;
        user-select: none;
        -webkit-tap-highlight-color: transparent;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
      }

      :host([has-color-index]) {
        color: var(--material-primary-contrast-color);
      }

      :host([focus-ring]) {
        border-color: var(--material-primary-color);
      }

      [part="icon"],
      [part="abbr"] {
        fill: currentColor;
      }

      [part="abbr"] {
        font-family: var(--material-font-family);
        font-size: 3em;
        font-weight: 500;
      }
    </style>
  </template>
</dom-module>`;

document.head.appendChild($_documentContainer.content);
