<template>
  <router-view></router-view>
</template>

<style lang="scss">

.container{
  max-width: 1200px;
  min-width: 1200px;
  margin: auto;

}

.router-fade-enter-active, .router-fade-leave-active {
  transition: opacity .3s;
}

.router-fade-enter, .router-fade-leave-active {
  opacity: 0;
}
</style>
