export function getAllCategory() {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: contextPath + '/api/category/get/All',
      type: 'get',
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        reject(error)
      }
    })
  })
}

export function getCategoryById(data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: contextPath + `/api/category/get/${data}`,
      type: 'get',
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        reject(error)
      }
    })
  })
}

export function createCategory(data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: contextPath + '/api/category/create',
      type: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        reject(error)
      }
    })
  })
}

export function updateCategory(data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: contextPath + '/api/category/update',
      type: 'put',
      data: JSON.stringify(data),
      contentType: 'application/json',
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        reject(error)
      }
    })
  })
}

export function deleteCategory(data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: contextPath + '/api/category/delete',
      type: 'delete',
      data: JSON.stringify(data),
      contentType: 'application/json',
      success: function (response) {
        resolve(response)
      },
      error: function (xhr, status, error) {
        reject(error)
      }
    })
  })
}
