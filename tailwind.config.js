const defaultTheme = require('tailwindcss/defaultTheme')

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./**/*.{html,css,clj,cljs}"],
  theme: {
    extend: {},
  },
  plugins: [require('@tailwindcss/forms')],
}
