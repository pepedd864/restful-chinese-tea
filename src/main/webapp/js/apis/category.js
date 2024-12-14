export function getAllCategory(contextPath) {
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

export function createCategory(contextPath, data) {
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

export function updateCategory(contextPath, data) {
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

export function deleteCategory(contextPath, data) {
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
