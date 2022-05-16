<template>
  <div class="form-group">
    <button
      type="button"
      class="btn btn-lg btn-block"
      v-bind:class="{
        'btn-primary': !showOutputArea,
        'btn-warning': showOutputArea,
      }"
      v-on:click="setStatus()"
    >
      {{ startButtonText }}
    </button>
  </div>

  <div class="form-group" v-show="showOutputArea">
    <div class="input-group">
      <span class="input-group-text font-weight-bold">İşlem <br />çıktıları</span>
      <textarea
        disabled
        v-show="showOutputArea"
        v-model="outputAreaContent"
        class="form-control z-depth-1"
        id="outputArea"
        rows="10"
      ></textarea>
    </div>
  </div>

  <div class="form-group" v-show="showOutputArea">
    <div class="input-group mb-2">
      <input
        type="text"
        v-model="selectionValue"
        class="form-control"
        placeholder="Seçiminiz"
        aria-label="Seçiminiz"
        aria-describedby="button-addon2"
        v-bind:disabled="submitSelectionDisabled"
      />
      <button
        class="btn btn-outline-secondary"
        type="button"
        id="submitSelectionButton"
        v-on:click="executeSelection()"
        v-bind:disabled="submitSelectionDisabled"
      >
        Gönder
      </button>
    </div>
  </div>

  <div class="form-group">
    <span id="feedbackMessageSpan">{{ this.feedbackMessage }}</span>
  </div>
</template>

<script>
import OpenVpnService from "../service/OpenVpnService";

export default {
  name: "OpenVpnExecutorComponent",
  data() {
    return {
      showOutputArea: false,
      startButtonText: "Başlat",
      outputAreaContent: "",
      selectionValue: "",
      submitSelectionDisabled: false,
      feedbackMessage: "",
    };
  },
  updated() {
    this.scrollToBottom();
  },
  methods: {
    setStatus() {
      if (this.showOutputArea) {
        this.stopOpenVpn();
      } else {
        this.startOpenVpn();
      }
    },
    startOpenVpn() {
      let self = this;
      OpenVpnService.callStartOpenVpn()
        .then((response) => {
          self.outputAreaContent = response.data.responseText;
          self.showOutputArea = true;
          self.startButtonText = "Temizle";
          self.feedbackMessage = "";
        })
        .catch(function (error) {
          self.feedbackMessage = error.toJSON().message;
        });
    },
    stopOpenVpn() {
      let self = this;
      OpenVpnService.callStopOpenVpn()
        .then((response) => {
          self.outputAreaContent = response.data.responseText;
          self.showOutputArea = false;
          self.startButtonText = "Başlat";
          // self.outputAreaContent = [];
          self.selectionValue = "";
          self.feedbackMessage = "";
        })
        .catch(function (error) {
          self.feedbackMessage = error.toJSON().message;
        });
    },
    executeSelection() {
      let self = this;

      if (self.selectionValue == "") {
        self.feedbackMessage = "Bir değer girmeniz gerekmektedir!";
        return;
      }
      self.feedbackMessage = "";

      self.submitSelectionDisabled = true;
      OpenVpnService.callExecuteSelection(self.selectionValue)
        .then((response) => {
          self.outputAreaContent = "\n\n" + response.data.responseText;
          self.submitSelectionDisabled = false;
          self.feedbackMessage = "";
        })
        .catch(function (error) {
          self.feedbackMessage = error.toJSON().message;
        });
    },
    scrollToBottom() {
      const element = document.getElementById("outputArea");

      let scrollOptions = {
        top: element.scrollHeight,
        behavior: "smooth",
      };

      element.scrollTo(scrollOptions);
    },
  },
};
</script>

<style>
textarea#outputArea {
  border: solid 2px;
}
</style>
