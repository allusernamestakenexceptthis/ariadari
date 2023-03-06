/**
 * This file exports utility common functions to be used throughout the application.
 *
 * Usage:
 * import { XYZ } from '@/helpers/common.js';
 * const XYZ = XYZ('hello-world');
 *
 */

/**
 * This function translates a string to the current language.
 * currently placeholder function, will be replaced with a real translation function.
 *
 * @param {string} value
 * @returns {string}
 *
 */

export function _tr (value) {
  return value
}

/**
 * This function formats a date to the current locale.
 *
 * @param {string} value
 * @returns {string} formatted date
 */
export function formatDate (value) {
  if (value) {
    const date = new Date(value)
    const locale = navigator.language
    const options = { dateStyle: 'medium', timeStyle: 'short' }
    const formattedDate = new Intl.DateTimeFormat(locale, options).format(date)
    return formattedDate
  }
}
