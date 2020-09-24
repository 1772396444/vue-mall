<template>
    <component v-bind="linkProps(to)">
        <slot/>
    </component>
</template>

<script>
export default {
    props: {
        to: {
            type: String,
            required: true
        }
    },
    methods: {
        linkProps(url) {
            return {
                is: 'router-link',
                to: url,
            }
            // return {
            //     is: 'a',
            //     href: url,
            //     target: '_blank',
            //     rel: 'noopener'
            // }
        }
    }
}
</script>
