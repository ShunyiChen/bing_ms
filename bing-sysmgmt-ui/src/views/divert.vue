<template></template>
<script setup>
const router = useRouter();
const redirect = ref(undefined);
const route = useRoute();

watch(route, (newRoute) => {
    redirect.value = newRoute.query && newRoute.query.redirect;
}, { immediate: true });

onMounted(() => {
    window.open("http://localhost:8080/auth/aad/launch?appName=legaldb&redirect="+redirect.value, "_self");
});
</script>