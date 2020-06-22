// question.js


$(document).ready(() => {
  // 获取 btn
  let replyButton = $('#reply')

  // 从 input 获取问题 id
  let questionId = $('input:hidden').val()

  // 获取回复内容
  let replyContent = $('#reply-content');

  replyButton.click(() => {
    $.ajax({
      url: '/comment',
      type: 'POST',
      contentType: "application/json",
      dataType: 'json',
      // json 字符串格式化
      data: JSON.stringify({
        'parentId': questionId,
        'type': 1,
        'content': replyContent.val()
      }),
      success: (data) => {
        if (data.code === 200) {
          // 发送成功 进行局部刷新
          // 清空 text-area
          replyContent.val('')
          replyContent.hide();
        } else if (data.code === 2005) {
          if (!confirm(data.message)) return
          window.open('https://github.com/login/oauth/authorize?client_id=8d5eabdc825de277497a&redirect_uri=http://localhost:8888/callback&scope=user&state=1')
          window.localStorage.setItem('closable', true)
        } else {
          alert(data.message)
        }
      }
    })
  })


})


