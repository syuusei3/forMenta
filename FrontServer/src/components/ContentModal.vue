<template>
  <transition name="modal" appear>
    <div class="modal-overlay" @click.self="$emit('close')">
      <div class="modal-window">
        <div class="modal-content">
          <slot/>
        </div>
        <button @click="$emit('close')">close</button>
      </div>
    </div>
  </transition>
</template>

