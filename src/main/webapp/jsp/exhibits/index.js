import {createExhibits, deleteExhibits, getByCategoryId, updateExhibits} from '../../js/apis/exhibits.js'
import {getCategoryById} from "../../js/apis/category.js";
import {transformFileToBuffer} from "../../js/utils/utils.js";
import {uploadFile} from "../../js/apis/file.js";

export async function initExhibitsList() {
  const statusEnum = {
    'list': '列表',
    'add': '添加展品',
    'edit': '修改展品',
    'img': '上传图片'
  }
  let currentStatus = statusEnum.list
  const {searchParams} = new URL(window.parent.location)
  const categoryId = searchParams.get('categoryId')
  // 查询对应的category
  const ctg = await getCategoryById(categoryId)
  const editForm = $('#app .edit-form')
  const editImg = $('#app .edit-image')
  const exhibit = $('#app .wrapper .content .exhibits')
  const {data} = await getByCategoryId(categoryId)
  let dataItem
  if (data && data.length) {
    // 取出一项
    dataItem = data[0]
    if (ctg.code !== 0) return

    // 设置info
    function setInfo() {
      const info = exhibit.find('.info')
      info.find('.id').text(`ID:${dataItem.id}`)
      info.find('.num').text(`编号:${dataItem.num}`)
      info.find('.category').text(ctg.data.title)
      // 设置详细信息
      exhibit.find('.img').attr('src', contextPath + dataItem.img)
      exhibit.find('.title').text(dataItem.title)
      exhibit.find('.desc').text(dataItem.description)
    }

    setInfo()
    // 设置上下页
    // 上一页
    $('.side .prev-btn').click(function () {
      const index = data.findIndex(item => item.id === dataItem.id) - 1
      if (index >= 0 && currentStatus === statusEnum.list) {
        dataItem = data[index]
        // 设置info
        setInfo()
      }
    })
    // 下一页
    $('.side .next-btn').click(function () {
      const index = data.findIndex(item => item.id === dataItem.id) + 1
      if (index < data.length && currentStatus === statusEnum.list) {
        dataItem = data[index]
        // 设置info
        setInfo()
      }
    })
  }
  // 设置按钮
  const btnGroup = exhibit.find('.btn-group')

  function setEditForm(status) {
    if (status === statusEnum.add) {
      editForm.find('input#num').val('')
      editForm.find('input#title').val('')
      editForm.find('textarea#desc').val('')
      editForm.find('.btn-group .edit-btn').text('添加记录')
    } else if (status === statusEnum.edit) {
      editForm.find('input#num').val(dataItem.num)
      editForm.find('input#title').val(dataItem.title)
      editForm.find('textarea#desc').val(dataItem.description)
      editForm.find('.btn-group .edit-btn').text('修改记录')
    }
  }

  $(btnGroup).on('click', '.btn', async function () {
    if ($(this).hasClass('add-btn')) {
      currentStatus = statusEnum.add
      exhibit.hide()
      editForm.show()
      setEditForm(statusEnum.add)
    }
    if ($(this).hasClass('edit-btn')) {
      if (!data) {
        alert('先新建一个展品')
        return
      }
      currentStatus = statusEnum.edit
      exhibit.hide()
      editForm.show()
      // 设置表单值
      setEditForm(statusEnum.edit)
    }
    if ($(this).hasClass('del-btn')) {
      if (!data) {
        alert('先新建一个展品')
        return
      }
      confirm('确定删除?') && await deleteExhibits(dataItem.id) && window.location.reload()
    }
    if ($(this).hasClass('img-btn')) {
      currentStatus = statusEnum.img
      exhibit.hide()
      editImg.show()
      editImg.find('img').attr('src', contextPath + dataItem.img)
    }
  })
  editForm.find('span#category').text(ctg.data.title)
  $(editForm).on('click', '.btn', async function () {
    if ($(this).hasClass('cancel-btn')) {
      currentStatus = statusEnum.list
      editForm.hide()
      exhibit.show()
    }
    if ($(this).hasClass('edit-btn')) {
      const num = $('input#num').val()
      const title = $('input#title').val()
      const description = $('textarea#desc').val()
      const req = {num, title, description, categoryId}
      if (dataItem?.id) {
        await updateExhibits({
          id: dataItem.id,
          ...req
        })
      } else {
        await createExhibits(req)
      }
      window.location.reload()
    }
  })
  $(editImg).on('click', '.btn', async function () {
    if ($(this).hasClass('cancel-btn')) {
      currentStatus = statusEnum.list
      editImg.hide()
      exhibit.show()
    }
    if ($(this).hasClass('upload-btn')) {
      const file = editImg.find('input[type="file"]')[0].files[0];
      if (!file) {
        alert('请选择文件')
        return;
      }
      const buffer = await transformFileToBuffer(file)
      const {data: img} = await uploadFile(file.name, buffer)
      if (!img) return
      const data = {
        id: dataItem.id,
        img
      }
      const {data: r} = await updateExhibits(data)
      if (!r) return
      editImg.hide()
      exhibit.show()
      window.location.reload();
    }
  })
}
