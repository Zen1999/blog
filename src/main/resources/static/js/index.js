// index.js
$(document).ready(() => {
  // JSON.parse() 方法判断 是否为 'true' 字符串
  if (JSON.parse(window.localStorage.getItem('closable'))) {
    window.close();
    window.localStorage.removeItem('closable')
  }
})