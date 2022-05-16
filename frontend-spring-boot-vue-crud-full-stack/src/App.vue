<template>
  <div class="container">
    <OpenVpnApp />
  </div>
</template>

<script>
import OpenVpnApp from "./components/OpenVpnApp.vue";

export default {
  name: "OpenVpnGui",
  components: {
    OpenVpnApp,
  },
};
</script>

<style>
@import url(https://unpkg.com/bootstrap@4.1.0/dist/css/bootstrap.min.css);
</style>
