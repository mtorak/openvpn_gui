<template>
  <div>
    <h1>OpenVpn Application</h1>
    <OpenVpnExecutorComponent></OpenVpnExecutorComponent>
  </div>
</template>

<script>
    import OpenVpnExecutorComponent from "./OpenVpnExecutorComponent";

    export default {
        name: "OpenVpnApp",
        components: {
            OpenVpnExecutorComponent
        }
    };
</script>

<style scoped>
</style>