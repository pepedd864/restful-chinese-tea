var menus = []

function generateMenus(contextPath, data) {
  menus = []
  menus.push({
    title: '分类管理',
    num: 'manage',
    icon: '/api/file/display/default-icon.png'
  }, ...data)
  const btns = menus.map((item) => {
    return '<div class="btn" data-page="' + (item.num === 'manage' ? 'category' : 'exhibits?num=' + item?.num) + '">' +
      '<img src="' + contextPath + item.icon + '"/>' +
      '<div class="text">' + item.title + '</div>' +
      '</div>'
  })
  $('.wrapper .side').html(btns)
  const elems = $(".wrapper .side .btn")
  elems.each(function (index, elem) {
    const toPage = $(elem).data("page");
    $(elem).click(() => {
      setMenuActive(elems, index)
      changePage(toPage);
    });
  });
}


$.ajaxSetup({
  beforeSend: function (xhr, settings) {
    // $('#loading').show();
    return true;
  },
  complete: function (xhr, textStatus) {
    // $('#loading').hide();
    xhr.then(function (e) {
      if (e.code === 0) return
      alert(e.description)
    })
    xhr.fail(function (e) {
      alert('网络错误，请稍后再试')
    })
  }
});
