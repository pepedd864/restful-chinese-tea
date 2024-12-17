import {createCategory, deleteCategory, getAllCategory, updateCategory} from "../../js/apis/category.js";
import {transformFileToBuffer} from "../../js/utils/utils.js";
import {uploadFile} from "../../js/apis/file.js";

function getListTitle() {
  return `
  <tr>
    <th>分类图标</th>
    <th>分类编号</th>
    <th>分类标题</th>
    <th>操作</th>
</tr>
`
}

function getListItem(item) {
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

export async function generateCategoryList() {
  const categoryList = $('#app .category-list table')

  const res = await getAllCategory()
  if (res.data) {
    const listTitle = getListTitle()
    const listContent = res.data.map(item => getListItem(item))
    categoryList.html(listTitle + listContent)
  }
}

export function listItemClick() {
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
      // 如果只有一行，不允许删除
      if ($('#app .category-list table').find('tr').length === 2) {
        alert('至少保留一个分类')
        return
      }
      const result = confirm('确定删除？')
      if (result) {
        const res = await deleteCategory(id)
        if (res.code === 0) {
          row.remove();
          await window.parent.generateMenus()
        }
      }
    }
  });
}

export function formInfoClick() {
  $('#app .edit-info button').click(async function () {
    const editInfo = $('#app .edit-info');
    const id = editInfo.find('input:eq(0)').val()
    const num = editInfo.find('input:eq(1)').val();
    const title = editInfo.find('input:eq(2)').val();
    const data = {
      id,
      num,
      title
    }
    if ($(this).hasClass('refresh-btn')) {
      window.location.reload()
    }
    if ($(this).hasClass('add-btn')) {
      if (!num || !title) {
        alert('请填写编号和标题')
        return
      }
      await createCategory(data)
      await window.parent.generateMenus()
      window.location.reload()
    }
    if ($(this).hasClass('edit-btn')) {
      await updateCategory(data)
      await window.parent.generateMenus()
      window.location.reload()
    }
  })
}

export async function formIconClick() {
  $('#app .edit-icon button').click(async function () {
    const editIcon = $('#app .edit-icon');
    const id = editIcon.find('input[type="text"]').val();
    if ($(this).hasClass('upload-btn')) {
      const file = editIcon.find('input[type="file"]')[0].files[0];
      if (!file) {
        alert('请选择文件')
        return;
      }
      const buffer = await transformFileToBuffer(file)
      console.log(buffer)
      const {data: icon} = await uploadFile(file.name, buffer)
      if (!icon) return
      const data = {
        id,
        icon
      }
      const r = await updateCategory(data)
      if (!r.data) return
      editIcon.hide();
      $('#app .edit-info').show()
      await window.parent.generateMenus()
      await generateCategoryList()
    }
    if ($(this).hasClass('cancel-btn')) {
      editIcon.hide();
      $('#app .edit-info').show()
    }

  })
}
