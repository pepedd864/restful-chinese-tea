import {createCategory, deleteCategory, updateCategory} from "../../js/apis/category.js";

export function getListItem(contextPath, item) {
  return `
<tr data-id="${item.id}">
<td>
<img src="${contextPath + item.icon}" />
</td>
<td>
${item.num}
</td>
<td>
${item.title}
</td>
<td>
<button class="btn upload-btn">上传</button>
<button class="btn edit-btn">编辑</button>
<button class="btn delete-btn">删除</button>
</td>
</tr>
`
}

export function listItemClick(contextPath) {
  $(document).on('click', '.category-list .btn', async function () {
    const row = $(this).closest('tr');
    const id = row.data('id');
    const num = row.find('td:eq(1)').text();
    const title = row.find('td:eq(2)').text();

    if ($(this).hasClass('upload-btn')) {
      const editIcon = $('#app .edit-icon');
      editIcon.find('input[type="text"]').val(id);
      editIcon.show();
      $('#app .edit-info').hide();
    }

    if ($(this).hasClass('edit-btn')) {
      const editInfo = $('#app .edit-info');
      editInfo.find('input:eq(0)').val(id);
      editInfo.find('input:eq(1)').val(num);
      editInfo.find('input:eq(2)').val(title);
      editInfo.show();
      $('#app .edit-icon').hide();
    }

    if ($(this).hasClass('delete-btn')) {
      const result = confirm('确定删除？')
      if (result) {
        const res = await deleteCategory(contextPath, id)
        if (res.code === 0) {
          row.remove();
        }
      }
    }
  });
}

export function formInfoClick(contextPath) {
  $('#app .edit-info button').click(function () {
    const editInfo = $('#app .edit-info');
    const id = editInfo.find('input:eq(0)').val()
    const num = editInfo.find('input:eq(1)').val();
    const title = editInfo.find('input:eq(2)').val();
    const data = {
      id,
      num,
      title
    }
    console.log($(this))
    if ($(this).hasClass('add-btn')) {
      createCategory(contextPath, data)
      window.parent.generateMenus(contextPath)
      // TODO
    }
    if ($(this).hasClass('edit-btn')) {
      updateCategory(contextPath, data)
      window.parent.generateMenus(contextPath)
      // TODO
    }
  })
}
