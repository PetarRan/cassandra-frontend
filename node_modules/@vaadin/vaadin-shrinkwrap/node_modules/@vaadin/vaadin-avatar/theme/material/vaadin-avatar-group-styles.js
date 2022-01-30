import '@vaadin/vaadin-material-styles/color.js';
import '@vaadin/vaadin-material-styles/mixins/menu-overlay.js';
import { html } from '@polymer/polymer/lib/utils/html-tag.js';

const $_documentContainer = html`<dom-module id="material-avatar-group-overlay" theme-for="vaadin-avatar-group-overlay">
  <template>
    <style include="material-menu-overlay">
      [part="overlay"] {
        outline: none;
      }
    </style>
  </template>
</dom-module><dom-module id="material-avatar-group-list-box" theme-for="vaadin-avatar-group-list-box">
  <template>
    <style>
      [part="items"] ::slotted(vaadin-item[theme="avatar-group-item"]) {
        padding: 8px;
        padding-right: 24px;
      }

      :host([dir="rtl"]) [part="items"] ::slotted(vaadin-item[theme="avatar-group-item"]) {
        padding: 8px;
        padding-left: 24px;
      }

      [part="items"] ::slotted(vaadin-item[theme="avatar-group-item"])::before {
        display: none;
      }
    </style>
  </template>
</dom-module><dom-module id="material-avatar-group-item" theme-for="vaadin-item">
  <template>
    <style>
      :host([theme="avatar-group-item"]) [part="content"] {
        display: flex;
        align-items: center;
      }

      :host([theme="avatar-group-item"]:not([dir="rtl"])) ::slotted(vaadin-avatar) {
        margin-right: 8px;
      }

      :host([theme="avatar-group-item"][dir="rtl"]) ::slotted(vaadin-avatar) {
        margin-left: 8px;
      }
    </style>
  </template>
</dom-module>`;

document.head.appendChild($_documentContainer.content);
